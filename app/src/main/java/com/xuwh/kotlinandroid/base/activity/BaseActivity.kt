package com.xuwh.kotlinandroid.base.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.enums.LoadState
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.databinding.ActivityBaseBinding
import com.xuwh.kotlinandroid.databinding.LayoutEmptyBinding
import com.xuwh.kotlinandroid.databinding.LayoutErrorBinding
import com.xuwh.kotlinandroid.enums.RefreshState
import com.xuwh.kotlinandroid.ext.dismissLoadingDialogExt
import com.xuwh.kotlinandroid.ext.showLoadingDialogExt
import com.xuwh.kotlinandroid.utils.StatusBarUtil
import com.xuwh.kotlinandroid.utils.ToastUtils

/**
 *
 * @ProjectName:    BaseKotlin
 * @Package:        com.xuwh.kotlinandroid.base.activity
 * @ClassName:      BaseActivity
 * @Description:    activity基类
 * @Author:         xuwh
 * @CreateDate:     2025/3/11 下午7:58
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/11 下午7:58
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM

    // 状态布局相关控件
    private var layoutEmptyBinding: LayoutEmptyBinding? = null
    private var layoutErrorBinding: LayoutErrorBinding? = null
    private lateinit var mActivityBaseBinding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StatusBarUtil.transparencyBar(this) //设置状态栏全透明
        StatusBarUtil.setStatusBarLightMode(this, true)

        mActivityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)

        binding = DataBindingUtil.inflate(
            layoutInflater, layoutId, mActivityBaseBinding
                .flContentContainer, true
        )
        binding.lifecycleOwner = this

        mActivityBaseBinding.ivBack.setOnClickListener { finish() }

        layoutErrorBinding?.tvError?.setOnClickListener(viewModel::onRetry)

        bindViewModel()
        observeStates()
        initData()
    }

    private fun observeStates() {
        viewModel.loadState.observe(this) { state ->
            removeLoadView()
            when (state) {
                is LoadState.Loading -> showLoadingDialogExt()
                is LoadState.Success -> dismissLoadingDialogExt()
                is LoadState.Empty -> {
                    dismissLoadingDialogExt()
                    if (null == layoutEmptyBinding) {
                        layoutEmptyBinding = DataBindingUtil.inflate(
                            layoutInflater, R.layout
                                .layout_empty, mActivityBaseBinding.flContentContainer, false
                        )
                    }
                    mActivityBaseBinding.flContentContainer.addView(layoutEmptyBinding?.root)
                }

                is LoadState.Error -> {
                    dismissLoadingDialogExt()
                    if (isShowErrorView()) {
                        if (null == layoutErrorBinding) {
                            layoutErrorBinding = DataBindingUtil.inflate(
                                layoutInflater, R.layout
                                    .layout_error, mActivityBaseBinding.flContentContainer, false
                            )
                        }
                        mActivityBaseBinding.flContentContainer.addView(layoutErrorBinding?.root)
                        return@observe
                    }
                    ToastUtils.show(state.errorMsg)
                }
            }
        }

        viewModel.navigateToLogin.observe(this) { needLogin ->
//            if (needLogin) startActivity(Intent(this, LoginActivity::class.java))
        }

    }

    open fun observeRefreshState(smartRefreshLayout: SmartRefreshLayout) {
        viewModel.refreshState.observe(this) { refreshState ->
            when (refreshState) {
                RefreshState.RefreshEnd -> smartRefreshLayout.finishRefresh()
                RefreshState.LoadMoreEnd -> smartRefreshLayout.finishLoadMore()
            }
        }
    }

    open fun observeHasMore(smartRefreshLayout: SmartRefreshLayout) {
        viewModel.hasMore.observe(this) { hasMore ->
            smartRefreshLayout.setNoMoreData(!hasMore)
        }
    }

    protected fun setAppBarViewVisibility(visibility: Boolean) {
        mActivityBaseBinding.constraintAppBr.visibility =
            if (visibility) View.VISIBLE else View.GONE

    }

    protected fun setTitleText(title: String) {
        mActivityBaseBinding.tvTitle.text = title
    }

    private fun removeLoadView() {
        val childCount = mActivityBaseBinding.flContentContainer.childCount
        if (childCount > 1) {
            mActivityBaseBinding.flContentContainer.removeViews(1, childCount)
        }
    }

    abstract val layoutId: Int
    abstract fun bindViewModel()

    abstract fun initData()

    protected open fun isShowErrorView(): Boolean {
        return false
    }

    override fun onDestroy() {
        super.onDestroy()

        binding?.apply {
            unbind()
            lifecycleOwner = null
        }
        layoutEmptyBinding = null
        layoutErrorBinding = null
        System.gc()
    }

}
package com.xuwh.kotlinandroid.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.databinding.ActivityBaseBinding
import com.xuwh.kotlinandroid.databinding.FragmentBaseBinding
import com.xuwh.kotlinandroid.databinding.LayoutEmptyBinding
import com.xuwh.kotlinandroid.databinding.LayoutErrorBinding
import com.xuwh.kotlinandroid.enums.LoadState
import com.xuwh.kotlinandroid.enums.RefreshState
import com.xuwh.kotlinandroid.ext.dismissLoadingDialogExt
import com.xuwh.kotlinandroid.ext.showLoadingDialogExt
import com.xuwh.kotlinandroid.utils.ToastUtils

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.base.fragment
 * @ClassName:      BaseFragment
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/14 下午9:55
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/14 下午9:55
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    protected lateinit var binding: VB
    protected abstract val viewModel: VM

    // 状态布局相关控件
    private var layoutEmptyBinding: LayoutEmptyBinding? = null
    private var layoutErrorBinding: LayoutErrorBinding? = null
    private lateinit var fragmentBaseBinding: FragmentBaseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentBaseBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)

        binding = DataBindingUtil.inflate(
            inflater, layoutId, fragmentBaseBinding
                .flContentContainer, true
        )
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        observeStates()
        initData()
    }

    private fun observeStates() {
        viewModel.loadState.observe(viewLifecycleOwner) { state ->
            removeLoadView()
            when (state) {
                is LoadState.Loading -> showLoadingDialogExt()
                is LoadState.Success -> dismissLoadingDialogExt()
                is LoadState.Empty -> {
                    dismissLoadingDialogExt()
                    if (null == layoutEmptyBinding) {
                        layoutEmptyBinding = DataBindingUtil.inflate(
                            layoutInflater, R.layout
                                .layout_empty, fragmentBaseBinding.flContentContainer, false
                        )
                    }
                    fragmentBaseBinding.flContentContainer.addView(layoutEmptyBinding?.root)
                }

                is LoadState.Error -> {
                    dismissLoadingDialogExt()
                    if (isShowErrorView()) {
                        if (null == layoutErrorBinding) {
                            layoutErrorBinding = DataBindingUtil.inflate(
                                layoutInflater, R.layout
                                    .layout_error, fragmentBaseBinding.flContentContainer, false
                            )
                        }
                        fragmentBaseBinding.flContentContainer.addView(layoutErrorBinding?.root)
                        return@observe
                    }
                    ToastUtils.show(state.errorMsg)
                }
            }
        }

        viewModel.navigateToLogin.observe(viewLifecycleOwner) { needLogin ->
//            if (needLogin) startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
    }

    open fun observeRefreshState(smartRefreshLayout: SmartRefreshLayout) {
        viewModel.refreshState.observe(viewLifecycleOwner) { refreshState ->
            when (refreshState) {
                RefreshState.RefreshEnd -> smartRefreshLayout.finishRefresh()
                RefreshState.LoadMoreEnd -> smartRefreshLayout.finishLoadMore()
            }
        }
    }

    open fun observeHasMore(smartRefreshLayout: SmartRefreshLayout) {
        viewModel.hasMore.observe(viewLifecycleOwner) { hasMore ->
            smartRefreshLayout.setNoMoreData(!hasMore)
        }
    }

    private fun removeLoadView() {
        val childCount = fragmentBaseBinding.flContentContainer.childCount
        if (childCount > 1) {
            fragmentBaseBinding.flContentContainer.removeViews(1, childCount)
        }
    }


    protected open fun isShowErrorView(): Boolean {
        return false
    }

    abstract val layoutId: Int
    abstract fun bindViewModel()
    open fun initData() {}
}
package com.xuwh.kotlinandroid.ui.home.view

import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleRegistry
import com.alibaba.android.arouter.launcher.ARouter
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.animator.HomeItemAnimator
import com.xuwh.kotlinandroid.base.fragment.BaseLazyFragment
import com.xuwh.kotlinandroid.databinding.FragmentHomeBinding
import com.xuwh.kotlinandroid.ext.verticalLayout
import com.xuwh.kotlinandroid.ui.home.adapter.HomeArticleAdapter
import com.xuwh.kotlinandroid.ui.home.viewmodel.HomeViewModel
import com.xuwh.kotlinandroid.utils.ToastUtils
import com.xuwh.kotlinandroid.widget.banner.BannerAdapter
import com.xuwh.kotlinandroid.widget.banner.BannerView
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.home.view
 * @ClassName:      HomeFragment
 * @Description:    首页fragment
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午2:10
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午2:10
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@AndroidEntryPoint
class HomeFragment : BaseLazyFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels<HomeViewModel>()

    override val layoutId = R.layout.fragment_home

    private val adapter: HomeArticleAdapter by lazy {
        HomeArticleAdapter(viewModel.homeArticleList).apply {
            onCollectClick = { id, isCollect, position ->
                if (isCollect) {
                    viewModel.dispatch(HomeViewModel.HomeAction.UnCollect(id, position))
                } else {
                    viewModel.dispatch(HomeViewModel.HomeAction.Coloect(id, position))
                }
            }

            onItemClick = {
                //TODO 点击事件
                ToastUtils.show("点击了$it")
//                ARouter.getInstance().build("").navigation()
            }
        }
    }


    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun lazyLoad() {

        viewModel.homeEvent.observe(this) { event ->

            event?.getContentIfNotHandled()?.let { result ->

                when (result) {
                    is HomeViewModel.HomeEvent.BannerRefresh -> {
                        binding.bannerView.apply {
                            setOnBannerItemClickListener(object :BannerView
                                .OnBannerItemClickListener{
                                override fun onItemClick(position: Int) {
                                    ToastUtils.show("点击了$position")
                                }
                            })
                            setImageList(viewModel.bannerList)
                            viewLifecycleOwner.lifecycle.addObserver(this)
                        }
                    }
                }
            }
        }

        binding.refreshView.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                viewModel.dispatch(HomeViewModel.HomeAction.ArticleRefresh)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                viewModel.dispatch(HomeViewModel.HomeAction.ArticleLoadMore)
            }
        })

        binding.rvArticle.run {
            verticalLayout()
            adapter=this@HomeFragment.adapter
            itemAnimator=HomeItemAnimator().also {
                it.setupAnimation(this)
            }
        }

        observeRefreshState(binding.refreshView)
        observeHasMore(binding.refreshView)
    }

}
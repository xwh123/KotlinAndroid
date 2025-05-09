package com.xuwh.kotlinandroid.ui.collect.view

import androidx.fragment.app.viewModels
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.fragment.BaseLazyFragment
import com.xuwh.kotlinandroid.databinding.FragmentCollectBinding
import com.xuwh.kotlinandroid.ext.verticalLayout
import com.xuwh.kotlinandroid.ui.collect.adapter.CollectArticleAdapter
import com.xuwh.kotlinandroid.ui.collect.viewmodel.CollectEvent
import com.xuwh.kotlinandroid.ui.collect.viewmodel.CollectViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.collect.view
 * @ClassName:      CollectFragment
 * @Description:    收藏文章页面
 * @Author:         xuwh
 * @CreateDate:     2025/3/21 下午1:18
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/21 下午1:18
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@AndroidEntryPoint
class CollectFragment:BaseLazyFragment<FragmentCollectBinding,CollectViewModel>() {

    override val viewModel: CollectViewModel by viewModels()

    override val layoutId= R.layout.fragment_collect

    override fun bindViewModel() {
        binding.viewModel=viewModel
    }

    override fun lazyLoad() {


        binding.refreshView.setOnRefreshLoadMoreListener(object :OnRefreshLoadMoreListener{
            override fun onRefresh(refreshLayout: RefreshLayout) {

                viewModel.dispatch(CollectEvent.Refresh)
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {

                viewModel.dispatch(CollectEvent.LoadMore)
            }

        })

        binding.rvCollectArticle.apply {
            verticalLayout()
            adapter= CollectArticleAdapter(viewModel.collectArticles)
        }

        viewModel.loadCollectArticles()

        observeRefreshState(binding.refreshView)
        observeHasMore(binding.refreshView)
    }

}
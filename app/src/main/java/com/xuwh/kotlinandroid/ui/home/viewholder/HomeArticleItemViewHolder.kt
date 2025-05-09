package com.xuwh.kotlinandroid.ui.home.viewholder

import android.view.ViewGroup
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.viewholder.BaseViewHolder
import com.xuwh.kotlinandroid.databinding.ItemHomeArticleBinding
import com.xuwh.kotlinandroid.ui.home.viewmodel.item.HomeArticleItemViewModel

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.home.viewholder
 * @ClassName:      HomeArticleItemViewHolder
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午6:07
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午6:07
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class HomeArticleItemViewHolder(parent: ViewGroup, layoutId: Int = R.layout.item_home_article) :
    BaseViewHolder<ItemHomeArticleBinding, HomeArticleItemViewModel>(
        parent,
        layoutId
    ) {
    override fun initViewModel() {
       mViewModel=HomeArticleItemViewModel()
    }

    override fun bindViewModel() {
       mDataBinding.viewModel=mViewModel
    }

    override fun init() {

    }
}
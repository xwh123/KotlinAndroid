package com.xuwh.kotlinandroid.ui.collect.viewholder

import android.view.ViewGroup
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.viewholder.BaseViewHolder
import com.xuwh.kotlinandroid.databinding.ItemCollectArticleBinding
import com.xuwh.kotlinandroid.ui.collect.viewmodel.item.CollectArticleItemViewModel

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.collect.viewholder
 * @ClassName:      CollectArticleViewHolder
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/21 下午2:57
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/21 下午2:57
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class CollectArticleViewHolder(
    parent: ViewGroup,
    layoutId: Int = R.layout.item_collect_article
):BaseViewHolder<ItemCollectArticleBinding,CollectArticleItemViewModel>(parent,layoutId) {
    override fun initViewModel() {
        mViewModel=CollectArticleItemViewModel()
    }

    override fun bindViewModel() {
       mDataBinding.viewModel=mViewModel
    }

    override fun init() {
    }
}
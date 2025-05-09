package com.xuwh.kotlinandroid.ui.collect.adapter

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.xuwh.kotlinandroid.base.adapter.BaseAdapter
import com.xuwh.kotlinandroid.net.bean.CollectArticle
import com.xuwh.kotlinandroid.ui.collect.viewholder.CollectArticleViewHolder

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.collect.adapter
 * @ClassName:      CollectArticleAdapter
 * @Description:    收藏文章
 * @Author:         xuwh
 * @CreateDate:     2025/3/21 下午2:59
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/21 下午2:59
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class CollectArticleAdapter(data: LiveData<List<CollectArticle>>) : BaseAdapter<CollectArticle,
        CollectArticleViewHolder>(data) {

    override fun areItemsTheSame(oldItem: CollectArticle, newItem: CollectArticle): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: CollectArticle, newItem: CollectArticle): Boolean {
        return oldItem==newItem
    }

    override fun onBindData(holder: CollectArticleViewHolder, position: Int) {

        val collectArticle = mDataList[position]
        holder.mDataBinding.viewModel?.setBaseItemViewModel(collectArticle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectArticleViewHolder {
        return CollectArticleViewHolder(parent)
    }
}
package com.xuwh.kotlinandroid.ui.navigation.adapter

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.xuwh.kotlinandroid.base.adapter.BaseAdapter
import com.xuwh.kotlinandroid.net.bean.Article
import com.xuwh.kotlinandroid.net.bean.Children
import com.xuwh.kotlinandroid.ui.navigation.viewholder.NavigationChildrenViewHolder
import com.xuwh.kotlinandroid.ui.structure.viewholder.StructureChildrenViewHolder

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.adapter
 * @ClassName:      StructureAdapter
 * @Description:    导航页面的Adapter
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午2:28
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午2:28
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class NavigationChildrenAdapter(data: LiveData<List<Article>>) : BaseAdapter<Article,
        NavigationChildrenViewHolder>(data) {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }

    override fun onBindData(holder: NavigationChildrenViewHolder, position: Int) {
        val children = mDataList[position]
        holder.mDataBinding.viewModel?.setBaseItemViewModel(children)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationChildrenViewHolder {

        return NavigationChildrenViewHolder(parent)
    }
}
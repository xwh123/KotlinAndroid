package com.xuwh.kotlinandroid.ui.navigation.adapter

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.xuwh.kotlinandroid.base.adapter.BaseAdapter
import com.xuwh.kotlinandroid.ext.flowLayout
import com.xuwh.kotlinandroid.net.bean.Navigation
import com.xuwh.kotlinandroid.net.bean.Structure
import com.xuwh.kotlinandroid.ui.navigation.viewholder.NavigationViewHolder
import com.xuwh.kotlinandroid.ui.structure.viewholder.StructureViewHolder

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
class NavigationAdapter(data: LiveData<List<Navigation>>) : BaseAdapter<Navigation,
        NavigationViewHolder>(data) {

    override fun areItemsTheSame(oldItem: Navigation, newItem: Navigation): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Navigation, newItem: Navigation): Boolean {
        return oldItem == newItem
    }

    override fun onBindData(holder: NavigationViewHolder, position: Int) {
        val navigation = mDataList[position]
        // 将 List 转换为 LiveData
        val childrenLiveData = MutableLiveData(navigation.articles)

        with(holder.mDataBinding.rvStructureChildren){
            flowLayout(spacing = 6, lineSpacing = 5,
                orientation = FlexDirection.ROW, alignItems = AlignItems.FLEX_START)

            isNestedScrollingEnabled = false
            adapter = NavigationChildrenAdapter(childrenLiveData)
        }

        holder.mDataBinding.viewModel?.setBaseItemViewModel(navigation)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationViewHolder {

        return NavigationViewHolder(parent)
    }
}
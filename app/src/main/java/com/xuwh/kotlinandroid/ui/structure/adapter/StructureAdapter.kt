package com.xuwh.kotlinandroid.ui.structure.adapter

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.xuwh.kotlinandroid.base.adapter.BaseAdapter
import com.xuwh.kotlinandroid.ext.dpToPx
import com.xuwh.kotlinandroid.ext.flowLayout
import com.xuwh.kotlinandroid.ext.verticalLayout
import com.xuwh.kotlinandroid.net.bean.Structure
import com.xuwh.kotlinandroid.ui.structure.viewholder.StructureViewHolder

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.adapter
 * @ClassName:      StructureAdapter
 * @Description:    体系页面的Adapter
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午2:28
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午2:28
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class StructureAdapter(data: LiveData<List<Structure>>) : BaseAdapter<Structure,
        StructureViewHolder>(data) {

    override fun areItemsTheSame(oldItem: Structure, newItem: Structure): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Structure, newItem: Structure): Boolean {
        return oldItem == newItem
    }

    override fun onBindData(holder: StructureViewHolder, position: Int) {
        val structure = mDataList[position]
        // 将 List 转换为 LiveData
        val childrenLiveData = MutableLiveData(structure.children)

        with(holder.mDataBinding.rvStructureChildren){
            flowLayout(spacing = 6, lineSpacing = 5,
                orientation = FlexDirection.ROW, alignItems = AlignItems.FLEX_START)

            isNestedScrollingEnabled = false
            adapter = StructureChildrenAdapter(childrenLiveData)
        }

        holder.mDataBinding.viewModel?.setBaseItemViewModel(structure)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StructureViewHolder {

        return StructureViewHolder(parent)
    }
}
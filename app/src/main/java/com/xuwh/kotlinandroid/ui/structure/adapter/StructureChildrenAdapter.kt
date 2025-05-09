package com.xuwh.kotlinandroid.ui.structure.adapter

import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.xuwh.kotlinandroid.base.adapter.BaseAdapter
import com.xuwh.kotlinandroid.net.bean.Children
import com.xuwh.kotlinandroid.net.bean.Structure
import com.xuwh.kotlinandroid.ui.structure.viewholder.StructureChildrenViewHolder
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
class StructureChildrenAdapter(data: LiveData<List<Children>>) : BaseAdapter<Children,
        StructureChildrenViewHolder>(data) {

    override fun areItemsTheSame(oldItem: Children, newItem: Children): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Children, newItem: Children): Boolean {
        return oldItem == newItem
    }

    override fun onBindData(holder: StructureChildrenViewHolder, position: Int) {
        val children = mDataList[position]
        holder.mDataBinding.viewModel?.setBaseItemViewModel(children)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StructureChildrenViewHolder {

        return StructureChildrenViewHolder(parent)
    }
}
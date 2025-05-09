package com.xuwh.kotlinandroid.ui.structure.viewholder

import android.view.ViewGroup
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.viewholder.BaseViewHolder
import com.xuwh.kotlinandroid.databinding.ItemChildrenStructureBinding
import com.xuwh.kotlinandroid.ui.structure.viewmodel.item.StructureChildrenItemViewMode

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.viewholder
 * @ClassName:      StructureChildrenViewHolder
 * @Description:     体系item的子item的ViewHolder
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午2:24
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午2:24
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class StructureChildrenViewHolder(
    parent: ViewGroup,
    layoutId: Int = R.layout.item_children_structure
) : BaseViewHolder<ItemChildrenStructureBinding,
        StructureChildrenItemViewMode>(
    parent,
    layoutId
) {
    override fun initViewModel() {
        mViewModel = StructureChildrenItemViewMode()
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
    }
}
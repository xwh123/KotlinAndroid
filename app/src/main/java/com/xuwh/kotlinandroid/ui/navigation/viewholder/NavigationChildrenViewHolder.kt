package com.xuwh.kotlinandroid.ui.navigation.viewholder

import android.view.ViewGroup
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.viewholder.BaseViewHolder
import com.xuwh.kotlinandroid.databinding.ItemChildrenNavigationBinding
import com.xuwh.kotlinandroid.databinding.ItemChildrenStructureBinding
import com.xuwh.kotlinandroid.ui.navigation.viewmodel.item.NavigationChildrenItemViewMode
import com.xuwh.kotlinandroid.ui.structure.viewmodel.item.StructureChildrenItemViewMode

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.viewholder
 * @ClassName:      StructureChildrenViewHolder
 * @Description:    导航item的子item的ViewHolder
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午2:24
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午2:24
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class NavigationChildrenViewHolder(
    parent: ViewGroup,
    layoutId: Int = R.layout.item_children_navigation
) : BaseViewHolder<ItemChildrenNavigationBinding,
        NavigationChildrenItemViewMode>(
    parent,
    layoutId
) {
    override fun initViewModel() {
        mViewModel = NavigationChildrenItemViewMode()
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
    }
}
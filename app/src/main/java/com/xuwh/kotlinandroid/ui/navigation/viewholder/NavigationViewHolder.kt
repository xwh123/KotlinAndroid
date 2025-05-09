package com.xuwh.kotlinandroid.ui.navigation.viewholder

import android.view.ViewGroup
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.viewholder.BaseViewHolder
import com.xuwh.kotlinandroid.databinding.ItemNavigationBinding
import com.xuwh.kotlinandroid.databinding.ItemStructureBinding
import com.xuwh.kotlinandroid.ui.navigation.viewmodel.item.NavigationItemViewMode
import com.xuwh.kotlinandroid.ui.structure.viewmodel.item.StructureItemViewMode

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.viewholder
 * @ClassName:      StructureChildrenViewHolder
 * @Description:    导航item
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午2:24
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午2:24
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class NavigationViewHolder(
    parent: ViewGroup,
    layoutId: Int = R.layout.item_navigation
) : BaseViewHolder<ItemNavigationBinding,
        NavigationItemViewMode>(
    parent,
    layoutId
) {
    override fun initViewModel() {
        mViewModel = NavigationItemViewMode()
    }

    override fun bindViewModel() {
        mDataBinding.viewModel = mViewModel
    }

    override fun init() {
    }
}
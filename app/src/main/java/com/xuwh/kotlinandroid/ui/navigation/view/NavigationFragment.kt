package com.xuwh.kotlinandroid.ui.navigation.view

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.fragment.BaseLazyFragment
import com.xuwh.kotlinandroid.databinding.FragmentNavigationBinding
import com.xuwh.kotlinandroid.databinding.FragmentStructureBinding
import com.xuwh.kotlinandroid.databinding.ItemNavigationBinding
import com.xuwh.kotlinandroid.ext.verticalLayout
import com.xuwh.kotlinandroid.ui.navigation.adapter.NavigationAdapter
import com.xuwh.kotlinandroid.ui.navigation.viewmodel.NavigationViewModel
import com.xuwh.kotlinandroid.ui.navigation.viewmodel.item.NavigationItemViewMode
import com.xuwh.kotlinandroid.ui.structure.adapter.StructureAdapter
import com.xuwh.kotlinandroid.ui.structure.viewmodel.StructureViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.view
 * @ClassName:      StructureFragment
 * @Description:    导航页面
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午1:42
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午1:42
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@AndroidEntryPoint
class NavigationFragment : BaseLazyFragment<FragmentNavigationBinding, NavigationViewModel>(){

    override val viewModel: NavigationViewModel by viewModels()
    override val layoutId = R.layout.fragment_navigation

    private lateinit var navAdapter: NavigationAdapter

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun lazyLoad() {

        binding.rvNavigation.apply {
            verticalLayout()
            navAdapter = NavigationAdapter(viewModel.navigationList)
            adapter = navAdapter
        }

        viewModel.loadStructure()
    }

}
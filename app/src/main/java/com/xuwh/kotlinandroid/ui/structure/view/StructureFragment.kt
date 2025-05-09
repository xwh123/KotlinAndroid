package com.xuwh.kotlinandroid.ui.structure.view

import androidx.fragment.app.viewModels
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.fragment.BaseLazyFragment
import com.xuwh.kotlinandroid.databinding.FragmentStructureBinding
import com.xuwh.kotlinandroid.ext.verticalLayout
import com.xuwh.kotlinandroid.ui.structure.adapter.StructureAdapter
import com.xuwh.kotlinandroid.ui.structure.viewmodel.StructureViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.view
 * @ClassName:      StructureFragment
 * @Description:    体系页面
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午1:42
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午1:42
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@AndroidEntryPoint
class StructureFragment : BaseLazyFragment<FragmentStructureBinding, StructureViewModel>() {

    override val viewModel: StructureViewModel by viewModels()
    override val layoutId = R.layout.fragment_structure

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun lazyLoad() {

        binding.rvStructure.apply {
            verticalLayout()
            adapter = StructureAdapter(viewModel.structureList)
        }

        viewModel.loadStructure()
    }
}
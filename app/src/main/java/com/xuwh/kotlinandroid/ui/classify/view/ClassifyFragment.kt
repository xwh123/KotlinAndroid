package com.xuwh.kotlinandroid.ui.classify.view

import androidx.fragment.app.viewModels
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.fragment.BaseLazyFragment
import com.xuwh.kotlinandroid.databinding.FragmentClassifyBinding
import com.xuwh.kotlinandroid.ui.classify.viewmodel.ClassifyViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.classify.view
 * @ClassName:      ClassifyFragment
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午12:21
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午12:21
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@AndroidEntryPoint
class ClassifyFragment : BaseLazyFragment<FragmentClassifyBinding, ClassifyViewModel>() {

    override val viewModel : ClassifyViewModel by viewModels()

    override val layoutId = R.layout.fragment_classify

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun lazyLoad() {

        viewModel.setupViewPager(this, binding.viewPager)
        viewModel.setupTabLayout(binding.tabClassify, binding.viewPager)

    }

}
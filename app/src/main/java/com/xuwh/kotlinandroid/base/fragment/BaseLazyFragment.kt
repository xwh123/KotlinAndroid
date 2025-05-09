package com.xuwh.kotlinandroid.base.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.base.fragment
 * @ClassName:      BaseLazyFragment
 * @Description:    懒加载fragment基类
 * @Author:         xuwh
 * @CreateDate:     2025/3/14 下午9:58
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/14 下午9:58
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class BaseLazyFragment<VB : ViewDataBinding, VM : BaseViewModel> :
    BaseFragment<VB, VM>() {

    private var isViewCreated = false
    private var isDataLoaded = false
    private var isVisibleToUser = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        lazyLoadDataIfVisible()
    }

    override fun onResume() {
        super.onResume()
        isVisibleToUser = true
        lazyLoadDataIfVisible()
    }

    private fun lazyLoadDataIfVisible() {
        if (isViewCreated && !isDataLoaded && isVisibleToUser) {
            lazyLoad()
            isDataLoaded = true
        }
    }

    override fun onPause() {
        super.onPause()
        isVisibleToUser = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isViewCreated = false
        isDataLoaded = false
    }

    abstract fun lazyLoad()
}
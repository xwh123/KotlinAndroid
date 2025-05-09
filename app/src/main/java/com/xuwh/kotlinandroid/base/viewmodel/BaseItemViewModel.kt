package com.xuwh.kotlinandroid.base.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @ProjectName:    BaseKotlin
 * @Package:        com.xuwh.kotlinandroid.base.viewmodel
 * @ClassName:      BaseItemViewModel
 * @Description:    列表item的viewmodel基类
 * @Author:         xuwh
 * @CreateDate:     2025/3/12 下午2:29
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/12 下午2:29
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class BaseItemViewModel<T> : ViewModel() {

    private val _baseItemViewModel = MutableLiveData<T>()

    val baseItemViewModel:LiveData<T> = _baseItemViewModel

    fun setBaseItemViewModel(t:T){
        clearItemData()
        _baseItemViewModel.value=t
        t?.let {
            setAllModel(it)
        }
    }

    protected abstract fun setAllModel(t:T)

    // 新增清理方法
    fun clearItemData() {
        _baseItemViewModel.value = null
    }
}
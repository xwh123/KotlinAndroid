package com.xuwh.kotlinandroid.ui.structure.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.net.bean.Structure
import com.xuwh.kotlinandroid.ui.structure.repository.StructureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.viewmodel
 * @ClassName:      StructureViewModel
 * @Description:    体系页面的ViewModel
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午1:43
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午1:43
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@HiltViewModel
class StructureViewModel @Inject constructor(
    private val structureRepository:
    StructureRepository
) : BaseViewModel() {


    private val _structureList = MutableLiveData<List<Structure>>()

     val structureList = _structureList

     fun loadStructure() {
        executeRequest(request = structureRepository::queryStructure,
            onSuccess = {
                _structureList.value = it
            })
    }

}
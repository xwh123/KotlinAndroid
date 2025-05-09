package com.xuwh.kotlinandroid.ui.navigation.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.net.bean.Navigation
import com.xuwh.kotlinandroid.net.bean.Structure
import com.xuwh.kotlinandroid.ui.navigation.repository.NavigationRepository
import com.xuwh.kotlinandroid.ui.structure.repository.StructureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.viewmodel
 * @ClassName:      StructureViewModel
 * @Description:    导航页面的ViewModel
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午1:43
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午1:43
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigationRepository: NavigationRepository
) : BaseViewModel() {


    private val _navigationList = MutableLiveData<List<Navigation>>()

     val navigationList = _navigationList

     fun loadStructure() {
        executeRequest(request = navigationRepository::queryNavigation,
            onSuccess = {
                navigationList.value = it
            })
    }

}

package com.xuwh.kotlinandroid.ui.navigation.viewmodel.item

import androidx.lifecycle.MutableLiveData
import com.xuwh.kotlinandroid.base.viewmodel.BaseItemViewModel
import com.xuwh.kotlinandroid.net.bean.Navigation
import com.xuwh.kotlinandroid.net.bean.Structure

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.viewmodel.item
 * @ClassName:      StructureChildrenItemViewMode;
 * @Description:    导航item的ViewModel
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午2:07
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午2:07
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class NavigationItemViewMode : BaseItemViewModel<Navigation>() {

     val structureName = MutableLiveData<String>()

    override open fun setAllModel(t: Navigation) {
        structureName.value = t.name
    }
}
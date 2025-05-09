package com.xuwh.kotlinandroid.ui.structure.viewmodel.item

import androidx.lifecycle.MutableLiveData
import com.xuwh.kotlinandroid.base.viewmodel.BaseItemViewModel
import com.xuwh.kotlinandroid.net.bean.Children
import com.xuwh.kotlinandroid.net.bean.Structure

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.viewmodel.item
 * @ClassName:      StructureChildrenItemViewMode;
 * @Description:    体系item的子item的ViewModel
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午2:07
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午2:07
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class StructureChildrenItemViewMode : BaseItemViewModel<Children>() {

     val childrenName = MutableLiveData<String>()

    override fun setAllModel(t: Children) {
        childrenName.value = t.name
    }
}
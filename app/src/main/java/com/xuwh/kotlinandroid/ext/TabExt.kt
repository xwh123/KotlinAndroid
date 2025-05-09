package com.xuwh.kotlinandroid.ext

import android.os.Build
import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayout
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.ui.classify.viewmodel.ClassTab

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ext
 * @ClassName:      TabExt
 * @Description:    tab 扩展类
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午1:10
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午1:10
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
/**
 * 禁用长按事件
 * @receiver TabLayout.TabView
 * @param tabView TabView
 */
fun TabLayout.TabView.disableLongClick() {
    isLongClickable = false
    setOnLongClickListener { true }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        tooltipText = null
    }
}


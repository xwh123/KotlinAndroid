package com.xuwh.kotlinandroid.ext

import android.content.res.Resources

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ext
 * @ClassName:      DpExt
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/19 下午3:50
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/19 下午3:50
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
// dp转换扩展
 fun Int.dp() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Float.dp() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()
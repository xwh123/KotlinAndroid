package com.xuwh.kotlinandroid.ext

import android.icu.text.SimpleDateFormat
import java.util.Locale

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ext
 * @ClassName:      日期扩展函数
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午6:01
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午6:01
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */

/**
 * 将时间戳转换为日期时间字符串
 * @receiver Long
 * @param pattern String
 * @return String
 */
fun Long.toDateTime(pattern: String = "yyyy-MM-dd HH:mm:ss"): String =
    SimpleDateFormat(pattern,Locale.getDefault()).format(this)
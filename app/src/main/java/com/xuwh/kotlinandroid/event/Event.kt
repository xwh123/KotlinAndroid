package com.xuwh.kotlinandroid.event

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.event
 * @ClassName:      EventAction
 * @Description:    事件类型
 * @Author:         xuwh
 * @CreateDate:     2025/3/17 下午6:37
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/17 下午6:37
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? = if (hasBeenHandled) null else {
        hasBeenHandled = true
        content
    }
}
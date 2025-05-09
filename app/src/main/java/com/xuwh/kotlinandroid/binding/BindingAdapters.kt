package com.xuwh.kotlinandroid.binding

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.binding
 * @ClassName:      BindingAdapters
 * @Description:     Data Binding 自定义绑定类
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午1:40
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午1:40
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
object BindingAdapters {

    private var lastClickTime = 0L
    private const val DEFAULT_THROTTLE_INTERVAL = 600L  // 默认间隔 500ms


    @JvmStatic
    @BindingAdapter("throttleClick")
    fun View.setThrottleClick(onClick: Runnable) {
        setThrottleClick(DEFAULT_THROTTLE_INTERVAL, onClick)
    }

    @JvmStatic
    @BindingAdapter("throttleClickInterval", "throttleClick")
    fun View.setThrottleClick(interval: Long, onClick: Runnable) {
        setOnClickListener {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime > interval) {
                lastClickTime = currentTime
                onClick.run()
            }else{
                Log.d("throttleClick","点击过快")
            }
        }
    }
}
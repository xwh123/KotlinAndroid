package com.xuwh.kotlinandroid.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import com.xuwh.kotlinandroid.R


/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.utils
 * @ClassName:      ToastUtils
 * @Description:    toast工具类
 * @Author:         xuwh
 * @CreateDate:     2025/3/14 下午11:57
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/14 下午11:57
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
object ToastUtils {

    private var isEnabled = true
    private lateinit var applicationContext: Context
    private var toastInstance: Toast? = null
    private var cachedTextView: TextView? = null
    /**
     * 初始化工具类
     * @param context 建议使用 ApplicationContext
     * @throws IllegalArgumentException 如果传入非法上下文
     */
    @MainThread
    fun init(context: Context) {
        applicationContext = context.applicationContext ?: throw IllegalArgumentException("Invalid context")
    }

    /**
     * 全局开关 Toast 显示
     */
    fun setEnabled(enabled: Boolean) {
        isEnabled = enabled
    }

    /**
     * 取消当前显示的 Toast
     */
    fun cancel() {
        toastInstance?.cancel()
    }

    @JvmOverloads
    fun show(message: String, duration: Int = Toast.LENGTH_SHORT) {
        showInternal(message, duration)
    }

    fun show(@StringRes resId: Int) {
        getStringSafely(resId)?.let { show(it) }
    }

    fun showLong(message: String) {
        showInternal(message, Toast.LENGTH_LONG)
    }

    fun showLong(@StringRes resId: Int) {
        getStringSafely(resId)?.let { showLong(it) }
    }

    private fun getStringSafely(@StringRes resId: Int): String? {
        return try {
            applicationContext.getString(resId)
        } catch (e: Exception) {
            null
        }
    }

    private fun showInternal(message: String, duration: Int) {
        if (!isEnabled || message.isBlank()) return
        ensureInitialized()

        // 切换到主线程执行
        if (!isMainThread()) {
            Handler(Looper.getMainLooper()).post { showInternal(message, duration) }
            return
        }

        prepareAndShow(message, duration)
    }

    @MainThread
    private fun prepareAndShow(message: String, duration: Int) {
        toastInstance?.cancel() // 取消前一个 Toast

        val textView = cachedTextView ?: createToastView()
        textView.text = message

        Toast(applicationContext).apply {
            this.duration = duration
            setGravity(Gravity.CENTER, 0, 0)
            view = textView.rootView
            show()
            toastInstance = this
        }
    }

    @MainThread
    private fun createToastView(): TextView {
        return LayoutInflater.from(applicationContext)
            .inflate(R.layout.layout_toast, null)
            .findViewById<TextView>(R.id.tv_toast_message)
            .also { cachedTextView = it }
    }

    private fun ensureInitialized() {
        if (!::applicationContext.isInitialized) {
            throw IllegalStateException("ToastUtils must be initialized first")
        }
    }

    private fun isMainThread() = Looper.myLooper() == Looper.getMainLooper()
}
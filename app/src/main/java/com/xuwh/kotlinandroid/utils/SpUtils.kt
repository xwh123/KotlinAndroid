package com.xuwh.kotlinandroid.utils

import android.content.Context
import android.content.SharedPreferences

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.utils
 * @ClassName:      SpUtils
 * @Description:    SharedPreferences 工具类  使用前需要先调用init方法初始化
 * @Author:         xuwh
 * @CreateDate:     2025/3/17 下午6:22
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/17 下午6:22
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
object SpUtils {

    private const val DEFAULT_FILE_NAME = "kotlin_date"
    private lateinit var appContext: Context

    @Volatile
    public var spInstance: SharedPreferences? = null

    fun init(context: Context, fileName: String = DEFAULT_FILE_NAME) {
        appContext = context.applicationContext
        spInstance = appContext.getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    /**
     * 保存数据（自动识别类型）
     * @param key 存储键
     * @param value 存储值（支持String/Int/Boolean/Float/Long）
     */
    inline fun <reified T> setParameter(key: String, value: T?) {
        val sp = spInstance ?: throw IllegalStateException("Must call initSpTool first")
        value ?: run {
            sp.edit().remove(key).apply()
            return
        }

        sp.edit().apply {
            when (T::class) {
                String::class -> putString(key, value as String)
                Int::class -> putInt(key, value as Int)
                Boolean::class -> putBoolean(key, value as Boolean)
                Float::class -> putFloat(key, value as Float)
                Long::class -> putLong(key, value as Long)
                else -> throw IllegalArgumentException("Unsupported type: ${T::class.simpleName}")
            }
        }.apply()
    }

    /**
     * 获取存储参数（类型安全版）
     * @param key 存储键
     * @param defaultValue 默认值（决定返回类型）
     */
    inline fun <reified T> getParameter(key: String, defaultValue: T): T {
        val sp = spInstance ?: throw IllegalStateException("Must call initSpTool first")
        return when (T::class) {
            String::class -> sp.getString(key, defaultValue as String) as T
            Int::class -> sp.getInt(key, defaultValue as Int) as T
            Boolean::class -> sp.getBoolean(key, defaultValue as Boolean) as T
            Float::class -> sp.getFloat(key, defaultValue as Float) as T
            Long::class -> sp.getLong(key, defaultValue as Long) as T
            else -> throw IllegalArgumentException("Unsupported type: ${T::class.simpleName}")
        }
    }

    /**
     * 清除所有数据
     */
    fun clear() {
        spInstance?.edit()?.clear()?.apply()
    }

    /**
     * 移除指定键数据
     */
    fun remove(key: String) {
        spInstance?.edit()?.remove(key)?.apply()
    }
}
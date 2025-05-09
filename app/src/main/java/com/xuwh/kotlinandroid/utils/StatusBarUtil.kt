package com.xuwh.kotlinandroid.utils

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.utils
 * @ClassName:      StatusBarUtil
 * @Description:     状态栏工具类
 * @Author:         xuwh
 * @CreateDate:     2025/3/14 下午10:08
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/14 下午10:08
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
object StatusBarUtil {

    private const val DEFAULT_STATUS_BAR_HEIGHT_DP = 25f
    private const val MIUI_DARK_MODE_FLAG_FIELD = "EXTRA_FLAG_STATUS_BAR_DARK_MODE"
    private const val MEIZU_DARK_FLAG_FIELD = "MEIZU_FLAG_DARK_STATUS_BAR_ICON"
    private const val MEIZU_FLAGS_FIELD = "meizuFlags"

    @Volatile
    private var statusBarHeight: Int = 0
    private var isStatusBarHeightParsed: Boolean = false


    /**
     * 修改状态栏为全透明
     *
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun transparencyBar(activity: Activity) {
        val window = activity.window
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            }
        }
    }

    /**
     * 设置状态栏亮色模式
     * @return 适配结果：1-MIUI 2-Flyme 3-Android原生 0-未适配
     */
    fun setStatusBarLightMode(activity: Activity, dark: Boolean): Int {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return 0

        return when {
            trySetMiuiStatusBarDarkMode(activity, dark) -> 1
            trySetFlymeStatusBarDarkMode(activity.window, dark) -> 2
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                setAndroidNativeStatusBar(activity, dark)
                3
            }
            else -> 0
        }
    }

    /**
     * 适配Android 6.0+原生方案
     */
    @TargetApi(Build.VERSION_CODES.M)
    private fun setAndroidNativeStatusBar(activity: Activity, dark: Boolean) {
        val decorView = activity.window.decorView
        var systemUiVisibility = decorView.systemUiVisibility
        systemUiVisibility = if (dark) {
            systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        decorView.systemUiVisibility = systemUiVisibility
    }

    /**
     * 适配Flyme状态栏
     */
    private fun trySetFlymeStatusBarDarkMode(window: Window, dark: Boolean): Boolean {
        return runCatching {
            val lp = window.attributes
            val darkFlag = WindowManager.LayoutParams::class.java.getDeclaredField(MEIZU_DARK_FLAG_FIELD)
            val meizuFlags = WindowManager.LayoutParams::class.java.getDeclaredField(MEIZU_FLAGS_FIELD)

            darkFlag.isAccessible = true
            meizuFlags.isAccessible = true

            val bit = darkFlag.getInt(null)
            var value = meizuFlags.getInt(lp)
            value = if (dark) value or bit else value and bit.inv()

            meizuFlags.setInt(lp, value)
            window.attributes = lp
            true
        }.getOrElse {
            false
        }
    }

    /**
     * 适配MIUI状态栏
     */
    private fun trySetMiuiStatusBarDarkMode(activity: Activity, dark: Boolean): Boolean {
        return runCatching {
            val window = activity.window
            val clazz: Class<*> = window.javaClass
            val layoutParams = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            val field = layoutParams.getField(MIUI_DARK_MODE_FLAG_FIELD)
            val darkModeFlag = field.getInt(layoutParams)
            val extraFlagField: Method = clazz.getMethod(
                "setExtraFlags",
                Int::class.javaPrimitiveType,
                Int::class.javaPrimitiveType
            )
            extraFlagField.invoke(window, if (dark) darkModeFlag else 0, darkModeFlag)

            // 适配开发版MIUI
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setAndroidNativeStatusBar(activity, dark)
            }
            true
        }.getOrElse {
            false
        }
    }

    /**
     * 获取状态栏高度（带缓存）
     */
    fun getStatusBarHeight(context: Context): Int {
        if (isStatusBarHeightParsed) return statusBarHeight

        return runCatching {
            val resources = context.resources
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                resources.getDimensionPixelSize(resourceId).also {
                    statusBarHeight = it
                    isStatusBarHeightParsed = true
                }
            } else {
                calculateDefaultStatusBarHeight(context)
            }
        }.getOrElse {
            calculateDefaultStatusBarHeight(context)
        }
    }

    /**
     * 计算默认状态栏高度
     */
    private fun calculateDefaultStatusBarHeight(context: Context): Int {
        val metrics: DisplayMetrics = context.resources.displayMetrics
        return (DEFAULT_STATUS_BAR_HEIGHT_DP * metrics.density + 0.5f).toInt()
    }
}
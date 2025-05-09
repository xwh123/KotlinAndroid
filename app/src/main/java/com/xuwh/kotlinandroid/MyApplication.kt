package com.xuwh.kotlinandroid

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.xuwh.kotlinandroid.utils.SpUtils
import com.xuwh.kotlinandroid.utils.ToastUtils
import dagger.hilt.android.HiltAndroidApp

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid
 * @ClassName:      MyApplication
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/15 下午5:12
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/15 下午5:12
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ARouter.init(this)

        ToastUtils.init(this)

        SpUtils.init(this)
    }

}
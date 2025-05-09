package com.xuwh.kotlinandroid.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import com.xuwh.kotlinandroid.R

/**
 *
 * @ProjectName:    BaseKotlin
 * @Package:        com.xuwh.kotlinandroid.dialog
 * @ClassName:      LoadingDialog
 * @Description:    加载view
 * @Author:         xuwh
 * @CreateDate:     2025/3/11 下午8:06
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/11 下午8:06
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class LoadingDialog : Dialog {

    constructor(context: Context) : super(context, R.style.dialog) {
        setContentView(R.layout.view_loading)

        setCancelable(false)
        setCanceledOnTouchOutside(false)

        window?.setDimAmount(0f)

        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.WRAP_CONTENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = params
        window?.setGravity(Gravity.CENTER)
    }
}
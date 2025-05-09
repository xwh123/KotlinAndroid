package com.xuwh.kotlinandroid.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.xuwh.kotlinandroid.dialog.LoadingDialog


/**
 *
 * @ProjectName:    BaseKotlin
 * @Package:        com.xuwh.kotlinandroid.ext
 * @ClassName:      LoadingDialogExt
 * @Description:    加载状态扩展函数
 * @Author:         xuwh
 * @CreateDate:     2025/3/11 下午8:03
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/11 下午8:03
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
private var loadingDialog: LoadingDialog? = null


fun AppCompatActivity.showLoadingDialogExt() {
    if (!this.isFinishing) {
        if (null == loadingDialog) {
            loadingDialog = LoadingDialog(this)
        }
        if (!loadingDialog!!.isShowing) {
            loadingDialog?.show()
        }

    }
}



fun AppCompatActivity.dismissLoadingDialogExt() {
    loadingDialog?.dismiss()
    loadingDialog = null
}

fun Fragment.showLoadingDialogExt() {
    if (!this.isDetached) {
        if (null == loadingDialog) {
            loadingDialog = LoadingDialog(this.requireContext())
        }
        if (!loadingDialog!!.isShowing) {
            loadingDialog?.show()
        }

    }
}



fun Fragment.dismissLoadingDialogExt() {
    loadingDialog?.dismiss()
    loadingDialog = null
}
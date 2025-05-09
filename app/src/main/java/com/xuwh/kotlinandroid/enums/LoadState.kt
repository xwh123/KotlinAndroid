package com.xuwh.kotlinandroid.enums

/**
 *
 * @ProjectName:    BaseKotlin
 * @Package:        com.xuwh.kotlinandroid.base
 * @ClassName:      LoadState
 * @Description:    加载状态
 * @Author:         xuwh
 * @CreateDate:     2025/3/11 下午7:41
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/11 下午7:41
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
sealed class LoadState<out T> {

    object Loading: LoadState<Nothing>()
    data class Success<T>(val data:T): LoadState<T>()
    object  Empty: LoadState<Nothing>()
    data class Error(val code:Int,val errorMsg:String): LoadState<Nothing>()
}
package com.xuwh.kotlinandroid.net

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.net
 * @ClassName:      DataResponse
 * @Description:    数据返回类
 * @Author:         xuwh
 * @CreateDate:     2025/3/17 下午3:26
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/17 下午3:26
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
data class DataResponse<T>(val data:T?,val errorCode:Int,val errorMsg:String)

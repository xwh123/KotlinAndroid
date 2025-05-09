package com.xuwh.kotlinandroid.ui.register.repository

import com.xuwh.kotlinandroid.net.ApiService
import com.xuwh.kotlinandroid.net.DataResponse
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.register.repository
 * @ClassName:      RegisterRepository
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/17 下午4:10
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/17 下午4:10
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */

class RegisterRepository @Inject constructor(private val api: ApiService) {

    suspend fun register(account: String, password: String, repassword: String) =
        api.register(account, password, repassword)
}
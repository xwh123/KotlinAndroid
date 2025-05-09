package com.xuwh.kotlinandroid.ui.login.repository

import com.xuwh.kotlinandroid.net.ApiService
import com.xuwh.kotlinandroid.net.DataResponse
import com.xuwh.kotlinandroid.net.bean.User
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.login.repository
 * @ClassName:      LoginRepository
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/17 下午2:49
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/17 下午2:49
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class LoginRepository @Inject constructor(private val api: ApiService) {

    suspend fun login(account: String, password: String): DataResponse<User> {
        return api.login(account, password)
    }
}
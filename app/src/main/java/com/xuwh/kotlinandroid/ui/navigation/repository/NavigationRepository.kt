package com.xuwh.kotlinandroid.ui.navigation.repository

import com.xuwh.kotlinandroid.net.ApiService
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.repository
 * @ClassName:      StructureRepository
 * @Description:    导航数据仓库
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午1:44
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午1:44
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class NavigationRepository @Inject constructor(private val api:ApiService) {

    suspend fun queryNavigation() = api.queryNavigation()

}
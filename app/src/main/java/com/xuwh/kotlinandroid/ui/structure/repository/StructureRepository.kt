package com.xuwh.kotlinandroid.ui.structure.repository

import com.xuwh.kotlinandroid.net.ApiService
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.structure.repository
 * @ClassName:      StructureRepository
 * @Description:    体系数据仓库
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午1:44
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午1:44
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class StructureRepository @Inject constructor(private val api:ApiService) {

    suspend fun queryStructure() = api.queryStructure()

}
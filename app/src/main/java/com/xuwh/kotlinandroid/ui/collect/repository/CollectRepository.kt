package com.xuwh.kotlinandroid.ui.collect.repository

import com.xuwh.kotlinandroid.net.ApiService
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.collect.repository
 * @ClassName:      CollectRepository
 * @Description:    收藏数据仓库
 * @Author:         xuwh
 * @CreateDate:     2025/3/21 下午1:15
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/21 下午1:15
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class CollectRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun queryCollectArticle(page: Int) = apiService.queryCollectArticle(page)
}
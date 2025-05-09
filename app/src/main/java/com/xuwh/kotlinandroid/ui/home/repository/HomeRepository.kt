package com.xuwh.kotlinandroid.ui.home.repository

import com.xuwh.kotlinandroid.net.ApiService
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.home.repository
 * @ClassName:      HomeRepository
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午2:12
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午2:12
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class HomeRepository @Inject constructor(private val api: ApiService) {

    suspend fun getBanner() = api.getBanner()

    suspend fun getHomeArticle(page: Int) = api.getHomeArticle(page)

    suspend fun collectInnerArticle(id: Int) = api.collectInnerArticle(id)

    suspend fun unCollectInnerArticle(id: Int) = api.uncollectInnerArticle(id)
}
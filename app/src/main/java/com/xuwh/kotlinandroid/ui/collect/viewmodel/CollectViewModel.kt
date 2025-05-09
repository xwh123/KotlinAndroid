package com.xuwh.kotlinandroid.ui.collect.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.enums.RefreshState
import com.xuwh.kotlinandroid.net.bean.CollectArticle
import com.xuwh.kotlinandroid.ui.collect.repository.CollectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.collect.viewmodel
 * @ClassName:      CollectViewModel
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/21 下午1:16
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/21 下午1:16
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@HiltViewModel
class CollectViewModel @Inject constructor(private val collectRepository: CollectRepository) :
    BaseViewModel() {

    private val _collectArticles = MutableLiveData<List<CollectArticle>>()

    val collectArticles = _collectArticles

    private var page = 0


    fun loadCollectArticles() {
        executeRequest(request = { collectRepository.queryCollectArticle(page) }, onSuccess = {response ->

            response?.datas?.let { newData ->

                _collectArticles.value=when{
                    0==page->newData.also {
                        setRefreshState(RefreshState.RefreshEnd) }
                    else->(_collectArticles.value?: emptyList())+newData.also { setRefreshState(RefreshState.LoadMoreEnd) }
                }
                setHasMore(page<response.pageCount)
            }?:run {
                // 处理空数据情况
                setRefreshState(if (page == 0) RefreshState.RefreshEnd else RefreshState.LoadMoreEnd)
                setHasMore(false)
            }
        })
    }

    fun dispatch(collectEvent: CollectEvent) {
        when (collectEvent) {
            is CollectEvent.Refresh -> refreshCollectArticles()

            is CollectEvent.LoadMore -> loadMoreCollectArticles()
        }
    }

    private fun refreshCollectArticles() {
        page = 0
        loadCollectArticles()
    }

    private  fun loadMoreCollectArticles() {
        page++
        loadCollectArticles()
    }
}

sealed class CollectEvent {
    object Refresh : CollectEvent()
    object LoadMore : CollectEvent()
}
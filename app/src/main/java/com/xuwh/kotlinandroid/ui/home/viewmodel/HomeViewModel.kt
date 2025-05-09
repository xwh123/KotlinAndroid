package com.xuwh.kotlinandroid.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.enums.RefreshState
import com.xuwh.kotlinandroid.event.Event
import com.xuwh.kotlinandroid.net.bean.Article
import com.xuwh.kotlinandroid.net.bean.Banner
import com.xuwh.kotlinandroid.ui.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.home.viewmodel
 * @ClassName:      HomeViewModel
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午2:11
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午2:11
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    BaseViewModel() {

    private val _bannerList = MutableLiveData<List<String>>()

    private val _homeEvent = MutableLiveData<Event<HomeEvent>>()

    val homeEvent =_homeEvent

    private val _homeArticleList = MutableLiveData<List<Article>>()

    val homeArticleList: LiveData<List<Article>> = _homeArticleList

    private var page = 0

    // 使用自定义 getter 获取 LiveData 的最新值
    val bannerList: List<String>
        get() = _bannerList.value ?: emptyList()

    init {
        getHomeBanner()
        getHomeArticles()

    }

    /**
     * 获取首页banner
     */
    private fun getHomeBanner() {
        executeRequest(request = homeRepository::getBanner,
            onSuccess = { banners ->
                _bannerList.value = banners.mapNotNull { it.imagePath }
                _homeEvent.value = Event(HomeEvent.BannerRefresh)
            })
    }

    fun dispatch(action: HomeAction) {
        when (action) {
            is HomeAction.ArticleRefresh -> refreshArticle()
            is HomeAction.ArticleLoadMore -> loadMoreArticle()
            is HomeAction.Coloect -> collectInnerArticle(action.id,action.position)
            is HomeAction.UnCollect -> unCollectInnerArticle(action.id,action.position)
        }
    }

    /**
     * 获取首页文章列表
     */
    private fun getHomeArticles(showLoading: Boolean = true) {
        executeRequest(
            showLoading = showLoading,
            request = { homeRepository.getHomeArticle(page) },
            onSuccess = { response ->

                response?.datas?.let { newData ->
                    _homeArticleList.value=when{
                        0==page->newData.also {
                            setRefreshState(RefreshState.RefreshEnd) }
                        else->(_homeArticleList.value?: emptyList())+newData.also { setRefreshState(RefreshState.LoadMoreEnd) }
                    }
                    setHasMore(page<response.pageCount)
                }?:run {
                    // 处理空数据情况
                    setRefreshState(if (page == 0) RefreshState.RefreshEnd else RefreshState.LoadMoreEnd)
                    setHasMore(false)
                }
            }
        )
    }

    private fun refreshArticle() {
        page = 0
        getHomeArticles(false)
    }

    private fun loadMoreArticle() {
        page++
        getHomeArticles(false)
    }

    /**
     * 收藏文章
     * @param id Int
     */
    private fun collectInnerArticle(id: Int,position:Int) {
        executeRequest(request = { homeRepository.collectInnerArticle(id) },
            onSuccess = { updateArticleCollectStatus(id, true,position) })

    }

    /**
     * 取消收藏文章
     * @param id Int
     */
    private fun unCollectInnerArticle(id: Int,position:Int) {
        executeRequest(request = { homeRepository.unCollectInnerArticle(id) },
            onSuccess = { updateArticleCollectStatus(id, false,position) })
    }

    // 新增更新收藏状态的方法
    private fun updateArticleCollectStatus(articleId: Int, isCollect: Boolean, position:Int) {
        val currentList = _homeArticleList.value?.toMutableList() ?: return
        currentList.replaceAll {
            if (it.id == articleId) it.copy(collect = isCollect) else it
        }
        _homeArticleList.value = currentList.toList()
    }

    sealed class HomeEvent {
        object BannerRefresh : HomeEvent()
    }

    sealed class HomeAction{
        object ArticleRefresh : HomeAction()
        object ArticleLoadMore : HomeAction()
        data class Coloect(val id: Int,val position:Int) : HomeAction()
        data class UnCollect(val id: Int,val position:Int) : HomeAction()
    }
}
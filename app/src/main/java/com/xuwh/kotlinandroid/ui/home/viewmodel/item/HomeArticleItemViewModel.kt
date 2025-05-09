package com.xuwh.kotlinandroid.ui.home.viewmodel.item

import androidx.lifecycle.MutableLiveData
import com.xuwh.kotlinandroid.base.viewmodel.BaseItemViewModel
import com.xuwh.kotlinandroid.ext.toDateTime
import com.xuwh.kotlinandroid.net.bean.Article

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.home.viewmodel.item
 * @ClassName:      HomeArticleItemViewModel
 * @Description:    首页文章item的viewmodel
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午5:52
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午5:52
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class HomeArticleItemViewModel : BaseItemViewModel<Article>() {

    val title = MutableLiveData<String?>()
    val isCollect = MutableLiveData<Boolean>()
    val author = MutableLiveData<String?>()
    val chapterName = MutableLiveData<String?>()
    val publishTime = MutableLiveData<String>()


    override fun setAllModel(t: Article) {
        title.value = t.title
        isCollect.value = t.collect
        author.value = t.author
        chapterName.value = t.chapterName
        publishTime.value = t.publishTime.toDateTime()
    }


}
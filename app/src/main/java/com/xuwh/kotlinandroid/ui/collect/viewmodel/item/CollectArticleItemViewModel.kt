package com.xuwh.kotlinandroid.ui.collect.viewmodel.item

import androidx.lifecycle.MutableLiveData
import com.xuwh.kotlinandroid.base.viewmodel.BaseItemViewModel
import com.xuwh.kotlinandroid.net.bean.CollectArticle

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.collect.viewmodel
 * @ClassName:      CollectArticleItemViewModel
 * @Description:    收藏文章Item的ViewModel
 * @Author:         xuwh
 * @CreateDate:     2025/3/21 下午2:47
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/21 下午2:47
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class CollectArticleItemViewModel:BaseItemViewModel<CollectArticle>() {

    val chapterName=MutableLiveData<String>()
    val niceDate=MutableLiveData<String>()
    val title=MutableLiveData<String>()

    override fun setAllModel(t: CollectArticle) {

        chapterName.value=t.chapterName
        niceDate.value=t.niceDate
        title.value=t.title
    }
}
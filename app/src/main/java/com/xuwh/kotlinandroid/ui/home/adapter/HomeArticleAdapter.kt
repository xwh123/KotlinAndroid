package com.xuwh.kotlinandroid.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.adapter.BaseAdapter
import com.xuwh.kotlinandroid.base.ext.bindCollectState
import com.xuwh.kotlinandroid.binding.BindingAdapters.setThrottleClick
import com.xuwh.kotlinandroid.net.bean.Article
import com.xuwh.kotlinandroid.ui.home.viewholder.HomeArticleItemViewHolder

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.home.adapter
 * @ClassName:      HomeArticleAdapter
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午6:11
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午6:11
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class HomeArticleAdapter(data: LiveData<List<Article>>) : BaseAdapter<Article,
        HomeArticleItemViewHolder>(data) {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.collect == newItem.collect
    }

    override fun getChangePayload(oldItem: Article, newItem: Article): Any? {
        return when {
            oldItem.collect != newItem.collect -> "collect_update"
            else -> null
        }
    }

    override fun onBindDataWithPayload(
        holder: HomeArticleItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val article = getItem(position)
        payloads.forEach { payload ->
            when (payload) {
                "collect_update" -> {
                    holder.mDataBinding.ivCollect.bindCollectState(article.collect)
                }
            }
        }

    }

    override fun onBindData(holder: HomeArticleItemViewHolder, position: Int) {
        val article = mDataList[position]

        holder.mDataBinding.apply {
            ivCollect.setThrottleClick {
                onCollectClick?.invoke(article.id, article.collect, position)
            }
            root.setThrottleClick {
                article.link?.let { url ->
                    onItemClick?.invoke(url)
                }
            }
        }
        holder.mDataBinding.viewModel?.setBaseItemViewModel(article)
    }

    override fun onBindViewHolder(
        holder: HomeArticleItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isNotEmpty() && payloads[0] == "partial_update") {
            // 局部更新逻辑，这里假设只更新 ivCollect 控件
            val article = mDataList[position]
            holder.mDataBinding.ivCollect.bindCollectState(article.collect)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeArticleItemViewHolder {
        return HomeArticleItemViewHolder(parent)
    }

    // 定义点击事件的 lambda 参数
    var onCollectClick: ((id: Int, isCollected: Boolean, position: Int) -> Unit)? = null
    var onItemClick: ((url: String) -> Unit)? = null

}
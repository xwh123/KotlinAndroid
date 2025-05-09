package com.xuwh.kotlinandroid.base.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.ext.load
import com.xuwh.kotlinandroid.ext.loadCircle

/**
 * DataBinding的自定义属性
 * @author LTP  2022/4/2
 */
@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    url?.let {
        load(url)
    }
}

/**
 * ImageView设置圆形图片
 * @author LTP  2022/4/2
 */
@BindingAdapter("circleImageUrl")
fun ImageView.setCircleImageUrl(url: String) {
    loadCircle(url)
}


@BindingAdapter("bindCollectState")
fun ImageView.bindCollectState(isCollected: Boolean) {
    setImageResource(
        if (isCollected) R.mipmap.icon_collected
        else R.mipmap.icon_un_collect
    )
}
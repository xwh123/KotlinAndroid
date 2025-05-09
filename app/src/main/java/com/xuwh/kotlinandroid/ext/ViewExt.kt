package com.xuwh.kotlinandroid.ext

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.xuwh.kotlinandroid.utils.StatusBarUtil


/**
 * ImageView利用Glide加载图片
 * @param url 图片url（可远程可本地）
 * @param showPlaceholder 是否展示placeholder，默认为true
 */
fun ImageView.load(url: String, showPlaceholder: Boolean = true) {
    if (showPlaceholder) {
        Glide.with(context).load(url)
//            .placeholder(R.drawable.ic_default_img)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(this)
    } else {
        Glide.with(context).load(url)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(this)
    }
}

/**
 * ImageView利用Glide加载图片
 * @param resourceId 本地图片资源Id
 * @param showPlaceholder 是否展示placeholder，默认为false
 */
fun ImageView.load(@DrawableRes resourceId: Int, showPlaceholder: Boolean = false) {
    // 之所以不添加withCrossFade渐变效果，是由于SplashBannerAdapter启动加载本地大图会出现滑动时图片闪烁
    if (showPlaceholder) {
        Glide.with(context).load(resourceId)
//            .placeholder(R.drawable.ic_default_img)
            .into(this)
    } else {
        Glide.with(context).load(resourceId)
            .into(this)
    }
}

/**
 * ImageView利用Glide加载圆形图片
 * @param url 图片url（可远程可本地）
 */
fun ImageView.loadCircle(url: String) {
    Glide.with(context).load(url)
//        .placeholder(R.drawable.ic_default_img)
        .apply(RequestOptions.bitmapTransform(CircleCrop()))
        .transition(DrawableTransitionOptions.withCrossFade(500))
        .into(this)
}

/**
 * ImageView利用Glide加载圆角图片
 * @receiver ImageView
 * @param url String
 * @param roundRadius Int
 */
fun ImageView.loadRounded (url: String,roundRadius:Int){

    Glide.with(context).asBitmap()
        .load(url)
        .transform(RoundedCorners(roundRadius))
        .into(this)


}

// 扩展函数方式封装
 fun View.updateStatusBarPadding(context: Context = this.context) {
    val statusBarHeight = StatusBarUtil.getStatusBarHeight(context)
    setPadding(paddingLeft, statusBarHeight, paddingRight, paddingBottom)
}

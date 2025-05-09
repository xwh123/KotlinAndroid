package com.xuwh.kotlinandroid.net.bean

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.net.bean
 * @ClassName:      Structure
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午1:48
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午1:48
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@Parcelize
data class Structure(
    val author: String,
    val children: List<Children>,
    val courseId: Int,
    val cover: String,
    val desc: String,
    val id: Int,
    val lisense: String,
    val lisenseLink: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val type: Int,
    val userControlSetTop: Boolean,
    val visible: Int
):Parcelable

@Parcelize
data class Children(
    val author: String,
    val courseId: Int,
    val cover: String,
    val desc: String,
    val id: Int,
    val lisense: String,
    val lisenseLink: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val type: Int,
    val userControlSetTop: Boolean,
    val visible: Int
):Parcelable
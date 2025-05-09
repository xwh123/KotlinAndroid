package com.xuwh.kotlinandroid.ext

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexLine
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.xuwh.kotlinandroid.itemdecoration.FlowLayoutItemDecoration

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ext
 * @ClassName:      RecyclerViewExt
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午4:48
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午4:48
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
/**
 * RecyclerView 扩展函数，设置为垂直方向布局
 * @receiver RecyclerView
 * @param block [@kotlin.ExtensionFunctionType] Function1<LinearLayoutManager, Unit>
 */
fun RecyclerView.verticalLayout(block: LinearLayoutManager.() -> Unit = {}) {
    layoutManager = LinearLayoutManager(context).apply {
        orientation = RecyclerView.VERTICAL
        block()
    }
}

/**
 * RecyclerView 扩展函数，设置为横向方向布局
 * @receiver RecyclerView
 * @param block [@kotlin.ExtensionFunctionType] Function1<LinearLayoutManager, Unit>
 */
fun RecyclerView.horizontalLayout(block: LinearLayoutManager.() -> Unit = {}) {
    layoutManager = LinearLayoutManager(context).apply {
        orientation = RecyclerView.HORIZONTAL
        block()
    }
}


/**
 * 流式布局扩展函数
 * @receiver RecyclerView
 * @param spanCount Int 每行/列最大显示数量（根据方向自动计算）
 * @param orientation 布局方向（默认横向排列）
 * @param spacing 项间距（单位：px）
 * @param lineSpacing 行间距（单位：px）
 * @param justifyContent 对齐方式
 * @param config 额外的Flexbox配置
 * 示例：recyclerView.flowLayout(
 *     spanCount = 4, // 每行最多4个元素
 *     spacing = 16.px, // 项间距16像素
 *     lineSpacing = 8.px // 行间距8像素
 * )
 *
 * recyclerView.flowLayout(
 *     orientation = FlexDirection.COLUMN,
 *     spanCount = 3, // 每列最多3个元素
 *     spacing = 8.px,
 *     lineSpacing = 16.px
 * )
 *
 * // 在res/values/dimens.xml中定义
 * <dimen name="item_spacing">8dp</dimen>
 * <dimen name="line_spacing">16dp</dimen>
 *
 * // 代码中使用
 * recyclerView.flowLayoutDp(
 *     lineSpacingRes = R.dimen.line_spacing,
 *     itemSpacingRes = R.dimen.item_spacing
 * )
 *
 * // 横向+两端对齐
 * recyclerView.flowLayout(
 *     justifyContent = JustifyContent.SPACE_BETWEEN,
 *     alignItems = AlignItems.CENTER
 * )
 *
 * // 纵向+居中对齐
 * recyclerView.flowLayout(
 *     orientation = FlexDirection.COLUMN,
 *     justifyContent = JustifyContent.CENTER
 * )
 *
 * recyclerView.flowLayout(config = {
 *     // 设置最大行数（需要自定义FlexboxLayoutManager）
 *     maxLine = 3
 *     // 设置元素伸缩比例
 *     (getFlexItemAt(0)?.layoutParams as? FlexboxLayoutManager.LayoutParams)?.flexGrow = 1.0f
 * })
 *
 */
fun RecyclerView.flowLayout(
    orientation: Int = FlexDirection.ROW,
    spacing: Int = 0,
    lineSpacing: Int = 0,
    justifyContent: Int = JustifyContent.FLEX_START,
    alignItems: Int = AlignItems.STRETCH,
    config: FlexboxLayoutManager.() -> Unit = {}
) {
    // 初始化Flexbox布局管理器
    layoutManager = FlexboxLayoutManager(context).apply {
        flexDirection = orientation
        flexWrap = FlexWrap.WRAP
        this.justifyContent = justifyContent
        this.alignItems = alignItems
        config()
    }

    // 添加间距装饰（修正参数名称）
    addItemDecoration(
        FlowLayoutItemDecoration(
            horizontalSpace = spacing,
            verticalSpace = lineSpacing,
            flexDirection = orientation
        )
    )
}

/**
 * 带资源ID参数的版本
 */
fun RecyclerView.flowLayoutDp(
    @DimenRes lineSpacingRes: Int,
    @DimenRes itemSpacingRes: Int,
    orientation: Int = FlexDirection.ROW,
    justifyContent: Int = JustifyContent.FLEX_START,
    alignItems: Int = AlignItems.STRETCH,
    config: FlexboxLayoutManager.() -> Unit = {}
) {
    val lineSpacing = context.resources.getDimensionPixelSize(lineSpacingRes)
    val itemSpacing = context.resources.getDimensionPixelSize(itemSpacingRes)
    // 使用命名参数修正调用顺序
    flowLayout(
        orientation = orientation,
        justifyContent = justifyContent,
        alignItems = alignItems,
        spacing = itemSpacing,
        lineSpacing = lineSpacing,
        config = config
    )
}



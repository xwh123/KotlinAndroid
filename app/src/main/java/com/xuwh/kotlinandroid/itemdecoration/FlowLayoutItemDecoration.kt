package com.xuwh.kotlinandroid.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.itemdecoration
 * @ClassName:      FlowLayoutItemDecoration
 * @Description:    流式布局的ItemDecoration
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午3:57
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午3:57
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class FlowLayoutItemDecoration ( private val horizontalSpace: Int,
                                 private val verticalSpace: Int,
                                 private val flexDirection: Int = FlexDirection.ROW) : RecyclerView
                                     .ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val layoutManager = parent.layoutManager as? FlexboxLayoutManager ?: return

        val itemCount = state.itemCount

        var currentFlexLineIndex = 0
        var previousPositionValue = Int.MIN_VALUE
        val isRowDirection = flexDirection == FlexDirection.ROW || flexDirection == FlexDirection.ROW_REVERSE

        for (i in 0 until position) {
            val child = layoutManager.findViewByPosition(i) ?: continue
            val currentPositionValue = if (isRowDirection) child.bottom else child.right
            if (currentPositionValue > previousPositionValue) {
                currentFlexLineIndex++
                previousPositionValue = currentPositionValue
            }
        }

        // 设置间隔
        if (isRowDirection) {
            // 行方向
            outRect.left = horizontalSpace
            outRect.right = horizontalSpace
            if (currentFlexLineIndex == 0) {
                outRect.top = verticalSpace
            }
            outRect.bottom = verticalSpace
        } else {
            // 列方向
            outRect.top = verticalSpace
            outRect.bottom = verticalSpace
            if (currentFlexLineIndex == 0) {
                outRect.left = horizontalSpace
            }
            outRect.right = horizontalSpace
        }

        // 计算最后一行/列的索引
        var lastFlexLineIndex = 0
        previousPositionValue = Int.MIN_VALUE
        for (i in 0 until itemCount) {
            val child = layoutManager.findViewByPosition(i) ?: continue
            val currentPositionValue = if (isRowDirection) child.bottom else child.right
            if (currentPositionValue > previousPositionValue) {
                lastFlexLineIndex++
                previousPositionValue = currentPositionValue
            }
        }
        lastFlexLineIndex--

        // 如果是最后一行/列，需要调整底部/右侧间隔
        if (currentFlexLineIndex == lastFlexLineIndex) {
            if (isRowDirection) {
                outRect.bottom = verticalSpace * 2
            } else {
                outRect.right = horizontalSpace * 2
            }
        }
    }
}
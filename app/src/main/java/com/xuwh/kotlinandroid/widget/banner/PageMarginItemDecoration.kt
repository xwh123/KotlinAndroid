package com.xuwh.kotlinandroid.widget.banner

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.xuwh.kotlinandroid.ext.dp

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.widget.banner
 * @ClassName:      PageMarginItemDecoration
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/19 下午2:57
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/19 下午2:57
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class PageMarginItemDecoration (private val spacing: Int): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        when (position) {
            0 -> outRect.left = spacing
            itemCount - 1 -> outRect.right = spacing
            else -> {
                outRect.left = spacing / 2
                outRect.right = spacing / 2
            }
        }
    }

}
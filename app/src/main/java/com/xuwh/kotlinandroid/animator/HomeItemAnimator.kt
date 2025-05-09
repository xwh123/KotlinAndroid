package com.xuwh.kotlinandroid.animator

import android.animation.ObjectAnimator
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.animator
 * @ClassName:      HomeItemAnimator
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/19 下午7:26
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/19 下午7:26
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class HomeItemAnimator : DefaultItemAnimator() {

    private val animatedItems = mutableSetOf<Int>()

    override fun animateAppearance(
        viewHolder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo?,
        postLayoutInfo: ItemHolderInfo
    ): Boolean {

        if (null == preLayoutInfo || -1 == preLayoutInfo.left) {
            return false
        }

        return super.animateAppearance(viewHolder, preLayoutInfo, postLayoutInfo)
    }
    fun setupAnimation(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager
                if (layoutManager is LinearLayoutManager){
                    val firstVisibleItemPosition = layoutManager?.findFirstVisibleItemPosition() ?: 0
                    val lastVisibleItemPosition = layoutManager?.findLastVisibleItemPosition() ?: 0

                    for (position in firstVisibleItemPosition..lastVisibleItemPosition) {
                        if (!animatedItems.contains(position)) {
                            val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                            viewHolder?.let {
                                startAnimation(it)
                                animatedItems.add(position)
                            }
                        }
                    }
                }
            }
        })
    }

    private fun startAnimation(viewHolder: RecyclerView.ViewHolder) {
        // 获取屏幕宽度（用于设置初始偏移）
        val screenWidth = viewHolder.itemView.context.resources.displayMetrics.widthPixels

        // 设置初始位置：将 item 放在右侧屏幕外
        viewHolder.itemView.translationX = screenWidth.toFloat()

        // 创建平移动画：从右侧滑入到原始位置
        val animator = ObjectAnimator.ofFloat(
            viewHolder.itemView,
            View.TRANSLATION_X,
            screenWidth.toFloat(),
            0f
        ).apply {
            duration = 500 // 动画时长
            // 根据 position 设置延迟（每个 item 依次延迟 100ms）
            startDelay = viewHolder.adapterPosition * 50L
        }

        animator.start()
    }
}
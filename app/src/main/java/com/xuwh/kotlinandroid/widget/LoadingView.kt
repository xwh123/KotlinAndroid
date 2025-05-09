package com.xuwh.kotlinandroid.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 *
 * @ProjectName:    BaseKotlin
 * @Package:        com.xuwh.kotlinandroid.widget
 * @ClassName:      LoadingDialog
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/11 下午8:04
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/11 下午8:04
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class LoadingView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var width = 0


    private var height = 0


    private var widthRect = 0


    private var heigheRect = 0


    private var rectPaint: Paint? = null


    private var pos = 0

    private var mRectF: RectF? = null

    private val color = arrayOf("#bbbbbb", "#aaaaaa", "#999999", "#888888", "#777777", "#666666")

    init {
        init()
    }

    private fun init() {
        rectPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST) {
            width = 80
        } else {
            width = MeasureSpec.getSize(widthMeasureSpec)
            height = MeasureSpec.getSize(heightMeasureSpec)
            width = Math.min(width, height)
        }
        widthRect = width / 12
        heigheRect = 4 * widthRect
        setMeasuredDimension(width, width)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (mRectF == null) {
            mRectF = RectF(
                ((width - widthRect) / 2).toFloat(),
                0f,
                ((width + widthRect) / 2).toFloat(),
                heigheRect.toFloat()
            )
        }

        for (i in 0..11) {
            if (i - pos >= 5) {
                rectPaint!!.color = Color.parseColor(color[5])
            } else if (i - pos >= 0 && i - pos < 5) {
                rectPaint!!.color = Color.parseColor(color[i - pos])
            } else if (i - pos >= -7 && i - pos < 0) {
                rectPaint!!.color = Color.parseColor(color[5])
            } else if (i - pos >= -11 && i - pos < -7) {
                rectPaint!!.color = Color.parseColor(color[12 + i - pos])
            }
            canvas.drawRoundRect(mRectF!!, 20f, 20f, rectPaint!!)
            canvas.rotate(30f, (width / 2).toFloat(), (width / 2).toFloat())
        }
        pos++
        if (pos > 11) {
            pos = 0
        }
        postInvalidateDelayed(100)
    }
}
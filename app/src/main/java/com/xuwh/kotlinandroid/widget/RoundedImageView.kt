package com.xuwh.kotlinandroid.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.ext.loadRounded

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.widget
 * @ClassName:      RoundedImageView
 * @Description:    圆角图片控件
 * @Author:         xuwh
 * @CreateDate:     2025/3/19 下午5:03
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/19 下午5:03
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class RoundedImageView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AppCompatImageView(context, attrs, defStyleAttr) {
    private val path = Path()
    private var radius = 20f

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.RoundedImageView)
            radius = typedArray.getDimension(R.styleable.RoundedImageView_radius, radius)
            typedArray.recycle()
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            setLayerType(LAYER_TYPE_SOFTWARE, null)
        }
    }

    override fun onDraw(canvas: Canvas) {
        path.reset()
        path.addRoundRect(0f, 0f, width.toFloat(), height.toFloat(), radius, radius, Path.Direction.CW)
        canvas.clipPath(path)
        super.onDraw(canvas)
    }

    fun setImageLoadUrl(url: String) {
        loadRounded(url, radius.toInt())
    }
}
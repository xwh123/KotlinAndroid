package com.xuwh.kotlinandroid.widget.banner

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginLeft
import androidx.core.view.marginStart
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.ext.dp
import java.util.Timer
import java.util.TimerTask

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.widget.banner
 * @ClassName:      BannerView
 * @Description:    自定义banner
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午10:07
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午10:07
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class BannerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), DefaultLifecycleObserver {

    // 点击监听接口
    private var itemClickListener: OnBannerItemClickListener? = null

    // 在现有代码中添加点击监听接口定义
    interface OnBannerItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnBannerItemClickListener(listener: OnBannerItemClickListener) {
        this.itemClickListener = listener
    }

    // 尺寸参数配置
    private val SIDE_PADDING_DP = 60f // 两侧可见区域
    private val PAGE_SPACING_DP = 8f   // 页面间距
    private val AUTO_PLAY_INTERVAL = 5000L

    private lateinit var viewPager: ViewPager2
    private lateinit var indicatorLayout: LinearLayout
    private var bannerAdapter: BannerAdapter? = null
    private var timer: Timer? = null
    private val handler = Handler(Looper.getMainLooper())

    private var imageList = mutableListOf<String>()

    private var gestureDetector: GestureDetector

    init {
        initView()
        gestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onDown(e: MotionEvent): Boolean {
                    stopAutoPlay()
                    return true
                }

                override fun onFling(
                    e1: MotionEvent?,
                    e2: MotionEvent,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    // 滑动结束后立即重启自动播放
                    handler.post { startAutoPlay() }
                    return super.onFling(e1, e2, velocityX, velocityY)
                }
            })
    }

    private fun initView() {
        val view = LayoutInflater.from(context).inflate(R.layout.view_banner, this, true)
        viewPager = view.findViewById(R.id.viewPager)
        indicatorLayout = view.findViewById(R.id.indicatorLayout)

        // 关键布局参数设置
        viewPager.apply {

            // 设置布局参数
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            )

            // 正确设置RecyclerView参数
            (getChildAt(0) as? RecyclerView)?.apply {
                // 确保RecyclerView填充父容器
                layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
                val padding = SIDE_PADDING_DP.dp()
                setPadding(padding, 0, padding, 0)
                clipToPadding = false
                clipChildren = false

                addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
                    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                        gestureDetector.onTouchEvent(e)
                        return false
                    }
                })
            }

            offscreenPageLimit = 2
            addItemDecoration(PageMarginItemDecoration(PAGE_SPACING_DP.dp()))
            setPageTransformer(ZoomPageTransformer())
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateIndicator(position)
            }
        })
    }

    fun setImageList(imageList: List<String>) {
        this.imageList.clear()
        this.imageList.addAll(imageList)
        bannerAdapter = BannerAdapter(imageList).apply {
            setOnItemClickListener(object : BannerAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    itemClickListener?.onItemClick(position % imageList.size)
                    // 点击后延迟启动（保持轮播节奏）
                    handler.postDelayed({ startAutoPlay() }, AUTO_PLAY_INTERVAL)
                }
            })
        }
        viewPager.adapter = bannerAdapter
        setupIndicator()
        startAutoPlay()
    }

    private fun setupIndicator() {
        indicatorLayout.removeAllViews()
        val count = imageList.size
        for (i in 0 until count) {
            val indicator = TextView(context)
            indicator.text = "●"
            indicator.textSize = 12f
            indicator.setTextColor(if (i == 0) Color.GREEN else Color.GRAY)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            indicatorLayout.addView(indicator, params)
        }
    }

    private fun updateIndicator(position: Int) {
        val count = indicatorLayout.childCount
        for (i in 0 until count) {
            val indicator = indicatorLayout.getChildAt(i) as TextView
            indicator.setTextColor(if (i == position % imageList.size) Color.GREEN else Color.GRAY)
        }
    }

    private fun startAutoPlay() {
        timer?.cancel()
        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                handler.post {
                    val currentItem = viewPager.currentItem
                    viewPager.setCurrentItem(currentItem + 1, true)
                }
            }
        }, AUTO_PLAY_INTERVAL, AUTO_PLAY_INTERVAL)
    }

    private fun stopAutoPlay() {
        timer?.cancel()
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        startAutoPlay()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        stopAutoPlay()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewPager.adapter = null  // 防止内存泄漏
        stopAutoPlay()
    }
}
package com.xuwh.kotlinandroid.widget.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.ext.setImageUrl
import com.xuwh.kotlinandroid.ext.loadRounded
import com.xuwh.kotlinandroid.widget.RoundedImageView

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.widget
 * @ClassName:      BannerAdapter
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午9:38
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午9:38
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
class BannerAdapter(private val items: List<String>) : RecyclerView.Adapter<BannerAdapter
.BannerViewHolder>() {

    // 适配器自己的点击监听接口
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BannerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        val holder = BannerViewHolder(view)
        view.setOnClickListener {
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemClickListener?.onItemClick(position)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val realPosition = position % items.size
        holder.imageView.setImageLoadUrl(items[realPosition])
    }

    override fun getItemCount() = if (items.size > 1) Int.MAX_VALUE else items.size

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: RoundedImageView = itemView.findViewById(R.id.iv_banner)
    }
}
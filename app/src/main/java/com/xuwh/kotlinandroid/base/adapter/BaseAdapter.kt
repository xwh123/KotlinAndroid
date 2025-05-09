package com.xuwh.kotlinandroid.base.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.xuwh.kotlinandroid.base.viewholder.BaseViewHolder
import javax.inject.Inject

/**
 *
 * @ProjectName:    BaseKotlin
 * @Package:        com.xuwh.kotlinandroid.base.adapter
 * @ClassName:      BaseAdapter
 * @Description:    recycleview的adapter基类
 * @Author:         xuwh
 * @CreateDate:     2025/3/12 下午2:41
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/12 下午2:41
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class BaseAdapter<T, VH : BaseViewHolder<*, *>>(
    private val
    dataList: LiveData<List<T>>
) : Adapter<VH>() {

    protected var mDataList: List<T> = emptyList()

    private var oldDataList: List<T> = emptyList()


    /**
     * 可选实现：判断是否是同一个Item
     * 默认使用全等比较（===）
     */
    open fun areItemsTheSame(oldItem: T, newItem: T): Boolean{
        return oldItem === newItem
    }
    /**
     * 可选实现：判断内容是否相同
     */
    open fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem === newItem
    }

    /**
     * 可选实现：获取变化负载
     * 默认返回null（表示没有局部更新）
     */
    open fun getChangePayload(oldItem: T, newItem: T): Any? {
        return null
    }

    private val observer = Observer<List<T>> { newDataList ->
        updateData(newDataList)
    }

    //recycleview 初始化绑定observer
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        dataList.observeForever(observer)
        dataList.value?.let {
            updateData(it)
        }
    }

    //分离时解绑observer
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        dataList.removeObserver(observer)

    }

    private fun updateData(newDataList: List<T>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldDataList.size

            override fun getNewListSize(): Int = newDataList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return areItemsTheSame(oldDataList[oldItemPosition], newDataList[newItemPosition])
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return areContentsTheSame(oldDataList[oldItemPosition], newDataList[newItemPosition])
            }

            override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
                return getChangePayload(oldDataList[oldItemPosition],newDataList[newItemPosition])
            }
        })

        oldDataList = newDataList.toList()
        mDataList = newDataList
        diffResult.dispatchUpdatesTo(this)
    }

    // 抽象方法，让子类实现具体的数据绑定逻辑
    abstract fun onBindData(holder: VH, position: Int)
    /**
     * 可选实现：带负载的数据绑定
     * 默认调用普通绑定方法
     */
    open fun onBindDataWithPayload(holder: VH, position: Int, payloads: MutableList<Any>) {
        onBindData(holder, position)
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            onBindDataWithPayload(holder, position, payloads)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindData(holder, position)
        holder.mDataBinding.executePendingBindings()
        holder.mDataBinding.invalidateAll()
    }

    override fun getItemCount(): Int = mDataList.size

    fun getItem(position: Int): T = mDataList[position]

    fun getDataList(): List<T> = mDataList
}
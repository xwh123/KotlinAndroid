package com.xuwh.kotlinandroid.base.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.xuwh.kotlinandroid.base.viewmodel.BaseItemViewModel
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject

/**
 *
 * @ProjectName:    BaseKotlin
 * @Package:        com.xuwh.kotlinandroid.base.viewholder
 * @ClassName:      BaseViewHolder
 * @Description:    ViewHolder基类
 * @Author:         xuwh
 * @CreateDate:     2025/3/12 下午2:33
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/12 下午2:33
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class BaseViewHolder<out DB : ViewDataBinding, VM : BaseItemViewModel<*>>(
    parent: ViewGroup,
    @LayoutRes layoutId: Int
) :RecyclerView.ViewHolder(DataBindingUtil.inflate<DB>(
        LayoutInflater.from(parent.context),
        layoutId,
        parent,
        false
    ).also { it.lifecycleOwner = parent.context as? androidx.lifecycle.LifecycleOwner }.root) {
    val mDataBinding: DB = DataBindingUtil.getBinding(itemView)!!
    protected lateinit var mViewModel: VM

    init {
    check(mDataBinding.root==itemView){"DataBinding root must match itemView"}
        initViewModel()
        bindViewModel()
    }

    fun getParent(): ViewParent = itemView.parent as ViewParent
    fun getViewModel(): VM = mViewModel

    protected abstract fun initViewModel()

    protected abstract fun bindViewModel()

    protected abstract fun init()

}
package com.xuwh.kotlinandroid.ui.mine.view

import android.app.Application
import android.content.Intent
import android.os.Process
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.fragment.BaseLazyFragment
import com.xuwh.kotlinandroid.databinding.FragmentMineBinding
import com.xuwh.kotlinandroid.ui.home.viewmodel.HomeViewModel
import com.xuwh.kotlinandroid.ui.mine.viewmodel.MineEvent
import com.xuwh.kotlinandroid.ui.mine.viewmodel.MineViewModel
import com.xuwh.kotlinandroid.utils.ToastUtils
import com.xuwh.kotlinandroid.widget.banner.BannerView
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.Contexts.getApplication

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.mine.view
 * @ClassName:      MineFragment
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午9:03
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午9:03
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@AndroidEntryPoint
class MineFragment : BaseLazyFragment<FragmentMineBinding, MineViewModel>() {
    override val viewModel : MineViewModel by viewModels()
    override val layoutId = R.layout.fragment_mine

    override fun bindViewModel() {
       binding.viewModel = viewModel
    }

    override fun lazyLoad() {

        viewModel.loginOutEvent.observe(this) { event ->

            event?.getContentIfNotHandled()?.let { result ->

                when (result) {
                    is MineEvent.LoginOutEvent -> {
                        // 重启应用核心代码
                        val intent = requireContext().packageManager
                            .getLaunchIntentForPackage(requireContext().packageName)
                        intent?.apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(this)
                        }
                       Process.killProcess(Process.myPid())
                    }
                }
            }
        }
    }

}
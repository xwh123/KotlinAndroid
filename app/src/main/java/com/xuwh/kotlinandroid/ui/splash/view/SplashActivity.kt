package com.xuwh.kotlinandroid.ui.splash.view

import androidx.activity.viewModels
import com.alibaba.android.arouter.launcher.ARouter
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.activity.BaseActivity
import com.xuwh.kotlinandroid.base.constant.ArouterConstants
import com.xuwh.kotlinandroid.databinding.ActivitySplashBinding
import com.xuwh.kotlinandroid.ui.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.splash
 * @ClassName:      SplashActivity
 * @Description:    欢迎页面
 * @Author:         xuwh
 * @CreateDate:     2025/3/15 下午4:28
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/15 下午4:28
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override val layoutId = R.layout.activity_splash
    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun initData() {

        viewModel.navigationEvent.observe(this) { event ->
            event?.getContentIfNotHandled()?.let { navEvent ->
                when (navEvent) {
                    is SplashViewModel.NavigationEvent.NavigatePath -> {
                        ARouter.getInstance().build(navEvent.path).navigation()
                        finish()
                    }
                }
            }

        }

    }

}
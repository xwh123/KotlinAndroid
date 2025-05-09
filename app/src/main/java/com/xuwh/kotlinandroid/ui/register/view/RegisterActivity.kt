package com.xuwh.kotlinandroid.ui.register.view

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.activity.BaseActivity
import com.xuwh.kotlinandroid.base.constant.ArouterConstants
import com.xuwh.kotlinandroid.databinding.ActivityRegisterBinding
import com.xuwh.kotlinandroid.ui.register.viewmodel.RegisterViewModel
import com.xuwh.kotlinandroid.utils.ToastUtils
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.register.view
 * @ClassName:      RegisterActivity
 * @Description:    注册
 * @Author:         xuwh
 * @CreateDate:     2025/3/17 下午4:10
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/17 下午4:10
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@Route(path = ArouterConstants.REGISTER)
@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>() {
    override val viewModel: RegisterViewModel by viewModels()
    override val layoutId = R.layout.activity_register

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun initData() {

        viewModel.registerResult.observe(this) { event ->
            event?.getContentIfNotHandled()?.let { result ->
                when (result) {
                    is RegisterViewModel.RegisterResult.Success -> {
                        ToastUtils.show("注册成功")
                        goLogin()
                    }

                    is RegisterViewModel.RegisterResult.Error -> {
                        ToastUtils.show(result.message)
                    }
                }

            }
        }

        viewModel.navigationEvent.observe(this){
           goLogin()
        }
    }

    fun goLogin(){
        finish()
    }
}
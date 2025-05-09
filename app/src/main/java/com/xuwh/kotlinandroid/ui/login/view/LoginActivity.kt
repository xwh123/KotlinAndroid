package com.xuwh.kotlinandroid.ui.login.view

import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.activity.BaseActivity
import com.xuwh.kotlinandroid.base.constant.ArouterConstants
import com.xuwh.kotlinandroid.databinding.ActivityLoginBinding
import com.xuwh.kotlinandroid.ui.login.viewmodel.LoginViewModel
import com.xuwh.kotlinandroid.utils.ToastUtils
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.login.view
 * @ClassName:      LoginActivity
 * @Description:    登录页面
 * @Author:         xuwh
 * @CreateDate:     2025/3/16 下午3:57
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/16 下午3:57
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@Route(path = ArouterConstants.LOGIN)
@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val viewModel: LoginViewModel by viewModels()
    override val layoutId = R.layout.activity_login

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun initData() {

        viewModel.loginResult.observe(this) { event ->
            event?.getContentIfNotHandled()?.let { result ->
                when (result) {
                    is LoginViewModel.LoginResult.LoginSuccess -> {
                        ToastUtils.show("登录成功")
                        ARouter.getInstance().build(ArouterConstants.MAIN).navigation()
                        finish()
                    }

                    is LoginViewModel.LoginResult.Error ->
                        ToastUtils.show(result.errorMsg)
                }
            }
        }

        viewModel.navigationEvent.observe(this) {
            ARouter.getInstance().build(ArouterConstants.REGISTER).navigation()
        }
    }

}
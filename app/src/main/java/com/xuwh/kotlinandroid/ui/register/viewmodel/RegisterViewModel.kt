package com.xuwh.kotlinandroid.ui.register.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.xuwh.kotlinandroid.base.constant.ArouterConstants
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.event.Event
import com.xuwh.kotlinandroid.ui.register.repository.RegisterRepository
import com.xuwh.kotlinandroid.utils.ToastUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.register.viewmodel
 * @ClassName:      RegisterViewModel
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/17 下午4:10
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/17 下午4:10
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerRepository: RegisterRepository) :
    BaseViewModel() {

    private val _registerResult = MutableLiveData<Event<RegisterResult>>()
    val registerResult = _registerResult


    private val _navigationEvent = MutableLiveData<Event<NavigationEvent>>()
    val navigationEvent = _navigationEvent

    fun register(account: String = "", password: String = "", repassword: String = "") {
        when {
            account.isBlank() -> _registerResult.value = Event(RegisterResult.Error("账号不能为空"))
            password.isBlank() -> _registerResult.value =
                Event(RegisterResult.Error("密码不能为空"))

            repassword.isBlank() -> _registerResult.value =
                Event(RegisterResult.Error("密码不能为空"))

            repassword != password -> _registerResult.value =
                Event(RegisterResult.Error("密码不一致"))

            else -> executeRequest(
                request = { registerRepository.register(account, password, repassword) },
                onSuccess = { _registerResult.value = Event(RegisterResult.Success) },
            )
        }
    }

    fun goLogin() {
        _navigationEvent.value = Event(NavigationEvent.NavigateToLogin)
    }

    sealed class RegisterResult {
        object Success : RegisterResult()
        data class Error(val message: String) : RegisterResult()
    }

    sealed class NavigationEvent {
        object NavigateToLogin : NavigationEvent()
    }
}
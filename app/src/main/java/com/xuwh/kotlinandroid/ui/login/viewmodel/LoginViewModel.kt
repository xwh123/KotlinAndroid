package com.xuwh.kotlinandroid.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import com.xuwh.kotlinandroid.base.constant.SpConstants
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.event.Event
import com.xuwh.kotlinandroid.ui.mine.repository.MineRepository
import com.xuwh.kotlinandroid.utils.SpUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.login.viewmodel
 * @ClassName:      LoginViewModel
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/16 下午3:57
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/16 下午3:57
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: MineRepository) :
    BaseViewModel() {

    private val _loginResult = MutableLiveData<Event<LoginResult>>()
    val loginResult = _loginResult

    private val _navigationEvent = MutableLiveData<Event<NavigationEvent>>()
    val navigationEvent = _navigationEvent

    fun login(account: String? = null, password: String? = null) {

        //takeUnless 根据条件判断返回对象本身或null  如果为空执行run代码块中的逻辑
        val validateAccount = account.takeUnless { it.isNullOrEmpty() } ?: run {
            _loginResult.value = Event(LoginResult.Error("账号不能为空"))
            return
        }

        val validatePassword=password.takeUnless { it.isNullOrEmpty() }?:run {
            _loginResult.value = Event(LoginResult.Error("密码不能为空"))
            return
        }

        executeRequest(
            request = { loginRepository.login(validateAccount, validatePassword) },
            onSuccess = { user ->
                SpUtils.apply {
                    setParameter(SpConstants.IS_LOGIN, true)
                    setParameter(SpConstants.USER_NAME, user.nickname)
                    setParameter(SpConstants.COIN_COUNT, user.coinCount)
                }
                _loginResult.value = Event(LoginResult.LoginSuccess)
            },
        )
    }

    fun register() {
        _navigationEvent.value = Event(NavigationEvent.NavigateToRegister)
    }

    sealed class LoginResult {
        object LoginSuccess : LoginResult()
        data class Error(val errorMsg: String) : LoginResult()
    }

    sealed class NavigationEvent {
        object NavigateToRegister : NavigationEvent()
    }
}
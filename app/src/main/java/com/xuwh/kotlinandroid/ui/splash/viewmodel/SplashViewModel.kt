package com.xuwh.kotlinandroid.ui.splash.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.alibaba.android.arouter.launcher.ARouter
import com.xuwh.kotlinandroid.base.constant.ArouterConstants
import com.xuwh.kotlinandroid.base.constant.SpConstants
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.enums.LoadState
import com.xuwh.kotlinandroid.event.Event
import com.xuwh.kotlinandroid.ui.register.viewmodel.RegisterViewModel.NavigationEvent
import com.xuwh.kotlinandroid.utils.SpUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.splash.viewmodel
 * @ClassName:      SplashViewModel
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/15 下午4:38
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/15 下午4:38
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel() {

    private val _navigationEvent = MutableLiveData<Event<NavigationEvent>>()
    val navigationEvent: LiveData<Event<NavigationEvent>> = _navigationEvent

    init {
        viewModelScope.launch {
            delay(3000)
            val isLogin = SpUtils.getParameter(SpConstants.IS_LOGIN,false)

            val navPath =if (isLogin){
                ArouterConstants.MAIN
            }else{
                ArouterConstants.LOGIN
            }
            _navigationEvent.value=Event(NavigationEvent.NavigatePath(navPath))
        }
    }

    sealed class NavigationEvent {
        data class NavigatePath(val path:String) : NavigationEvent()
    }

}
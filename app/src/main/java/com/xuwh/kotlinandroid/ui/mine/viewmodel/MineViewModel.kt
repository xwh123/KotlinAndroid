package com.xuwh.kotlinandroid.ui.mine.viewmodel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.event.Event
import com.xuwh.kotlinandroid.ui.home.viewmodel.HomeViewModel.HomeEvent
import com.xuwh.kotlinandroid.ui.mine.repository.MineRepository
import com.xuwh.kotlinandroid.utils.SpUtils
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.mine.viewmodel
 * @ClassName:      MineViewModel
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/18 下午9:04
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/18 下午9:04
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@HiltViewModel
class MineViewModel @Inject constructor(private val mineRepository: MineRepository) :
    BaseViewModel() {

    private val _loginOutEvent = MutableLiveData<Event<MineEvent>>()

    val loginOutEvent =_loginOutEvent

    fun loginOut() {
        executeRequest(request = mineRepository::logout,
            onSuccess = {
                SpUtils.clear()
                _loginOutEvent.value = Event(MineEvent.LoginOutEvent)
            })
    }
}

sealed class MineEvent {
    object LoginOutEvent : MineEvent()
}
package com.xuwh.kotlinandroid.base.viewmodel

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xuwh.kotlinandroid.enums.LoadState
import com.xuwh.kotlinandroid.enums.RefreshState
import com.xuwh.kotlinandroid.net.DataResponse
import kotlinx.coroutines.launch


/**
 *
 * @ProjectName:    BaseKotlin
 * @Package:        com.xuwh.kotlinandroid.base.viewmodel
 * @ClassName:      BaseViewModel
 * @Description:    viewmodel基类
 * @Author:         xuwh
 * @CreateDate:     2025/3/11 下午7:44
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/11 下午7:44
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
abstract class BaseViewModel : ViewModel(){

    val _loadState = MutableLiveData<LoadState<*>>()

    val loadState: LiveData<LoadState<*>> = _loadState

    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean> = _navigateToLogin

     var refreshState = MutableLiveData<RefreshState>()

    var hasMore = MutableLiveData<Boolean>()

    protected fun <T> executeRequest(
        showLoading:Boolean=true,
        request: suspend () -> DataResponse<T>,
        onSuccess: (T) -> Unit = {}
    ) {
        viewModelScope.launch {
            // 调用
            setLoadingState(showLoading)
            try {
                val result = request()
                if (0!=result.errorCode){
                    _loadState.value=LoadState.Error(result.errorCode,result.errorMsg)
                }else{
                    _loadState.value = when {
                        result.data is List<*> && result.data.isEmpty() -> LoadState.Empty
                        else -> LoadState.Success(result.data)
                    }
                    onSuccess(result.data!!)
                }
            } catch (e: Exception) {
                Log.d("BaseViewModel", e.message!!)
                _loadState.value = LoadState.Error(e.hashCode(), e.message!!)
            }
        }
    }

    fun setLoadingState(showLoading: Boolean) {
        showLoading.takeIf { it }?.let {
            _loadState.value=LoadState.Loading
        }
    }

    // 更新刷新状态
    fun setRefreshState(refreshState: RefreshState) {
        this.refreshState.value=refreshState
    }

    // 更新是否还有更多数据
    fun setHasMore(hasMore: Boolean) {
        this.hasMore.value = hasMore
    }

     fun onRetry(view: View) {}
}
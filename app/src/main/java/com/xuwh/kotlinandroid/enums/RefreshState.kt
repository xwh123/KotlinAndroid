package com.xuwh.kotlinandroid.enums

/**
 *
 * @ProjectName:    BaseKotlin
 * @Package:        com.xuwh.kotlinandroid.enums
 * @ClassName:      RefreshState
 * @Description:    刷新状态枚举类
 * @Author:         xuwh
 * @CreateDate:     2025/3/12 下午4:55
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/12 下午4:55
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
sealed class RefreshState{
    object RefreshEnd : RefreshState()
    object LoadMoreEnd : RefreshState()
}
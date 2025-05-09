package com.xuwh.kotlinandroid.ui.main.viewmodel

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.ext.disableLongClick
import com.xuwh.kotlinandroid.ui.classify.view.ClassifyFragment
import com.xuwh.kotlinandroid.ui.collect.view.CollectFragment
import com.xuwh.kotlinandroid.ui.home.view.HomeFragment
import com.xuwh.kotlinandroid.ui.main.view.MainActivity
import com.xuwh.kotlinandroid.ui.mine.view.MineFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.main.viewmodel
 * @ClassName:      MainViewModel
 * @Description:    类作用描述
 * @Author:         xuwh
 * @CreateDate:     2025/3/15 下午7:33
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/15 下午7:33
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {


    private val tabs = listOf(MainTab.Home,MainTab.Collect, MainTab.Classify, MainTab.Mine)

    fun setupViewPager(activity: MainActivity, pager: ViewPager2) {
        pager.apply {
            adapter = MainPagerAdapter(activity).also {
                offscreenPageLimit = tabs.size
                isUserInputEnabled = false
            }
        }
    }

    fun setupTabLayout(tabLayout: TabLayout, pager: ViewPager2) {
        TabLayoutMediator(tabLayout, pager) { tab, position ->
            val tabConfig = tabs[position]
            tab.configureWith(tabConfig)
        }.attach()
    }

    // 独立 Adapter 类
    private inner class MainPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
        override fun getItemCount() = tabs.size
        override fun createFragment(position: Int) = when (tabs[position]) {
            MainTab.Home -> HomeFragment()
            MainTab.Classify -> ClassifyFragment()
            MainTab.Collect -> CollectFragment()
            MainTab.Mine -> MineFragment()
        }
    }

    // Tab 配置扩展函数
    private fun TabLayout.Tab.configureWith(tab: MainTab) {
        setText(tab.titleRes)
        setIcon(tab.iconRes)
        view.disableLongClick()
    }
}

// Tab 配置密封类
sealed interface MainTab {

    @get:StringRes
    val titleRes: Int

    @get:DrawableRes
    val iconRes: Int

    object Home : MainTab {
        @StringRes
        override val titleRes = R.string.tab_home

        @DrawableRes
        override val iconRes = R.drawable.selector_tab_home
    }

    object Classify : MainTab {
        @StringRes
        override val titleRes = R.string.tab_classify

        @DrawableRes
        override val iconRes = R.drawable.selector_tab_classify
    }

    object Collect : MainTab {
        @StringRes
        override val titleRes = R.string.tab_collect

        @DrawableRes
        override val iconRes = R.drawable.selector_tab_collect
    }

    object Mine : MainTab {
        @StringRes
        override val titleRes = R.string.tab_mine

        @DrawableRes
        override val iconRes = R.drawable.selector_tab_mine
    }
}
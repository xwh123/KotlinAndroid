package com.xuwh.kotlinandroid.ui.classify.viewmodel

import androidx.annotation.StringRes
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.viewmodel.BaseViewModel
import com.xuwh.kotlinandroid.ext.disableLongClick
import com.xuwh.kotlinandroid.ui.classify.view.ClassifyFragment
import com.xuwh.kotlinandroid.ui.home.view.HomeFragment
import com.xuwh.kotlinandroid.ui.main.view.MainActivity
import com.xuwh.kotlinandroid.ui.main.viewmodel.MainTab
import com.xuwh.kotlinandroid.ui.mine.view.MineFragment
import com.xuwh.kotlinandroid.ui.navigation.view.NavigationFragment
import com.xuwh.kotlinandroid.ui.structure.view.StructureFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 *
 * @ProjectName:    KotlinAndroid
 * @Package:        com.xuwh.kotlinandroid.ui.classify.viewmodel
 * @ClassName:      ClassifyViewModel
 * @Description:    分类
 * @Author:         xuwh
 * @CreateDate:     2025/3/20 下午12:23
 * @UpdateUser:     更新者
 * @UpdateDate:     2025/3/20 下午12:23
 * @UpdateRemark:   更新说明
 * @Version:        1.0
 */
@HiltViewModel
class ClassifyViewModel @Inject constructor():BaseViewModel() {

    private val tabs = listOf(ClassTab.StructureTab, ClassTab.NavTab)

    fun setupViewPager(fragment: ClassifyFragment, pager: ViewPager2) {
        pager.apply {
            adapter = ClassPagerAdapter(fragment).also {
                offscreenPageLimit = tabs.size
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
    private inner class ClassPagerAdapter(fragment: ClassifyFragment) : FragmentStateAdapter
        (fragment) {
        override fun getItemCount() = tabs.size
        override fun createFragment(position: Int) = when (tabs[position]) {
            ClassTab.StructureTab -> StructureFragment()
            ClassTab.NavTab -> NavigationFragment()
        }
    }

    // Tab 配置扩展函数
    private fun TabLayout.Tab.configureWith(tab: ClassTab) {
        setText(tab.titleRes)
        view.disableLongClick()
    }
}


sealed interface ClassTab{
    @get:StringRes
    val titleRes: Int

    object StructureTab:ClassTab{
        override val titleRes= R.string.tab_structure
    }

    object NavTab:ClassTab{
        override val titleRes= R.string.tab_nav
    }

}
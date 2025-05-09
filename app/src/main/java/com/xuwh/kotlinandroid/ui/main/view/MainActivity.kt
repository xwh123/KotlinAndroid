package com.xuwh.kotlinandroid.ui.main.view

import android.os.Build
import android.view.KeyEvent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayoutMediator
import com.xuwh.kotlinandroid.R
import com.xuwh.kotlinandroid.base.activity.BaseActivity
import com.xuwh.kotlinandroid.base.constant.ArouterConstants
import com.xuwh.kotlinandroid.databinding.ActivityMainBinding
import com.xuwh.kotlinandroid.ui.classify.view.ClassifyFragment
import com.xuwh.kotlinandroid.ui.home.view.HomeFragment
import com.xuwh.kotlinandroid.ui.main.viewmodel.MainViewModel
import com.xuwh.kotlinandroid.ui.mine.view.MineFragment
import com.xuwh.kotlinandroid.utils.ToastUtils
import dagger.hilt.android.AndroidEntryPoint


@Route(path = ArouterConstants.MAIN)
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    companion object {
        private const val EXIT_DOUBLE_PRESS_INTERVAL = 2000L
    }

    override val viewModel : MainViewModel by viewModels()

    override val layoutId = R.layout.activity_main

    private var lastBackPressedTime = 0L


    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun initData() {
        viewModel.setupViewPager(this, binding.pager)
        viewModel.setupTabLayout(binding.tabLayout, binding.pager)

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK && event?.action == KeyEvent.ACTION_DOWN) {
            return handleBackPressed()
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun handleBackPressed(): Boolean {
        val currentTime = System.currentTimeMillis()

        return if (currentTime - lastBackPressedTime <= EXIT_DOUBLE_PRESS_INTERVAL) {
            // 在时间间隔内再次点击，退出应用
            finish()
            true
        } else {
            // 首次点击或超过间隔时间
            lastBackPressedTime = currentTime
            ToastUtils.show("再按一次退出")
            true
        }
    }
}


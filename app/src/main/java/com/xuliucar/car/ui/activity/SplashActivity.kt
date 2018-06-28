package com.xuliucar.car.ui.activity

import com.cangmaomao.lib.action.f_testa
import com.cangmaomao.lib.action.f_testb
import com.cangmaomao.lib.action.f_testc
import com.cangmaomao.lib.base.BaseActivity
import com.cangmaomao.m_mine.TestFragment
import com.xuliucar.car.R

class SplashActivity : BaseActivity() {

    override fun layViewId(): Int {
        return R.layout.a_splash
    }

    override fun addViewId(): Int {
        return R.id.frameLayout
    }

    override fun initView() {
        val flag = intent.getIntExtra("flag", 0);
        when (flag) {
            f_testa -> addFragment(TestFragment())
            f_testb -> addFragment(TestFragment())
            f_testc -> addFragment(TestFragment())
        }
    }


}
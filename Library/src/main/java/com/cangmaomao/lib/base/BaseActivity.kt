package com.cangmaomao.lib.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.toolbar_view.*

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layViewId())
        initView()
    }

    //布局ID
    abstract fun layViewId(): Int

    //初始化
    abstract fun initView()

    //fragment
    abstract fun addViewId(): Int

    fun setToolbarTitle(str: String) {
        toolbar_title.text = str
    }

    fun setToolbarSubtitle(str: String) {
        toolbar_subtitle.text = str
    }

    fun backToolbar() {
        toolbar.setNavigationOnClickListener { finish() }
    }


    fun addFragment(fragment: Fragment) {
        val bt = supportFragmentManager.beginTransaction()
        val fragmentList = supportFragmentManager.fragments
        val fragmentTmp = supportFragmentManager.findFragmentByTag(fragment::class.java.simpleName)
        if (fragmentTmp == null) {
            for (f in fragmentList) {
                bt.hide(f)
            }
            bt.add(addViewId(), fragment, fragment::class.java.simpleName)
        } else {
            for (f in fragmentList) {
                if (f.tag.equals(fragmentTmp.tag)) bt.show(fragmentTmp) else bt.hide(f)
            }
            bt.show(fragmentTmp)
        }
        bt.commitAllowingStateLoss()
    }


}
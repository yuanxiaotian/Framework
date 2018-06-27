package com.cangmaomao.m_mine

import android.content.Intent
import android.net.Uri
import com.cangmaomao.lib.action.test
import com.cangmaomao.lib.action.test2
import com.cangmaomao.lib.base.BaseActivity
import kotlinx.android.synthetic.main.test.*

class TestActivity : BaseActivity() {

    override fun layViewId(): Int {
        return R.layout.test
    }

    override fun addViewId(): Int {
        return 0
    }

    override fun initView() {

        text3.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(test2))) }
    }

}
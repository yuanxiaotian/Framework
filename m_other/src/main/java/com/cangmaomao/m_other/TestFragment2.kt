package com.cangmaomao.m_other

import android.content.Intent
import android.net.Uri
import com.cangmaomao.lib.action.test
import com.cangmaomao.lib.base.BaseActivity
import kotlinx.android.synthetic.main.test_v.*

class TestFragment2 : BaseActivity() {


    override fun layViewId(): Int {
        return R.layout.test_v
    }

    override fun addViewId(): Int {
        return 0
    }

    override fun initView() {
        text4.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(test))) }
    }


}
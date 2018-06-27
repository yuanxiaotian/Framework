package com.xuliucar.car.ui.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.cangmaomao.lib.action.test
import com.cangmaomao.lib.action.test2
import com.xuliucar.car.R
import kotlinx.android.synthetic.main.a_splash.*

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_splash)
        text1.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(test))) }
        text2.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(test2))) }
    }
}
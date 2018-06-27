package com.cangmaomao.lib.base

import android.os.Bundle
import android.view.View
import com.cangmaomao.lib.utils.SystemUtils
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * Author:帅气的potato
 */

abstract class BaseFragment<T : BasePresenter> : BaseAppCompatFragment(), BaseView<T> {

    var t: T? = null
    var mRxPermissions: RxPermissions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        t = SystemUtils.getGenericInstance(this, 0)
        mRxPermissions = RxPermissions(_mActivity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }

    abstract fun initView(savedInstanceState: Bundle?)
}

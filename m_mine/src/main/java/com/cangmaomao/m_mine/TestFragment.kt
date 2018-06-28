package com.cangmaomao.m_mine

import android.os.Bundle
import com.cangmaomao.lib.base.BaseFragment
import com.xuliucar.me.contract.Contract

class TestFragment : BaseFragment<Contract.Presenter>() {


    override val contentViewLayoutID: Int
        get() = R.layout.test


    override fun initView(savedInstanceState: Bundle?) {
    }


}
package com.xuliucar.me.contract

import com.cangmaomao.lib.base.BasePresenter
import com.cangmaomao.lib.base.BaseView

interface Contract2 {

    interface LoginView : BaseView<Presenter> {

        fun showRequestErrInfo(msg: String?)

        fun getCompany(): String

        fun getAccount(): String

        fun getPwd(): String

    }


    interface Presenter : BasePresenter {

        //登陆请求
        fun loginRequest()

        //注册
        fun reg(tag: String)

        //请求失败
        fun requestFail(msg: String?)
    }

}
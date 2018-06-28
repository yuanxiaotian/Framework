package com.cangmaomao.lib.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tbruyelle.rxpermissions2.RxPermissions
import me.yokeyword.fragmentation.ISupportFragment


/**
 * Author:帅气的potato
 */

abstract class BaseFragment<T : BasePresenter> : BaseAppCompatFragment(), BaseView<T> {

    lateinit var t: T
    var mRxPermissions: RxPermissions? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRxPermissions = RxPermissions(_mActivity)
    }

    override fun setPresenter(presenter: T) {
        t = presenter
    }

    override fun onCreateView(inflater: LayoutInflater,  container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(savedInstanceState)
    }


    abstract fun initView(savedInstanceState: Bundle?)


    /****
     * 从栈中移除当前fragment
     */
    override fun pop() {
        super.pop()
    }

    /****
     * 跳转至目标fragment
     */
    override fun start(toFragment: ISupportFragment?) {
        super.start(toFragment)
    }

    /***
     * toFragment：目标fragment
     * targetFragmentClass:当前fragment
     * includeTargetFragment:是否在栈中保留当前fragment
     */
    override fun startWithPopTo(toFragment: ISupportFragment?, targetFragmentClass: Class<*>?, includeTargetFragment: Boolean) {
        super.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment)
    }


    /****
     * 加载fragment
     * containerId：加载的id
     * toFragment:被加载的fargment对象
     */
    override fun loadRootFragment(containerId: Int, toFragment: ISupportFragment?) {
        super.loadRootFragment(containerId, toFragment)
    }


    /****
     * 替换fragment
     */
    override fun replaceFragment(toFragment: ISupportFragment?, addToBackStack: Boolean) {
        super.replaceFragment(toFragment, addToBackStack)
    }


}

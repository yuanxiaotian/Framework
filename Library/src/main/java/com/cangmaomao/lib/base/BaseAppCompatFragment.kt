package com.cangmaomao.lib.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

import com.cangmaomao.lib.widget.MultipleStatusLayout

import me.yokeyword.fragmentation.SupportFragment

/**
 * Author:帅气的potato
 */

abstract class BaseAppCompatFragment : SupportFragment() {

    protected var mContext: Context? = null
    protected var mView: View? = null
    private var mMultipleStatusLayout: MultipleStatusLayout? = null

    /**
     * 与布局资源进行绑定
     *
     * @return 返回当前布局id
     */
    protected abstract val contentViewLayoutID: Int


    val handler: Handler
        get() = Handler(_mActivity.mainLooper)

    /**
     * 当fragment创建的时候获取测试的tag,绑定相应的eventBus
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {//获取传入参数
            getBundleExtras(arguments)
        }
        mContext = activity
    }

    /**
     * 创建fragment布局
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        if (contentViewLayoutID != 0 && mView == null) {
            mMultipleStatusLayout = MultipleStatusLayout(context)
            mView = inflater.inflate(contentViewLayoutID, mMultipleStatusLayout)
            return mView
        } else if (mView != null) {
            return mView
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    /**
     * 获得bundle data
     */
    protected fun getBundleExtras(extras: Bundle?) {}

    /**
     * 初始化toolbar (只有标题）
     *
     * @param title 标题
     */
    protected fun initToolBarNav(toolbar: Toolbar, @StringRes title: Int) {
        initToolBarNav(toolbar, getString(title))
    }

    /**
     * 初始化toolbar (只有标题）
     *
     * @param title 标题
     */
    protected fun initToolBarNav(toolbar: Toolbar, @StringRes title: Int, listener: Toolbar.OnMenuItemClickListener?) {
        initToolBarNav(toolbar, getString(title))
        if (listener != null) {
            toolbar.setOnMenuItemClickListener(listener)
        }
    }

    /***
     *
     * @param toolbar
     * @param title
     * @param listener
     */
    protected fun initToolBarNav(toolbar: Toolbar, title: String, listener: Toolbar.OnMenuItemClickListener?) {
        initToolBarNav(toolbar, title)
        if (listener != null) {
            toolbar.setOnMenuItemClickListener(listener)
        }
    }

    /**
     * 初始化toolbar (只有标题）
     *
     * @param title 标题
     */
    @JvmOverloads protected fun initToolBarNav(toolbar: Toolbar, title: String = "") {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { v ->
            showKeyboard(false)
            pop()
        }
        toolbar.title = title
    }

    /**
     * 初始化toolbar(只有标题）
     *
     * @param runnable 导航点击事件
     */
    protected fun initToolBarNav(toolbar: Toolbar, title: String, runnable: Runnable) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { v ->
            showKeyboard(false)
            handler.postDelayed(runnable, 200)
        }
        toolbar.title = title
    }

    /**
     * 跳转到相应的activity（没有带任何信息）
     */
    protected fun readyGo(clazz: Class<*>) {
        val intent = Intent(activity, clazz)
        startActivity(intent)
    }

    /**
     * 跳转到相应的activity，携带bundle数据
     */
    protected fun readyGo(clazz: Class<*>, bundle: Bundle?) {
        val intent = Intent(activity, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 跳转到相应的activity，然后干掉自己
     */
    protected fun readyGoThenKill(clazz: Class<*>) {
        val intent = Intent(activity, clazz)
        startActivity(intent)
        activity!!.finish()
    }

    /**
     * 跳转到相应的activity并携带bundle数据，然后干掉自己
     */
    protected fun readyGoThenKill(clazz: Class<*>, bundle: Bundle?) {
        val intent = Intent(activity, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
        activity!!.finish()

    }

    /**
     * 跳转到相应activity，并接受回传值
     */
    protected fun readyGoForResult(clazz: Class<*>, requestCode: Int) {
        val intent = Intent(activity, clazz)
        startActivityForResult(intent, requestCode)
    }

    /**
     * 跳转到相应activity,并带参数
     */
    protected fun readyGoForResult(clazz: Class<*>, requestCode: Int, bundle: Bundle?) {
        val intent = Intent(activity, clazz)
        if (null != bundle) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }


    /**
     * 显示内容
     */
    fun showContent() {
        mMultipleStatusLayout!!.showContent()
    }


    /**
     * 显示没有数据
     * @param drawId  图片id
     * @param message 提示信息
     */
    fun showEmpty(@DrawableRes drawId: Int, message: String) {
        mMultipleStatusLayout!!.showEmpty(drawId, message)
    }

    /**
     * 显示正在加载
     */
    fun showLoading() {
        mMultipleStatusLayout!!.showLoading()
    }

    /**
     * 显示网络异常
     */
    //    public void showNetError(View.OnClickListener listener) {
    //        showNetError(getString(R.string.no_network_message), listener);
    //    }
    //
    //    /**
    //     * 显示网络异常
    //     *
    //     * @param msg      提示信息
    //     * @param listener 提示信息
    //     */
    //    public void showNetError(String msg, View.OnClickListener listener) {
    //        showNetError(R.drawable.ic_no_data, msg, getString(R.string.click_refresh), listener);
    //    }

    /**
     * 显示网络异常
     *
     * @param drawId          图片id
     * @param msg             提示信息
     * @param errorButtonText 按钮文字
     * @param listener        点击监听
     */
    fun showNetError(@DrawableRes drawId: Int, msg: String, errorButtonText: String,
                     listener: View.OnClickListener) {
        mMultipleStatusLayout!!.showError(drawId, msg, errorButtonText, listener)
    }

    protected fun showKeyboard(isShow: Boolean) {
        val imm = _mActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (isShow) {
            if (_mActivity.currentFocus == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            } else {
                imm.showSoftInput(_mActivity.currentFocus, 0)
            }
        } else {
            if (_mActivity.currentFocus != null) {
                imm.hideSoftInputFromWindow(_mActivity.currentFocus!!.windowToken, 0)
            }
        }
    }
}
/**
 * 初始化toolbar
 */

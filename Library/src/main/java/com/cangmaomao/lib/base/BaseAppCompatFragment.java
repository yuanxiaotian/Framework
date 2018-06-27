package com.cangmaomao.lib.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.cangmaomao.lib.widget.MultipleStatusLayout;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by EDZ on 2018/6/27.
 */

public abstract class BaseAppCompatFragment extends SupportFragment{

    protected Context mContext;
    protected View mView;
    private MultipleStatusLayout mMultipleStatusLayout;

    /**
     * 当fragment创建的时候获取测试的tag,绑定相应的eventBus
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {//获取传入参数
            getBundleExtras(getArguments());
        }
        mContext = getActivity();
    }

    /**
     * 创建fragment布局
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        if (getContentViewLayoutID() != 0 && mView == null) {
            mMultipleStatusLayout = new MultipleStatusLayout(getContext());
            mView = inflater.inflate(getContentViewLayoutID(), mMultipleStatusLayout);
            return mView;
        } else if (mView != null) {
            return mView;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    /**
     * 获得bundle data
     */
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * 与布局资源进行绑定
     *
     * @return 返回当前布局id
     */
    protected abstract int getContentViewLayoutID();


    /**
     * 初始化toolbar
     */
    protected void initToolBarNav(Toolbar toolbar) {
        initToolBarNav(toolbar, "");
    }

    /**
     * 初始化toolbar (只有标题）
     *
     * @param title 标题
     */
    protected void initToolBarNav(Toolbar toolbar, @StringRes int title) {
        initToolBarNav(toolbar, getString(title));
    }

    /**
     * 初始化toolbar (只有标题）
     *
     * @param title 标题
     */
    protected void initToolBarNav(Toolbar toolbar, @StringRes int title, Toolbar.OnMenuItemClickListener listener) {
        initToolBarNav(toolbar, getString(title));
        if (listener != null) {
            toolbar.setOnMenuItemClickListener(listener);
        }
    }

    /***
     *
     * @param toolbar
     * @param title
     * @param listener
     */
    protected void initToolBarNav(Toolbar toolbar,  String title, Toolbar.OnMenuItemClickListener listener) {
        initToolBarNav(toolbar, title);
        if (listener != null) {
            toolbar.setOnMenuItemClickListener(listener);
        }
    }

    /**
     * 初始化toolbar (只有标题）
     *
     * @param title 标题
     */
    protected void initToolBarNav(Toolbar toolbar, String title) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v->{
            showKeyboard(false);
            pop();
        });
        toolbar.setTitle(title);
    }

    /**
     * 初始化toolbar(只有标题）
     *
     * @param runnable 导航点击事件
     */
    protected void initToolBarNav(Toolbar toolbar, String title, final Runnable runnable) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v->{
            showKeyboard(false);
            getHandler().postDelayed(runnable, 200);
        });
        toolbar.setTitle(title);
    }

    /**
     * 跳转到相应的activity（没有带任何信息）
     */
    protected void readyGo(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
    }

    /**
     * 跳转到相应的activity，携带bundle数据
     */
    protected void readyGo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转到相应的activity，然后干掉自己
     */
    protected void readyGoThenKill(Class<?> clazz) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivity(intent);
        getActivity().finish();
    }

    /**
     * 跳转到相应的activity并携带bundle数据，然后干掉自己
     */
    protected void readyGoThenKill(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        getActivity().finish();

    }

    /**
     * 跳转到相应activity，并接受回传值
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        startActivityForResult(intent, requestCode);
    }

    /**
     * 跳转到相应activity,并带参数
     */
    protected void readyGoForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }


    /**
     * 显示内容
     */
    public void showContent() {
        mMultipleStatusLayout.showContent();
    }


    /**
     * 显示没有数据
     * @param drawId  图片id
     * @param message 提示信息
     */
    public void showEmpty(@DrawableRes int drawId, String message) {
        mMultipleStatusLayout.showEmpty(drawId, message);
    }

    /**
     * 显示正在加载
     */
    public void showLoading() {
        mMultipleStatusLayout.showLoading();
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
    public void showNetError(@DrawableRes int drawId, String msg, String errorButtonText,
                             View.OnClickListener listener) {
        mMultipleStatusLayout.showError(drawId, msg, errorButtonText, listener);
    }

    protected void showKeyboard(boolean isShow) {
        InputMethodManager imm = (InputMethodManager) _mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            if (_mActivity.getCurrentFocus() == null) {
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            } else {
                imm.showSoftInput(_mActivity.getCurrentFocus(), 0);
            }
        } else {
            if (_mActivity.getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(_mActivity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }



    public Handler getHandler() {
        return new Handler(_mActivity.getMainLooper());
    }
}
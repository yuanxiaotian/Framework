package com.cangmaomao.lib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;

import java.lang.reflect.ParameterizedType;


/**
 * Author:  帅气的potato
 * Description:
 */

public class SystemUtils {


    /**
     * 判断是否在主线程中运行
     *
     * @return
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 获取应用版本号
     *
     * @param context 上下文对象
     * @return 返回当前应用的版本号 默认返回为1
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 获取当前类上的泛型参数，并实例化
     *
     * @param object 当前类
     * @param index  类泛型参数角标
     * @param <T>    泛型实例化对象
     * @return 泛型实例化对象
     */
    public static <T> T getGenericInstance(Object object, int index) {
        try {
            ParameterizedType type = (ParameterizedType) (object.getClass().getGenericSuperclass());//获取当前类的父类泛型参数
            Class<T> clazz = (Class) (type.getActualTypeArguments()[index]);//获取泛型class
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {

        }
        return null;
    }


}

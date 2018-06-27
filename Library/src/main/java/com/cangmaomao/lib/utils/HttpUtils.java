package com.cangmaomao.lib.utils;

import com.cangmaomao.network.request.HttpManage;

/**
 * Author:帅气的potato
 */

public class HttpUtils {

    private static HttpUtils httpUtils;
    private static HttpManage manage;

    private HttpUtils() {
        manage = HttpManage.getInstance();
    }

    public static HttpUtils getIntance() {
        if (httpUtils == null) {
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }


    public void setHttpRequest(Class<?> clazz) {
//        manage.create(clazz)
    }

}

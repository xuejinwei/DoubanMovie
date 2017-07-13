package com.xuejinwei.doubanmovie.api;

import android.text.TextUtils;
import android.util.Log;

import com.xuejinwei.doubanmovie.api.exceptions.DataInvalidException;
import com.xuejinwei.doubanmovie.api.exceptions.OkHttpFailureException;
import com.xuejinwei.doubanmovie.api.exceptions.ServerException;
import com.xuejinwei.doubanmovie.utils.ToastUtil;

import java.io.IOException;

/**
 * Created by xuejinwei on 2017/3/13.
 * Email:xuejinwei@outlook.com
 * 全局网络请求统一处理
 */

public class RequestHandler {
    /**
     * 通用的异常处理
     */
    public static boolean handleError(Throwable throwable) {

        // 网络异常，默认进行提示
        if (throwable instanceof IOException) {

            ToastUtil.show("网络异常，请稍后再试");
//            return true;
        }

        // 服务器异常
        if (throwable instanceof OkHttpFailureException) {
            ToastUtil.show(throwable.getMessage());
            return true;
        }

        // 数据解析失败，打印log
        if (throwable instanceof DataInvalidException) {
            Log.e("RequestHandler", "数据解析失败", throwable);
            return true;
        }

        Log.e("RequestHandler", "其他异常", throwable);
        return false;
    }

    public static void handleActionError(ServerException serverException) {

        if (serverException != null && !TextUtils.isEmpty(serverException.getMessage())) {
            ToastUtil.show(serverException.getMessage());
        }
    }
}

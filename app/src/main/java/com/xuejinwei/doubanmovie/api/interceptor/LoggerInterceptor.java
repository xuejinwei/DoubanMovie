package com.xuejinwei.doubanmovie.api.interceptor;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by xuejinwei on 2017/7/13.
 * Email:xuejinwei@outlook.com
 * retrofit 日志拦截器
 */

public class LoggerInterceptor implements Interceptor {
    private static final String TAG = "RetrofitLog";

    private final HttpLoggingInterceptor interceptor;


    public LoggerInterceptor() {

        interceptor = new HttpLoggingInterceptor(message -> {
            if (message.startsWith("{")) {
                Logger.json(message);
            } else if (message.contains("<!DOCTYPE html>")) {
                Logger.d(message);
            } else {
                Log.i("Api_head", message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return interceptor.intercept(chain);
    }
}

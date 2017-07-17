package com.xuejinwei.doubanmovie.api;

import android.support.annotation.NonNull;

import com.xuejinwei.doubanmovie.api.interceptor.ApiInterceptor;
import com.xuejinwei.doubanmovie.api.interceptor.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xuejinwei on 2017/3/13.
 * Email:xuejinwei@outlook.com
 */

public class ApiManager {
    public static MediaType JSON = MediaType.parse("application/json");

    private static String baseUrl = "https://Api.douban.com/v2/";

    private static Retrofit.Builder sBuilder;
    private static Api              sApi;
    private static ApiWrapper       sApiWrapper;

    static {
        OkHttpClient client = buildOkHttpClient();
        sBuilder = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    @NonNull
    private static OkHttpClient buildOkHttpClient() {

        return new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS) // 超时时间
                .writeTimeout(10, TimeUnit.SECONDS) // 超时时间
                .connectTimeout(10, TimeUnit.SECONDS) // 超时时间
                .addInterceptor(new ApiInterceptor()) // 异常信息拦截,添加request head
                .addInterceptor(new LoggerInterceptor()) // 日志拦截器
                .build();
    }

    public static Api getApi() {
        if (sApi == null) {
            sApi = sBuilder.baseUrl(baseUrl).build().create(Api.class);
        }
        return sApi;
    }


    public static ApiWrapper getApiWrapper() {
        if (sApiWrapper == null) {
            sApiWrapper = new ApiWrapper();
        }
        return sApiWrapper;
    }
}

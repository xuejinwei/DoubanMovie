package com.xuejinwei.doubanmovie.api.interceptor;

import com.xuejinwei.doubanmovie.api.exceptions.OkHttpFailureException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by twiceYuan on 01/12/2016.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 * <p>
 * 服务器统一拦截器，添加request head
 */
public class ApiInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        // Append user agent header
        Request request = chain.request().newBuilder()
                // 如果手动添加Accept-Encoding请求头，okhttp就不会自动执行Gzip，详情见BridgeInterceptor 78~82行
                .build();
        Response response = chain.proceed(request);
        if (!response.isSuccessful()) {
            throw new OkHttpFailureException("服务器异常：" + response.code());
        }

        return response;
    }
}

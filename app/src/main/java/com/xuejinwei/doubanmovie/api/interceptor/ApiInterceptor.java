package com.xuejinwei.doubanmovie.api.interceptor;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.xuejinwei.doubanmovie.api.ApiManager;
import com.xuejinwei.doubanmovie.api.exceptions.DataInvalidException;
import com.xuejinwei.doubanmovie.api.exceptions.OkHttpFailureException;
import com.xuejinwei.doubanmovie.model.DouBanBaseResult;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

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
        DouBanBaseResult data = null;
        try {
            String a = response.body().string();
            data = new Gson().fromJson(response.body().string(), DouBanBaseResult.class);
        } catch (JsonSyntaxException e) {
            throw new DataInvalidException("数据解析失败：" + e.getMessage());
        }
        if (data == null) {
            throw new DataInvalidException("数据解析失败：" + response.body().string());
        }

//        if (data.status != 0) {
//            throw new ServerException(data.status, data.msg);
//        }

        // un wrap result
        return response.newBuilder().code(200)
                .body(ResponseBody.create(ApiManager.JSON, data.toString()))
                .build();
    }
}

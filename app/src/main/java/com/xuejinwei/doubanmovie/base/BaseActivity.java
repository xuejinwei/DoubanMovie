package com.xuejinwei.doubanmovie.base;

import android.support.v7.app.AppCompatActivity;

import com.xuejinwei.doubanmovie.api.Api;
import com.xuejinwei.doubanmovie.api.ApiManager;
import com.xuejinwei.doubanmovie.api.ApiWrapper;
import com.xuejinwei.doubanmovie.api.RxRequestManager;
import com.xuejinwei.doubanmovie.api.RxUtil;
import com.xuejinwei.doubanmovie.api.exceptions.ServerException;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by xuejinwei on 2017/7/13.
 * Email:xuejinwei@outlook.com
 */

public class BaseActivity extends AppCompatActivity implements RxRequestManager{


    /*
     * Retrofit & RxJava
     */

    protected CompositeSubscription mSubscriptions = new CompositeSubscription();


    public <T> void exec(Observable<T> request, Action1<T> resultCallback) {
        RxUtil.exec(mSubscriptions, request, resultCallback);
    }

    public <T> void exec(Observable<T> request, Action1<T> resultCallback,
                         Action1<ServerException> serverCallback) {
        RxUtil.exec(mSubscriptions, request, resultCallback, serverCallback);
    }

    @Override
    public <T> void exec(Observable<T> request, Action1<T> resultCallback,
                         Action1<ServerException> serverCallback,
                         Func1<Throwable, Boolean> localErrorCallback) {
        RxUtil.exec(mSubscriptions, request, resultCallback, serverCallback, localErrorCallback);
    }

    public ApiWrapper ApiWrapper() {
        return ApiManager.getApiWrapper();
    }

    public Api Api() {
        return ApiManager.getApi();
    }
}

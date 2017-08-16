package com.xuejinwei.doubanmovie.base;

import android.support.v4.app.Fragment;

import com.xuejinwei.doubanmovie.api.Api;
import com.xuejinwei.doubanmovie.api.ApiManager;
import com.xuejinwei.doubanmovie.api.ApiWrapper;
import com.xuejinwei.doubanmovie.api.RxRequestManager;
import com.xuejinwei.doubanmovie.api.RxUtil;
import com.xuejinwei.doubanmovie.api.exceptions.ServerException;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by xuejinwei on 2017/8/16.
 * Email:xuejinwei@outlook.com
 */

public class BaseFragment extends Fragment implements RxRequestManager{



    @Override
    public void onDestroy() {
        if (null != mDisposable) {
            mDisposable.clear();
        }
        super.onDestroy();
    }


    /*
     * Retrofit & RxJava
     */

    protected CompositeDisposable mDisposable = new CompositeDisposable();


    public <T> void exec(Observable<T> request, Consumer<T> resultCallback) {
        RxUtil.exec(mDisposable, request, resultCallback);
    }

    public <T> void exec(Observable<T> request, Consumer<T> resultCallback,
                         Consumer<ServerException> serverCallback) {
        RxUtil.exec(mDisposable, request, resultCallback, serverCallback);
    }

    @Override
    public <T> void exec(Observable<T> request, Consumer<T> resultCallback,
                         Consumer<ServerException> serverCallback,
                         Function<Throwable, Boolean> localErrorCallback) {
        RxUtil.exec(mDisposable, request, resultCallback, serverCallback, localErrorCallback);
    }

    public ApiWrapper ApiWrapper() {
        return ApiManager.getApiWrapper();
    }

    public Api Api() {
        return ApiManager.getApi();
    }
}

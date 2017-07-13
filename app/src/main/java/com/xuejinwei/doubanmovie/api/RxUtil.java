package com.xuejinwei.doubanmovie.api;

import com.xuejinwei.doubanmovie.api.exceptions.ServerException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by xuejinwei on 2017/5/23.
 * Email:xuejinwei@outlook.com
 */

public class RxUtil {

    public static <T> void exec(CompositeSubscription mSubscriptions, Observable<T> request, Action1<T> resultCallback) {
        exec(mSubscriptions, request, resultCallback, RequestHandler::handleActionError);
    }

    public static <T> void exec(CompositeSubscription mSubscriptions, Observable<T> request, Action1<T> resultCallback,
                                Action1<ServerException> serverCallback) {
        exec(mSubscriptions, request, resultCallback, serverCallback,throwable -> false);
    }

    public static <T> void exec(CompositeSubscription mSubscriptions, Observable<T> request, Action1<T> resultCallback,
                                Action1<ServerException> serverCallback, Func1<Throwable, Boolean> localErrorCallback) {
        mSubscriptions.add(network(request)
                .subscribe(resultCallback, throwable -> {
                    if (throwable instanceof ServerException) {
                        serverCallback.call((ServerException) throwable);
                        return;
                    }
                    if (!localErrorCallback.call(throwable)) {
                        RequestHandler.handleError(throwable);
                    }
                }));
    }

    public static <T> Observable<T> network(Observable<T> networkRequest) {
        return networkRequest.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}

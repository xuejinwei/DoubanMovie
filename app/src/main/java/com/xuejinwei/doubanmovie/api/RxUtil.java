package com.xuejinwei.doubanmovie.api;

import com.xuejinwei.doubanmovie.api.exceptions.ServerException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xuejinwei on 2017/5/23.
 * Email:xuejinwei@outlook.com
 */

public class RxUtil {

    public static <T> void exec(CompositeDisposable mDisposable, Observable<T> request, Consumer<T> resultCallback) {
        exec(mDisposable, request, resultCallback, RequestHandler::handleActionError);
    }

    public static <T> void exec(CompositeDisposable mDisposable, Observable<T> request, Consumer<T> resultCallback,
                                Consumer<ServerException> serverCallback) {
        exec(mDisposable, request, resultCallback, serverCallback,throwable -> false);
    }

    public static <T> void exec(CompositeDisposable mDisposable, Observable<T> request, Consumer<T> resultCallback,
                                Consumer<ServerException> serverCallback, Function<Throwable, Boolean> localErrorCallback) {
        mDisposable.add(network(request)
                .subscribe(resultCallback, throwable -> {
                    if (throwable instanceof ServerException) {
                        serverCallback.accept((ServerException) throwable);
                        return;
                    }
                    if (!localErrorCallback.apply(throwable)) {
                        RequestHandler.handleError(throwable);
                    }
                }));
    }

    public static <T> Observable<T> network(Observable<T> networkRequest) {
        return networkRequest.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}

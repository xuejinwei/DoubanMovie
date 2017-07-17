package com.xuejinwei.doubanmovie.api;


import com.xuejinwei.doubanmovie.api.exceptions.ServerException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


/**
 * Created by xuejinwei on 2017/5/23.
 * Email:xuejinwei@outlook.com
 * 执行 Rx 请求接口
 * <p>
 * 实现处
 * 真正实现处
 * {@link RxUtil}
 */

public interface RxRequestManager {

    ApiWrapper ApiWrapper();

    Api Api();

    /**
     * Run a common retrofit request with default error handler
     * 通用retrofit request，只处理正确结果callback
     * 逻辑错误、网络错误默认处理
     */
    <T> void exec(Observable<T> request, Consumer<T> resultCallback);

    /**
     * Run a common retrofit request
     * 通用retrofit request，自定义逻辑错误处理
     * 适合只需要对服务器返回的status进行不同处理的情况，不包含UI初始化处理
     *
     * @param request        request
     * @param resultCallback 返回结果callback
     * @param serverCallback  {@link ServerException} 服务器逻辑错误处理callback ，包含status 和msg
     * @param <T>            成功返回结果
     */
    <T> void exec(Observable<T> request, Consumer<T> resultCallback,
                  Consumer<ServerException> serverCallback);


    /**
     * Run a common retrofit request
     * 逻辑错误和网络错误都需要处理，适合在发生错误的时候需要进行UI初始化的情况，例如在发送数据显示loading时候，在发生逻辑、网络错误时候，都需要隐藏loading
     * @param request        request
     * @param resultCallback 返回结果callback
     * @param serverCallback  {@link ServerException} 逻辑错误处理callback ，包含status 和msg，只能是服务器返回的逻辑错误
     * @param localErrorCallback 本地错误回调：网络、服务器异常、数据解析错误callback。如果返回true，则说明自己完全处理该错误，不执行默认错误处理；如果返回false，则说明还需要默认底层的默认错误处理
     * @param <T>            成功返回结果
     */
    <T> void exec(Observable<T> request, Consumer<T> resultCallback,
                  Consumer<ServerException> serverCallback, Function<Throwable, Boolean> localErrorCallback);
}

package com.xuejinwei.doubanmovie.api.exceptions;

/**
 * Created by xuejinwei on 2017/3/16.
 * Email:xuejinwei@outlook.com
 * 服务器异常，http返回404或者502
 */

public class OkHttpFailureException extends RuntimeException {

    public OkHttpFailureException(String message) {
        super(message);
    }
}

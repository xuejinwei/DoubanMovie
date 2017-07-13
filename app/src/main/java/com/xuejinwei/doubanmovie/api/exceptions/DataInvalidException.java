package com.xuejinwei.doubanmovie.api.exceptions;

/**
 * Created by twiceYuan on 01/12/2016.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 *
 * 数据不合法
 */
public class DataInvalidException extends RuntimeException {

    public DataInvalidException(String message) {
        super(message);
    }
}

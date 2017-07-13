package com.xuejinwei.doubanmovie.api.exceptions;

/**
 * Created by xuejinwei on 2017/3/14.
 * Email:xuejinwei@outlook.com
 */

public class ServerException extends RuntimeException {
    private int status;

    /**
     * 传入retrofit 除了 data.status == ErrorCode.Code_300，和data.status != ErrorCode.Code_Success，200的其他异常,方便通过status特殊处理
     */
    public ServerException(int status, String msg) {
        super(msg);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
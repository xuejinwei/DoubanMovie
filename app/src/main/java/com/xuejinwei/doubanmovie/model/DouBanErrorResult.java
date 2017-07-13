package com.xuejinwei.doubanmovie.model;

/**
 * Created by xuejinwei on 16/3/26.
 * Email:xuejinwei@outlook.com
 * 豆瓣的错误封装实体
 */
public class DouBanErrorResult extends Exception {

    /**
     * 豆瓣错误代码
     */
    public int    code;
    /**
     * 豆瓣错误信息，英文
     */
    public String msg;
    /**
     * 错误的请求
     */
    public String request;
}

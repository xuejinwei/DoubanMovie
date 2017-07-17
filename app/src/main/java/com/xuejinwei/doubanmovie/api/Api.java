package com.xuejinwei.doubanmovie.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by xuejinwei on 2017/7/13.
 * Email:xuejinwei@outlook.com
 */

public interface Api {

    /**
     * 查询正在热映movie
     *
     * @param start int 开始数
     * @param count 查询数量，传入0 为默认数量
     * @return
     */
    @GET("movie/in_theaters")
    Observable<Object> getMovieInTheaters(@Query("start") int start, @Query("count") int count);

}

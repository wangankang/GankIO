package com.cornor.gank.http;

import com.cornor.gank.model.pojo.GankData;
import com.cornor.gank.model.pojo.GankDateContent;
import com.cornor.gank.model.pojo.GankList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/7
 */

public interface GankApiService {
    @GET("api/data/{type}/15/{page}")
    Observable<GankList<GankData>> categoryData(@Path("type") String type, @Path("page") int page);


    @GET("api/day/{year}/{month}/{day}")
    Observable<GankDateContent> dateContent(@Path("year") int year,@Path("month") int month,@Path("day") int day);

}

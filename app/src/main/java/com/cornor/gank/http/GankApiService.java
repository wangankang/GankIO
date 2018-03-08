package com.cornor.gank.http;

import com.cornor.gank.model.pojo.GankCategory;
import com.cornor.gank.model.pojo.GankData;

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
    Observable<GankData<GankCategory>> categoryData(@Path("type") String type, @Path("page") int page);
}

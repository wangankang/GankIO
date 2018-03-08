package com.cornor.gank.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/7
 */

public class GankApi {
    private static final String BASE_URL = "http://gank.io/";
    private static final int TIME_OUT = 10;
    private Retrofit retrofit;
    private GankApiService apiService;

    private GankApi() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(GankApiService.class);
    }

    private static class SingleInstance {
        public static final GankApi instance = new GankApi();
    }

    public static GankApi getInstance(){
        return SingleInstance.instance;
    }

    public GankApiService getApiService(){
        return apiService;
    }

}

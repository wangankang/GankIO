package com.cornor.gank.model;

import com.cornor.gank.http.GankApi;
import com.cornor.gank.model.pojo.GankCategory;
import com.cornor.gank.model.pojo.GankData;

import java.io.Serializable;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/7
 */

public class GankModel implements Serializable {
    public void getCategoryData(String type, int page, Observer<GankData<GankCategory>> observer){
        GankApi.getInstance().getApiService().categoryData(type, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}

package com.cornor.gank.model;

import android.text.TextUtils;

import com.cornor.gank.http.GankApi;
import com.cornor.gank.model.pojo.GankData;
import com.cornor.gank.model.pojo.GankDateContent;
import com.cornor.gank.model.pojo.GankList;

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
    public void getCategoryData(String type, int page, Observer<GankList<GankData>> observer){
        GankApi.getInstance().getApiService().categoryData(type, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void getDateContent(String date, Observer<GankDateContent> observer) {
        if(TextUtils.isEmpty(date)){
            return;
        }
        String[] dateArr = date.split("-");
        if(dateArr != null && dateArr.length == 3){
            int year = Integer.parseInt(dateArr[0]);
            int month = Integer.parseInt(dateArr[1]);
            int day = Integer.parseInt(dateArr[2]);
            GankApi.getInstance().getApiService().dateContent(year, month, day)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }
    }

}

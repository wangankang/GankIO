package com.cornor.gank.presenter;

import android.util.Log;

import com.cornor.gank.model.GankModel;
import com.cornor.gank.model.pojo.GankData;
import com.cornor.gank.model.pojo.GankList;
import com.cornor.gank.ui.view.ICategoryView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/7
 */

public class GankPresenter {
    private ICategoryView iCategoryView;
    private GankModel gankModel;
    public GankPresenter(ICategoryView categoryView){
        this.iCategoryView = categoryView;
        gankModel = new GankModel();
    }

    public void getCategoryData(String type,int page){
        gankModel.getCategoryData(type, page, new Observer<GankList<GankData>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("TAG","onSubscribe");
            }

            @Override
            public void onNext(GankList<GankData> gankList) {
                Log.i("TAG","TAG");
                if(gankList.error){
                   iCategoryView.loadFail("");
                }else {
                    iCategoryView.loadDataSuccess(gankList.results);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.i("TAG","onError");
                iCategoryView.loadFail("加载失败");
                iCategoryView.hideRefresh();
            }

            @Override
            public void onComplete() {
                Log.i("TAG","onComplete");
                iCategoryView.hideRefresh();
            }
        });
    }
}

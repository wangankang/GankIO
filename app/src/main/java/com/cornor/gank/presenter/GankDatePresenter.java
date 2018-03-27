package com.cornor.gank.presenter;

import android.util.Log;

import com.cornor.gank.model.GankModel;
import com.cornor.gank.model.pojo.GankDateContent;
import com.cornor.gank.ui.view.IDateView;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/7
 */

public class GankDatePresenter {
    private IDateView iDateView;
    private GankModel gankModel;
    public GankDatePresenter(IDateView iDateView){
        this.iDateView = iDateView;
        gankModel = new GankModel();
    }

    public void getDateContent(String date){
        iDateView.showRefresh();
        gankModel.getDateContent(date, new Observer<GankDateContent>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GankDateContent dateContent) {
                Log.i("TAG","TAG");
                if(dateContent.error){
                    iDateView.loadFail("");
                }else {
                    iDateView.loadDataSuccess(dateContent);
                }
            }

            @Override
            public void onError(Throwable e) {
                iDateView.loadFail("");
            }

            @Override
            public void onComplete() {
                iDateView.hideRefresh();
            }
        });
    }

}

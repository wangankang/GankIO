package com.cornor.gank.ui.view;

import com.cornor.gank.model.pojo.GankCategory;

import java.util.List;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/7
 */

public interface ICategoryView {
    void showRefresh();
    void hideRefresh();
    void loadDataSuccess(List<GankCategory> data);
    void loadFail(String errMsg);
}

package com.cornor.gank.ui.view;

import com.cornor.gank.model.pojo.GankDateContent;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/7
 */

public interface IDateView {
    void showRefresh();
    void hideRefresh();
    void loadDataSuccess(GankDateContent dateContent);
    void loadFail(String errMsg);
}

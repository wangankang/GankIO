package com.cornor.gank.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/8
 */

public abstract class LinearScrollBottomListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager linearLayoutManager;
    private int totalItemCount;
    private int lastTotalItemCount;
    private int visibleItemCount;
    private int firstVisibleItemIndex;

    private boolean isLoading;
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItemIndex = linearLayoutManager.findFirstVisibleItemPosition();
        visibleItemCount = recyclerView.getChildCount();
        if(isLoading){
            if(totalItemCount > lastTotalItemCount){
                isLoading = false;
            }
        }else{
            if(firstVisibleItemIndex + visibleItemCount >= totalItemCount){
                isLoading = true;
                lastTotalItemCount = totalItemCount;
                onLoadMore(0);
            }
        }
    }

    public abstract void onLoadMore(int currentPage);

}

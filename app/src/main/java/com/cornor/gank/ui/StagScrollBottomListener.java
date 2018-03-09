package com.cornor.gank.ui;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/8
 */

public abstract class StagScrollBottomListener extends RecyclerView.OnScrollListener {
    public static final int PRE_LOAD_SIZE = 4;
    private StaggeredGridLayoutManager layoutManager;
    private boolean isLoading;
    private int totalItemCount;
    private int lastTotalItemCount;
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        layoutManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
        totalItemCount = layoutManager.getItemCount();
        int[] pos  = new int[2];
        layoutManager.findLastCompletelyVisibleItemPositions(pos);
        boolean isBottom = Math.max(pos[0],pos[1]) + PRE_LOAD_SIZE >= (layoutManager.getItemCount() - 1);
        if(isLoading){
            if(totalItemCount > lastTotalItemCount){
                isLoading = false;
            }
        }else {
            if(isBottom){
                isLoading = true;
                lastTotalItemCount = totalItemCount;
                onLoadMore(0);
            }
        }
    }

    public abstract void onLoadMore(int currentPage);

}

package com.cornor.gank.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cornor.gank.R;
import com.cornor.gank.model.OnToTopListener;
import com.cornor.gank.model.pojo.GankData;
import com.cornor.gank.presenter.GankPresenter;
import com.cornor.gank.ui.adapeter.GankGirlsAdapter;
import com.cornor.gank.ui.view.ICategoryView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/8
 */

public class PictureFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,ICategoryView,OnToTopListener{
    @BindView(R.id.sRLyt)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rcyCV)
    RecyclerView recyclerView;


    GankGirlsAdapter girlsAdapter;
    GankPresenter presenter;
    List<GankData> categoryList;
    private int curPage = 1;
    private String type = "福利";
    private boolean isLoadMore = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(presenter == null){
            presenter = new GankPresenter(this);
        }
        categoryList = new ArrayList<>();
        girlsAdapter = new GankGirlsAdapter(getActivity(),categoryList);
    }


    public static PictureFragment newInstance(){
        return new PictureFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data,container,false);
        ButterKnife.bind(this,view);
        swipeRefreshLayout.setOnRefreshListener(this);
        girlsAdapter.setOnGankItemClickListener((category,clickView) ->{
//            Intent intent = new Intent(getActivity(), BrowsePicActivity.class);
//            intent.putExtra("url",category.getUrl());
//            startActivity(intent);
            startPictureActivity(category,clickView);
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(girlsAdapter);
        recyclerView.addOnScrollListener(new StagScrollBottomListener() {
            @Override
            public void onLoadMore(int currentPage) {
                isLoadMore = true;
                presenter.getCategoryData(type,++curPage);
            }
        });
        presenter.getCategoryData(type,curPage);
        return view;
    }

    private void startPictureActivity(GankData category, View transitView) {
        Intent intent = new Intent(getActivity(), BrowsePicActivity.class);
        intent.putExtra("url",category.getUrl());
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                getActivity(), transitView, "picture");
        try {
            ActivityCompat.startActivity(getActivity(), intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        curPage = 1;
        presenter.getCategoryData(type,curPage);
    }

    @Override
    public void showRefresh() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void loadDataSuccess(List<GankData> data) {
        if(!isLoadMore){
            categoryList.clear();
        }
        categoryList.addAll(data);
        girlsAdapter.notifyDataSetChanged();
        isLoadMore = false;
    }

    @Override
    public void loadFail(String errMsg) {
        Toast.makeText(getActivity(),errMsg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void toTop() {
        recyclerView.smoothScrollToPosition(0);
    }
}

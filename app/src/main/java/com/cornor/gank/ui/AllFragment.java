package com.cornor.gank.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cornor.gank.R;
import com.cornor.gank.model.OnToTopListener;
import com.cornor.gank.model.pojo.GankData;
import com.cornor.gank.presenter.GankPresenter;
import com.cornor.gank.ui.adapeter.GankCategoryAdapter;
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

public class AllFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,ICategoryView,OnToTopListener{

    @BindView(R.id.sRLyt)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rcyCV)
    RecyclerView recyclerView;

    GankCategoryAdapter gankCategoryAdapter;
    GankPresenter presenter;
    List<GankData> categoryList;
    private int curPage = 1;
    private String type = "all";
    private boolean isLoadMore = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(presenter == null){
            presenter = new GankPresenter(this);
        }
        categoryList = new ArrayList<>();
        if(getArguments() != null){
            type = getArguments().getString("type");
        }
    }

    public static AllFragment newInstance(String type){
        AllFragment allFragment = new AllFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        allFragment.setArguments(bundle);
        return allFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data,container,false);
        ButterKnife.bind(this,view);
        gankCategoryAdapter = new GankCategoryAdapter(getActivity(),categoryList);
        recyclerView.setAdapter(gankCategoryAdapter);
        gankCategoryAdapter.setOnGankItemClickListener((category, clickView) -> getActivity().startActivity(WebActivity.newIntent(getActivity(), category.getUrl(), category.getDesc())));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        presenter.getCategoryData(type,curPage);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(new LinearScrollBottomListener() {
            @Override
            public void onLoadMore(int currentPage) {
                isLoadMore = true;
                presenter.getCategoryData(type,++curPage);
            }
        });
        return view;
    }

    @Override
    public void showRefresh() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void change(String type){
        isLoadMore = false;
        this.type = type;
        curPage = 1;
        showRefresh();
        presenter.getCategoryData(type,curPage);
    }

    @Override
    public void loadDataSuccess(List<GankData> data) {
        if(!isLoadMore){
            categoryList.clear();
        }
        categoryList.addAll(data);
        gankCategoryAdapter.notifyDataSetChanged();
        isLoadMore = false;
    }

    @Override
    public void loadFail(String errMsg) {
        Toast.makeText(getActivity(),errMsg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRefresh() {
        curPage = 1;
        presenter.getCategoryData(type,curPage);
    }

    @Override
    public void toTop() {
        recyclerView.smoothScrollToPosition(0);
    }
}

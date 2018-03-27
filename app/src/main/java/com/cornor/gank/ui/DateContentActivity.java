package com.cornor.gank.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.cornor.gank.R;
import com.cornor.gank.model.pojo.GankData;
import com.cornor.gank.model.pojo.GankDateContent;
import com.cornor.gank.presenter.GankDatePresenter;
import com.cornor.gank.ui.adapeter.GankDateAdapter;
import com.cornor.gank.ui.view.IDateView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateContentActivity extends AppCompatActivity implements IDateView{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLyt)
    TabLayout tabLayout;
    @BindView(R.id.prg_bar)
    ProgressBar progressBar;
    @BindView(R.id.rcyCV)
    RecyclerView recyclerView;

    String date;
    GankDatePresenter presenter;
    GankDateAdapter adapter;
    List<GankData> gankDataList;
    GankDateContent dateContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_content);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if(presenter == null){
            presenter = new GankDatePresenter(this);
        }
        gankDataList = new ArrayList<>();
        adapter = new GankDateAdapter(this,gankDataList);
        adapter.setOnGankItemClickListener((category, view) -> startActivity(WebActivity.newIntent(DateContentActivity.this, category.getUrl(), category.getDesc())));
        date = getIntent().getStringExtra("date");
        actionBar.setTitle(date);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fillData(tab.getText().toString());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        presenter.getDateContent(date);
    }

    private void fillData(String text) {
        gankDataList.clear();
        switch (text){
            case "Android":{
                gankDataList.addAll(dateContent.results.androidList);
                break;
            }
            case "iOS":{
                gankDataList.addAll(dateContent.results.iOSList);
                break;
            }
            case "前端":{
                gankDataList.addAll(dateContent.results.h5List);
                break;
            }
            case "瞎推荐":{
                gankDataList.addAll(dateContent.results.recomList);
                break;
            }
            case "拓展资源":{
                gankDataList.addAll(dateContent.results.expandList);
                break;
            }

            case "App":{
                gankDataList.addAll(dateContent.results.appList);
                break;
            }

            case "福利":{
                gankDataList.addAll(dateContent.results.girlList);
                break;
            }
            case "休息视频":{
                gankDataList.addAll(dateContent.results.videoList);
                break;
            }
            default:
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showRefresh() {
        if(!progressBar.isShown()){
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideRefresh() {
        if(progressBar.isShown()){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadDataSuccess(GankDateContent dateContent) {
        this.dateContent = dateContent;
        for (String tab : dateContent.category) {
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        if(tabLayout.getTabCount() > 0){
            fillData(dateContent.category.get(tabLayout.getSelectedTabPosition()));
        }else {
            tabLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadFail(String errMsg) {

    }
}

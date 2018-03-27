package com.cornor.gank.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;

import com.bumptech.glide.Glide;
import com.cornor.gank.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowsePicActivity extends AppCompatActivity {

    @BindView(R.id.imgV_girl)
    AppCompatImageView imgVGirl;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    String url;

    protected boolean mIsHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_pic);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ViewCompat.setTransitionName(imgVGirl, "girls");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_24dp);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            appBarLayout.setElevation(10.6f);
        }
        appBarLayout.setAlpha(0.5f);

        actionBar.setTitle("");
        url = getIntent().getStringExtra("url");
        imgVGirl.setOnClickListener((view)-> hideOrShowToolbar());
        Glide.with(this)
                .load(url)
                .into(imgVGirl);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    protected void hideOrShowToolbar() {
        appBarLayout.animate()
                .translationY(mIsHidden ? 0 : -appBarLayout.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsHidden = !mIsHidden;
    }

}

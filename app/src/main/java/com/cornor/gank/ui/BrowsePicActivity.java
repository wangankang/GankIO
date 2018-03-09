package com.cornor.gank.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.cornor.gank.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrowsePicActivity extends AppCompatActivity {

    @BindView(R.id.imgV_girl)
    AppCompatImageView imgVGirl;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_pic);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra("url");
        Glide.with(this)
                .load(url)
                .into(imgVGirl);
    }
}

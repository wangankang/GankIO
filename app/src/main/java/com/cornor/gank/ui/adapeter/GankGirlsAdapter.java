package com.cornor.gank.ui.adapeter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.cornor.gank.R;
import com.cornor.gank.model.pojo.GankData;
import com.cornor.gank.ui.widget.RatioImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/8
 */

public class GankGirlsAdapter extends RecyclerView.Adapter<GankGirlsAdapter.GirlsHolder> {

    private List<GankData> list;
    private Context context;
    private GankCategoryAdapter.OnGankItemClickListener onGankItemClickListener;

    public void setOnGankItemClickListener(GankCategoryAdapter.OnGankItemClickListener onGankItemClickListener) {
        this.onGankItemClickListener = onGankItemClickListener;
    }

    public GankGirlsAdapter(Context context, List<GankData> list) {
        this.list = list;
        this.context = context;
    }
    @Override
    public GankGirlsAdapter.GirlsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_girls, parent, false);
        return new GankGirlsAdapter.GirlsHolder(v,onGankItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GirlsHolder holder, int position) {
        GankData gankData = list.get(position);
        holder.category = gankData;
        Glide.with(context)
                .load(gankData.getUrl())
                .into(holder.imgVGirls);
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class GirlsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imgV_girls)
        RatioImageView imgVGirls;
        public GankCategoryAdapter.OnGankItemClickListener onGankItemClickListener;
        public GankData category;

        public GirlsHolder(View itemView,GankCategoryAdapter.OnGankItemClickListener onGankItemClickListener) {
            super(itemView);
            this.onGankItemClickListener = onGankItemClickListener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            imgVGirls.setOriginalSize(100,100);
        }


        @Override public void onClick(View v) {
            if(onGankItemClickListener != null){
                onGankItemClickListener.OnItemClick(category,imgVGirls);
            }
        }
    }
}

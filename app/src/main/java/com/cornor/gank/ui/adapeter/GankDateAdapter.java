package com.cornor.gank.ui.adapeter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cornor.gank.R;
import com.cornor.gank.model.pojo.GankData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/8
 */

public class GankDateAdapter extends RecyclerView.Adapter<GankDateAdapter.DateContentHolder> {

    public static final int VIEW_TYPE_ITEM  = 0;
    public static final int VIEW_TYPE_EMPTY  = 1;

    private List<GankData> list;
    private Context context;
    private OnGankItemClickListener onGankItemClickListener;

    public void setOnGankItemClickListener(OnGankItemClickListener onGankItemClickListener) {
        this.onGankItemClickListener = onGankItemClickListener;
    }

    public interface OnGankItemClickListener{
        void OnItemClick(GankData category, View view);
    }

    public GankDateAdapter(Context context, List<GankData> list) {
        this.list = list;
        this.context = context;
    }


    @Override
    public GankDateAdapter.DateContentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_date_content, parent, false);
        return new GankDateAdapter.DateContentHolder(v,onGankItemClickListener);
    }

    @Override
    public void onBindViewHolder(DateContentHolder holder, int position) {
        GankData gankData = list.get(position);
        holder.gankData = gankData;
        holder.txtVTitle.setText(gankData.getDesc());
    }


    @Override
    public int getItemCount() {
        if(list == null){
           return 0;
        }
        return list.size();

    }

    static class DateContentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.title)
        TextView txtVTitle;
        public OnGankItemClickListener onGankItemClickListener;
        public GankData gankData;

        public DateContentHolder(View itemView,OnGankItemClickListener onGankItemClickListener) {
            super(itemView);
            this.onGankItemClickListener = onGankItemClickListener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        @Override public void onClick(View v) {
            if(onGankItemClickListener != null){
                onGankItemClickListener.OnItemClick(gankData,v);
            }
        }
    }
}

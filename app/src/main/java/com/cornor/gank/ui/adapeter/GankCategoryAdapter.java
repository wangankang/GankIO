package com.cornor.gank.ui.adapeter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cornor.gank.R;
import com.cornor.gank.model.pojo.GankCategory;
import com.cornor.gank.util.DateUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/8
 */

public class GankCategoryAdapter extends RecyclerView.Adapter<GankCategoryAdapter.CategoryHolder> {

    private List<GankCategory> list;
    private Context context;
    private OnGankItemClickListener onGankItemClickListener;

    public void setOnGankItemClickListener(OnGankItemClickListener onGankItemClickListener) {
        this.onGankItemClickListener = onGankItemClickListener;
    }

    public interface OnGankItemClickListener{
        void OnItemClick(GankCategory category);
    }

    public GankCategoryAdapter(Context context, List<GankCategory> list) {
        this.list = list;
        this.context = context;
    }
    @Override
    public GankCategoryAdapter.CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_category, parent, false);
        return new GankCategoryAdapter.CategoryHolder(v,onGankItemClickListener);
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, int position) {
        GankCategory gankCategory = list.get(position);
        holder.category = gankCategory;
        holder.txtVDate.setText(DateUtil.fromDate(gankCategory.getCreatedat()));
        holder.txtVTitle.setText(gankCategory.getDesc());
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    static class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txtV_contentTitle)
        TextView txtVTitle;
        @BindView(R.id.txtV_date)
        TextView txtVDate;
        public OnGankItemClickListener onGankItemClickListener;
        public GankCategory category;

        public CategoryHolder(View itemView,OnGankItemClickListener onGankItemClickListener) {
            super(itemView);
            this.onGankItemClickListener = onGankItemClickListener;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        @Override public void onClick(View v) {
            if(onGankItemClickListener != null){
                onGankItemClickListener.OnItemClick(category);
            }
        }
    }
}

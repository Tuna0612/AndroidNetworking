package com.sean.anw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.sean.anw.LoadHolder;
import com.sean.anw.R;
import com.sean.anw.holder.LatestHolder;
import com.sean.anw.model.Photo;


import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static ClickListener clickListener;
    private List<Photo> mediaList;
    private Context context;
    int ITEM = 1;
    int LOAD_MORE = 2;

    public boolean isOnLoadMore() {
        return onLoadMore;
    }

    public void setOnLoadMore(boolean onLoadMore) {
        this.onLoadMore = onLoadMore;
    }

    private boolean onLoadMore = true;

    public Adapter(List<Photo> mediaList, Context context) {
        this.mediaList = mediaList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        if (i == ITEM) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View item = layoutInflater.inflate(R.layout.item, viewGroup, false);
            return new LatestHolder(item);
        } else if (i == LOAD_MORE) {
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            View view = layoutInflater.inflate(R.layout.loadmore, viewGroup, false);
            return new LoadHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof LatestHolder) {
            try {
                Glide
                        .with(context)
                        .load(mediaList.get(position).getUrlL())
                        .into(((LatestHolder) holder).imgView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (holder instanceof LoadHolder) {
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (onLoadMore) {
            if (position == mediaList.size() - 1) return LOAD_MORE;
            else return ITEM;
        } else return ITEM;

    }

    @Override
    public int getItemCount() {
        return mediaList == null ? 0 : mediaList.size();
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        Adapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}

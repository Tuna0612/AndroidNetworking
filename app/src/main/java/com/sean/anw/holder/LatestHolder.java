package com.sean.anw.holder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sean.anw.R;

public class LatestHolder extends RecyclerView.ViewHolder {

    public ImageView imgView;
    public LatestHolder(@NonNull View itemView) {
        super(itemView);
        imgView = itemView.findViewById(R.id.imgPost);
    }
}

package com.sean.anw.holder;

import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.sean.anw.R;

public class LoadHolder extends RecyclerView.ViewHolder {
    //public ProgressBar progressBar;
    public LoadHolder(@NonNull View itemView) {
        super(itemView);
//        progressBar = itemView.findViewById(R.id.spin_kit);
//        Sprite doubleBounce = new DoubleBounce();
//        progressBar.setIndeterminateDrawable(doubleBounce);
    }
}

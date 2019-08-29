package com.sean.anw;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.sean.anw.adapter.Adapter;
import com.sean.anw.model.Photo;
import com.sean.anw.model.Wallpaper;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<Photo> mediaList;
    Adapter mediaAdapter;

    private RecyclerView lvList;

    private SwipeRefreshLayout swipe;

    private int page = 1;
    private int per_page = 10;
    SpotsDialog spotsDialog;

    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = findViewById(R.id.swipe);
        spotsDialog = new SpotsDialog(MainActivity.this);
        spotsDialog.show();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                simulateFetchingData();
            }
        });
        swipe.setColorSchemeResources(R.color.blue,R.color.red,R.color.yello,R.color.green);

        final RecyclerView recyclerView = findViewById(R.id.lvPhoto);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        mediaList = new ArrayList<>();
        mediaAdapter = new Adapter(mediaList, this);
        recyclerView.setAdapter(mediaAdapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                getData(page+1,per_page);
            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String sourceUrl = mediaList.get(position).getUrlL();
                Intent intent = new Intent(getApplicationContext(), ViewImageActivity.class);
                intent.putExtra("sourceUrl", sourceUrl);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getData(page,per_page);
    }





    public void getData(int page,int per_page) {
        Retrofit.getInstance().getPhoto("flickr.groups.pools.getPhotos","89653c0e8feaab4d132f1f5b21bf7753","14611226@N25","license,tags,media,url_q,url_n,url_c,url_l,url_h,url_k,url_o",per_page,page,"json",1).enqueue(new Callback<Wallpaper>() {
            @Override
            public void onResponse(@NonNull Call<Wallpaper> call, @NonNull Response<Wallpaper> response) {
                spotsDialog.dismiss();
                swipe.setRefreshing(false);
                if (response.body().getPhotos().getPhoto().size() == 0) {

                    mediaAdapter.setOnLoadMore(true);
                }
                mediaList.addAll(response.body().getPhotos().getPhoto());
                mediaAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(@NonNull Call<Wallpaper> call, @NonNull Throwable t) {
                spotsDialog.dismiss();
                Log.e("error======", t.getMessage() + "");
                swipe.setRefreshing(false);
            }
        });
    }

    private void simulateFetchingData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaList.clear();
                getData(1,10);
                swipe.setRefreshing(false);
                mediaAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Refresh Finished!", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}

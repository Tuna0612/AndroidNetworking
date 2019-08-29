package com.sean.anw;


import com.sean.anw.model.Wallpaper;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("services/rest")
    Call<Wallpaper> getPhoto(@Query("method") String method,
                             @Query("api_key") String api_key,
                             @Query("group_id") String user_id,
                             @Query("extras") String extras,
                             @Query("per_page") int per_page,
                             @Query("page") int page,
                             @Query("format") String format,
                             @Query("nojsoncallback") int nojsoncallback);
}

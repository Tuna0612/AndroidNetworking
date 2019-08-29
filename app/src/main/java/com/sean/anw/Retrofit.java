package com.sean.anw;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private static Service polyService;

    public static Service getInstance(){

        if (polyService == null){
            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://www.flickr.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            polyService = retrofit.create(Service.class);
        }
        return polyService;
    }
}

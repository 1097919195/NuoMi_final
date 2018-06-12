package com.example.shoppingcart;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by castl on 2016/5/13.
 */
public class NetWork {
    private static MyApi myApi;


    public static MyApi getMyApi() {
        if (myApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(OkHttpClient.okhttp())
                    .baseUrl(GankUrl.MY_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            myApi = retrofit.create(MyApi.class);
        }
        return myApi;
    }
}

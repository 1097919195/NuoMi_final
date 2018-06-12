package com.example.shoppingcart;


import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ZZN on 2017/8/25.
 */

public interface MyApi {
//    http://jisutqybmf.market.alicloudapi.com/weather/query?location=29.982631,120.612979
//    http://106.15.198.49/nuomi/nuomi.php
//    @FormUrlEncoded
    @POST("menu.php")
        Call<GoodsItem> getUserinfo(
//                @Field("userid") String userid,
//                                   @Field("password") String password
    );
}

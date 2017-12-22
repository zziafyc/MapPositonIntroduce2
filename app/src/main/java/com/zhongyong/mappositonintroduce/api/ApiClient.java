package com.zhongyong.mappositonintroduce.api;

import com.zhongyong.mappositonintroduce.base.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by fyc on 2017/11/16.
 */

public class ApiClient {

    // 请求超时时间
    private static final int DEFAULT_TIMEOUT = 30;
    private static ApiService mApiService;

    public static ApiService getApiService() {
        if (mApiService == null) {
            //手动创建一个OkHttpClient并设置超时时间
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            Retrofit retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build();
            mApiService = retrofit.create(ApiService.class);

        }
        return mApiService;
    }


}

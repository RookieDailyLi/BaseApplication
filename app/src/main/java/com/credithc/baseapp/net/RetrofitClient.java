package com.credithc.baseapp.net;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author liyong
 * @date 2019/10/18 9:35
 * @description
 */
public class RetrofitClient {

    private Retrofit retrofit;
    private HashMap<String, Retrofit> retrofitMap = new HashMap<>();

    private static class Creator {
        private static RetrofitClient instance = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return Creator.instance;
    }

    private synchronized Retrofit getRetrofit(String url) {
        Retrofit retrofit = retrofitMap.get(url);
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            retrofitMap.put(url, retrofit);
        }
        return retrofit;
    }

    public RetrofitService createService() {
        return createRetrofitService(RetrofitService.class, "");
    }

    public <T> T createRetrofitService(Class<T> cls, String baseUrl) {
        return getRetrofit(baseUrl).create(cls);
    }
}

package com.credithc.baseapp.net;

import com.credithc.baseapp.net.config.ServerHelper;
import com.credithc.netlib.okhttp.OkHttpInstance;
import com.credithc.netlib.retrofit.converter.NetConverterFactory;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author liyong
 * @date 2019/10/18 9:35
 * @description
 */
public class RetrofitClient {

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
                    .client(OkHttpInstance.getInstance().getOkHttpClient())
                    .addConverterFactory(NetConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            retrofitMap.put(url, retrofit);
        }
        return retrofit;
    }

    public static RetrofitService createService() {
        return createRetrofitService(RetrofitService.class, ServerHelper.getInstance().getHostURL());
    }

    public static <T> T createRetrofitService(Class<T> cls, String baseUrl) {
        return getInstance().getRetrofit(baseUrl).create(cls);
    }
}

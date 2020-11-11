package com.credithc.baseapp.net;

import com.credithc.baseapp.net.config.ServerHelper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

/**
 * @author liyong
 * @date 2020/9/14 11:11
 * @description
 */
public class RetrofitHelper {

    private static Map<String, Retrofit> retrofitHashMap = new HashMap<>();

    public static void addRetrofit(String url, Retrofit retrofit) {
        retrofitHashMap.put(url, retrofit);
    }

    public static Retrofit getRetrofit(String url) {
        return retrofitHashMap.get(url);
    }

    public static RetrofitApiService createDefaultRetrofitApi() {
        return createRetrofitApi(ServerHelper.getInstance().getHostURL(), RetrofitApiService.class);
    }

    public static <T> T createRetrofitApi(String url, Class<T> objCls) {
        Retrofit retrofit = getRetrofit(url);
        if (retrofit == null) {
            throw new RuntimeException("Retrofit实例未初始化");
        }

        return retrofit.create(objCls);
    }

}

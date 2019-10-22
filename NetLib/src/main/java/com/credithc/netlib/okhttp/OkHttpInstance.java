package com.credithc.netlib.okhttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author liyong
 * @date 2019/10/16 11:33
 * @description
 */
public class OkHttpInstance {

    private static OkHttpInstance instance;
    private OkHttpClient okHttpClient;
    public static OkHttpClient.Builder defaultBuilder;

    static {
        defaultBuilder = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
    }

    public static OkHttpInstance getInstance() {
        if (instance == null) {
            synchronized (OkHttpInstance.class) {
                if (instance == null) {
                    instance = new OkHttpInstance();
                }
            }
        }
        return instance;
    }

    public void init(OkHttpClient.Builder builder) {
        okHttpClient = builder.build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}

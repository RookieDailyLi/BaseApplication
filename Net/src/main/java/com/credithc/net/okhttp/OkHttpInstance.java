package com.credithc.net.okhttp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author liyong
 * @date 2019/10/16 11:33
 * @description
 */
public class OkHttpInstance {
    public static OkHttpClient.Builder defaultBuilder;

    static {
        defaultBuilder = new OkHttpClient.Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
    }

    private OkHttpInstance() {
    }

    public static OkHttpInstance newInstance() {
        return new OkHttpInstance();
    }

    public OkHttpClient createOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }
}

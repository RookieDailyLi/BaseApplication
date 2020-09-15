package com.credithc.net.retrofit.service;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author liyong
 * @date 2019/10/18 9:35
 * @description
 */
public class ApiService<T> {
    String baseUrl;
    Class<T> serviceCls;
    OkHttpClient client;
    Converter.Factory convertFactory;
    CallAdapter.Factory callAdapterFactory;

    private ApiService() {

    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setServiceCls(Class<T> serviceCls) {
        this.serviceCls = serviceCls;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public void setConvertFactory(Converter.Factory convertFactory) {
        this.convertFactory = convertFactory;
    }

    public void setCallAdapterFactory(CallAdapter.Factory callAdapterFactory) {
        this.callAdapterFactory = callAdapterFactory;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public Class<T> getServiceCls() {
        return serviceCls;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public Converter.Factory getConvertFactory() {
        return convertFactory;
    }

    public CallAdapter.Factory getCallAdapterFactory() {
        return callAdapterFactory;
    }

    public T createService() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(convertFactory)
                .addCallAdapterFactory(callAdapterFactory)
                .build()
                .create(serviceCls);

    }

    public static class Builder<T> {
        String baseUrl;
        Class<T> serviceCls;
        OkHttpClient client;
        Converter.Factory convertFactory;
        CallAdapter.Factory callAdapterFactory = RxJava2CallAdapterFactory.create();


        public Builder baseUrl(String url) {
            this.baseUrl = url;
            return this;
        }

        public Builder service(Class<T> service) {
            this.serviceCls = service;
            return this;
        }

        public Builder client(OkHttpClient client) {
            this.client = client;
            return this;
        }

        public Builder converterFactory(Converter.Factory factory) {
            this.convertFactory = factory;
            return this;
        }

        public ApiService build() {
            ApiService<T> apiService = new ApiService<>();
            apiService.setBaseUrl(baseUrl);
            apiService.setServiceCls(serviceCls);
            apiService.setClient(client);
            apiService.setConvertFactory(convertFactory);
            apiService.setCallAdapterFactory(callAdapterFactory);
            return apiService;
        }
    }
}

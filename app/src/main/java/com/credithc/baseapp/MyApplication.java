package com.credithc.baseapp;

import android.app.Application;
import android.content.Context;

import com.credithc.baseapp.helper.HotFixHelper;
import com.credithc.baseapp.net.RetrofitHelper;
import com.credithc.baseapp.net.config.ServerHelper;
import com.credithc.baseapp.net.interceptor.CommonHeaderInterceptor;
import com.credithc.baseapp.net.interceptor.RequestEncryptInterceptor;
import com.credithc.baseapp.net.interceptor.ResponseDecryptInterceptor;
import com.credithc.baseapp.net.interceptor.ResponseGzipInterceptor;
import com.credithc.baseapp.util.GlobalTools;
import com.credithc.common.GlobalContext;
import com.credithc.common.util.AppUtil;
import com.credithc.mvp.lifecycle.ActivityLifecyclePublisher;
import com.credithc.mvp.lifecycle.FragmentLifecyclePublisher;
import com.credithc.mvp.lifecycle.IActLifeSubscriber;
import com.credithc.mvp.lifecycle.IFragLifeSubscriber;
import com.credithc.net.okhttp.OkHttpInstance;
import com.credithc.net.retrofit.factory.NetConverterFactory;

import androidx.multidex.MultiDex;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author liyong
 * @date 2019/10/21 14:27
 * @description
 */
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {//兼容5.0以下系统
        super.attachBaseContext(base);
        MultiDex.install(base);
        HotFixHelper.install();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalContext.setContext(this);
        if (AppUtil.isInitAble()) {
            onceInstall();
            delayInstall();
        }
    }

    protected void onceInstall() {
        GlobalTools.installLog(BuildConfig.SHOW_LOG);
        HotFixHelper.init(this);
    }

    protected void delayInstall() {
        initOkHttp();
    }

    @SuppressWarnings("unchecked")
    protected void initOkHttp() {
        OkHttpClient okHttpClient = OkHttpInstance.newInstance().createOkHttpClient(OkHttpInstance.defaultBuilder
                .addInterceptor(new CommonHeaderInterceptor())
                .addInterceptor(new RequestEncryptInterceptor())  // 请求参数加密拦截器
                .addNetworkInterceptor(new ResponseDecryptInterceptor()) // 响应解密拦截器
                .addNetworkInterceptor(new ResponseGzipInterceptor()));// 响应解密拦截器);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerHelper.getInstance().getHostURL())
                .client(okHttpClient)
                .addConverterFactory(NetConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        RetrofitHelper.addRetrofit(ServerHelper.getInstance().getHostURL(), retrofit);
    }

    protected final void addActivityLifeCycle(IActLifeSubscriber activityLife) {
        ActivityLifecyclePublisher.addSubscriber(activityLife);
    }

    protected final void addFragmentLifeCycle(IFragLifeSubscriber iFragmentLife) {
        FragmentLifecyclePublisher.addSubscriber(iFragmentLife);
    }
}

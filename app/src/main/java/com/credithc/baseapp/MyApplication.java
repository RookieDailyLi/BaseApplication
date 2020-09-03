package com.credithc.baseapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.credithc.baseapp.helper.HotFixHelper;
import com.credithc.baseapp.net.interceptor.CommonHeaderInterceptor;
import com.credithc.baseapp.net.interceptor.RequestEncryptInterceptor;
import com.credithc.baseapp.net.interceptor.ResponseDecryptInterceptor;
import com.credithc.baseapp.net.interceptor.ResponseGzipInterceptor;
import com.credithc.baseapp.util.GlobalTools;
import com.credithc.commonlib.GlobalContext;
import com.credithc.commonlib.util.AppUtil;
import com.credithc.mvp.ui.lifecycle.ActivityLifecycle;
import com.credithc.mvp.ui.lifecycle.CommonActivityLifeCycle;
import com.credithc.mvp.ui.lifecycle.FragmentLifecycle;
import com.credithc.mvp.ui.lifecycle.IActivityLifecycle;
import com.credithc.mvp.ui.lifecycle.IFragmentLifecycle;
import com.credithc.netlib.okhttp.OkHttpInstance;

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
        addActivityLifeCycle(new CommonActivityLifeCycle());
    }

    protected void delayInstall() {
        initOkHttp();
    }

    protected void initOkHttp() {
        OkHttpInstance.getInstance().init(OkHttpInstance.defaultBuilder
                .addInterceptor(new CommonHeaderInterceptor())
                .addInterceptor(new RequestEncryptInterceptor())  // 请求参数加密拦截器
                .addNetworkInterceptor(new ResponseDecryptInterceptor()) // 响应解密拦截器
                .addNetworkInterceptor(new ResponseGzipInterceptor()));// 响应解密拦截器);
    }

    protected final void addActivityLifeCycle(IActivityLifecycle activityLife) {
        ActivityLifecycle.addSubscriber(activityLife);
    }

    protected final void addFragmentLifeCycle(IFragmentLifecycle iFragmentLife) {
        FragmentLifecycle.addSubscriber(iFragmentLife);
    }
}

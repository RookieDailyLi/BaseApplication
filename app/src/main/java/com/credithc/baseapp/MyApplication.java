package com.credithc.baseapp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.credithc.baseapp.net.interceptor.CommonHeaderInterceptor;
import com.credithc.baseapp.net.interceptor.RequestEncryptInterceptor;
import com.credithc.baseapp.net.interceptor.ResponseDecryptInterceptor;
import com.credithc.baseapp.util.GlobalTools;
import com.credithc.commonlib.GlobalContext;
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
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalContext.setContext(this);
        GlobalTools.installLog(BuildConfig.DEBUG);
        OkHttpInstance.getInstance().init(OkHttpInstance.defaultBuilder
                .addInterceptor(new CommonHeaderInterceptor())
                .addInterceptor(new RequestEncryptInterceptor())
                .addNetworkInterceptor(new ResponseDecryptInterceptor()));
    }
}

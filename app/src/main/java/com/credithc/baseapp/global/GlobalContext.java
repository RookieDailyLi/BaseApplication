package com.credithc.baseapp.global;


import android.app.Application;

/**
 * @author liyong
 * @date 2019/10/21
 * @des
 */
public class GlobalContext {

    private static Application mContext;


    public static <T extends Application> void setContext(T context) {
        mContext = context;
    }

    public static <T extends Application> T getContext() {
        if (mContext == null) {
            throw new NullPointerException("请在 MyApplication 初始化 时调用 setContext 方法");
        }
        return (T) mContext;
    }
}

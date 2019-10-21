package com.credithc.baseapp;

import com.credithc.baseapp.server.config.DebugServerConfig;

/**
 * @author liyong
 * @date 2019/10/21 14:33
 * @description
 */
public class DebugMyApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        DebugServerConfig.config(DebugServerConfig.ServerTestType);
    }
}

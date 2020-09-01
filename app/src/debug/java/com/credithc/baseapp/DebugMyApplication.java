package com.credithc.baseapp;

import com.credithc.baseapp.server.ServerManager;

/**
 * @author liyong
 * @date 2019/10/21 14:33
 * @description
 */
public class DebugMyApplication extends MyApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ServerManager.config(ServerManager.ServerTestType);
    }
}

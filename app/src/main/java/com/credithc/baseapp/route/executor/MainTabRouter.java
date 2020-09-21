package com.credithc.baseapp.route.executor;

import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.credithc.baseapp.route.RouterConstants;
import com.credithc.common.util.ActivityManager;

import java.util.Map;


/**
 * @author liyong
 * @date 2019/12/13
 * @des
 */
public class MainTabRouter implements IAppRouter {
    public static String POSITION = "position";

    @Override
    public void navigation(String url, Map<String, String> params) {
        navigation(ActivityManager.current(), url, params);
    }

    @Override
    public void navigation(Context context, String url, Map<String, String> params) {
        int index = RouterConstants.MAIN_HOME;
        switch (url) {
            case RouterConstants.ROUTER_NAME_HOME_PAGE_KEY:
                index = RouterConstants.MAIN_HOME;
                break;
            case RouterConstants.ROUTER_NAME_PROJECT_PAGE_KEY:
                index = RouterConstants.MAIN_PROJECT;
                break;
            case RouterConstants.ROUTER_NAME_MINE_PAGE_KEY:
                index = RouterConstants.MAIN_MINE;
                break;
        }
        ARouter.getInstance()
                .build(RouterConstants.ROUTER_NAME_MAIN_KEY)
                .withInt(POSITION, index)
                .navigation();
    }
}

package com.credithc.baseapp.route;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 路由配置类
 */
public class RouterHelper {

    public static void init(final Application application) {
        //初始化ARouter路由器
        ARouter.init(application);
    }


}

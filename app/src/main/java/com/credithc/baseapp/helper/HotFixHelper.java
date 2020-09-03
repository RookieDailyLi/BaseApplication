package com.credithc.baseapp.helper;

import android.app.Application;

import com.credithc.baseapp.BuildConfig;
import com.credithc.commonlib.util.ChannelUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * @author liyong
 * @date 2020/9/2
 * @des
 */
public class HotFixHelper {

    public static void init(Application application) {
        Bugly.setAppChannel(application, ChannelUtils.getChannel("baseApp"));
        Bugly.init(application, BuildConfig.Bugly_ID, false);
        Bugly.setIsDevelopmentDevice(application, isDevelopmentDevice());
    }

    public static void install() {
        if (isDevelopmentDevice()) {
            // Debug 包不使用热修复
            return;
        }
        Beta.installTinker();
    }

    /**
     * 是否标记为开发测试设备
     *
     * @return true 标记
     */
    private static boolean isDevelopmentDevice() {
        return BuildConfig.DEBUG;
    }

}

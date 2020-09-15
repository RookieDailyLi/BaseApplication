package com.credithc.baseapp.util;

import android.os.Handler;

import com.credithc.common.util.CheckApk;
import com.credithc.common.util.LogUtil;
import com.credithc.common.util.ToastUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
*@author liyong
*@date 2019/10/22
*@des
*/
public class GlobalTools {

    public static class SP {
        public static final String GESTURES_PASSWORD = "gestures_password";
    }

    // 日志打印
    public static void installLog(boolean showLog) {
        LogUtil.setShowLog(showLog);
        if (showLog) {
            FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                    .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                    .methodCount(2)         // (Optional) How many method line to show. Default 2
                    .methodOffset(1)        // (Optional) Hides internal method calls up to offset. Default 5
                    .tag("BaseApp--->")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                    .build();

            Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
                @Override
                public boolean isLoggable(int priority, String tag) {
                    return true;
                }
            });
        }

    }

    // 校验apk签名
    public static void checkApkSign(String singInfoKey) {
        boolean right = CheckApk.checkSign(singInfoKey);
        if (right) {
            return;
        }
        ToastUtil.showToast("安装包签名异常，程序即将退出");
        new Handler().postDelayed(new Runnable() {
            public void run() {
                // 退出程序
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        }, 3000);
    }
}

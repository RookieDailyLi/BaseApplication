package com.credithc.baseapp.route.util;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.Consts;
import com.credithc.baseapp.BuildConfig;
import com.credithc.common.util.LogUtil;
import com.credithc.common.util.StringUtil;

import androidx.annotation.Nullable;


/**
 * Created by lwj on 2018/11/2.
 * lwjfork@gmail.com
 */
public class RouterHandler {


    public static void onLost(@Nullable Context context, Postcard postcard) {
        // nothing to do
    }


    public static void debug(String msg, Object... args) {
        if (BuildConfig.SHOW_LOG) {
            ARouter.logger.debug(Consts.TAG, StringUtil.getFormatStr(msg, args));
        }
    }


    public static void info(String msg, Object... args) {
        if (BuildConfig.SHOW_LOG) {
            ARouter.logger.info(Consts.TAG, StringUtil.getFormatStr(msg, args));
        }
    }


    public static void warning(String msg, Object... args) {
        if (BuildConfig.SHOW_LOG) {
            ARouter.logger.warning(Consts.TAG, StringUtil.getFormatStr(msg, args));
        }
    }


    public static void error(String msg, Object... args) {
        if (BuildConfig.SHOW_LOG) {
            ARouter.logger.error(Consts.TAG, StringUtil.getFormatStr(msg, args));
        }
    }


    public static void printException(Exception e) {
        LogUtil.e("Router error %s", e.getMessage());
    }
}

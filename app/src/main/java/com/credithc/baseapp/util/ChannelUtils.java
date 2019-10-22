package com.credithc.baseapp.util;

import android.app.Application;
import android.text.TextUtils;

import com.credithc.commonlib.GlobalContext;
import com.meituan.android.walle.WalleChannelReader;

/**
 * Created by Administrator on 2018/3/7.
 */

public class ChannelUtils {
    private static String channel;

    public static String getChannel() {
        return getChannel(GlobalContext.getContext());
    }

    public static String getChannel(Application application) {

        if (TextUtils.isEmpty(channel)) {
            channel = WalleChannelReader.getChannel(application);
        }
        if (TextUtils.isEmpty(channel)) {
            channel = "hengmall";
        }
        return channel;
    }
}

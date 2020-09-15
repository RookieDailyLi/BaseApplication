package com.credithc.common.util;

import android.app.Application;
import android.text.TextUtils;

import com.credithc.common.GlobalContext;
import com.meituan.android.walle.WalleChannelReader;


public class ChannelUtils {
    private static String channel;

    public static String getChannel(String defaultChannel) {
        return getChannel(GlobalContext.getContext(), defaultChannel);
    }

    public static String getChannel(Application application, String defaultChannel) {

        if (TextUtils.isEmpty(channel)) {
            channel = WalleChannelReader.getChannel(application);
        }
        if (TextUtils.isEmpty(channel)) {
            channel = defaultChannel;
        }
        return channel;
    }
}

package com.credithc.common.util;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * @author liyong
 * @date 2020/8/28
 * @des
 */
public final class NetUtil {

    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_2G = 2;
    public static final int TYPE_3G = 3;
    public static final int TYPE_4G = 4;
    public static final int TYPE_5G = 5;

    public static ConnectivityManager getManager() {
        return OSUtils.getSystemService(Service.CONNECTIVITY_SERVICE);
    }

    /**
     * 网络是否可用
     */
    @SuppressWarnings("MissingPermission")
    public static boolean isNetConnected() {
        ConnectivityManager cm = getManager();
        if (cm == null) {
            return false;
        }
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return isConnect(netInfo);
    }

    public static boolean isConnect(NetworkInfo networkInfo) {
        return networkInfo != null && networkInfo.isAvailable();
    }

    @SuppressWarnings("MissingPermission")
    public static int getAPNType() {
        int type = TYPE_UNKNOWN;
        ConnectivityManager cm = getManager();
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (isConnect(networkInfo)) {
                int netType = networkInfo.getType();
                if (netType == ConnectivityManager.TYPE_WIFI) {
                    type = TYPE_WIFI;
                } else {
                    TelephonyManager telephonyManager = OSUtils.getSystemService(Context.TELEPHONY_SERVICE);
                    if (telephonyManager != null && !telephonyManager.isNetworkRoaming()) {
                        int subType = networkInfo.getSubtype();
                        if ((subType == TelephonyManager.NETWORK_TYPE_GPRS
                                || subType == TelephonyManager.NETWORK_TYPE_EDGE
                                || subType == TelephonyManager.NETWORK_TYPE_CDMA
                                || subType == TelephonyManager.NETWORK_TYPE_1xRTT
                                || subType == TelephonyManager.NETWORK_TYPE_IDEN)) {
                            type = TYPE_2G;
                        } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                                || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                                || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                                || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                                || subType == TelephonyManager.NETWORK_TYPE_HSUPA
                                || subType == TelephonyManager.NETWORK_TYPE_HSPA
                                || subType == TelephonyManager.NETWORK_TYPE_EVDO_B
                                || subType == TelephonyManager.NETWORK_TYPE_EHRPD
                                || subType == TelephonyManager.NETWORK_TYPE_HSPAP
                                || subType == TelephonyManager.NETWORK_TYPE_TD_SCDMA
                        ) {
                            type = TYPE_3G;
                        } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {
                            type = TYPE_4G;
                        } else if (subType == TelephonyManager.NETWORK_TYPE_NR) {
                            type = TYPE_5G;
                        } else {
                            type = TYPE_2G; // 默认是 2G
                        }
                    }
                }
            }
        }
        return type;
    }

    public static boolean isWifiConnected() {
        return getAPNType() == TYPE_WIFI;
    }

    public static boolean is2GConnected() {
        return getAPNType() == TYPE_2G;
    }

    public static boolean is3GConnected() {
        return getAPNType() == TYPE_3G;
    }


    public static boolean is4GConnected() {
        return getAPNType() == TYPE_4G;
    }

    public static boolean is5GConnected() {
        return getAPNType() == TYPE_5G;
    }


}


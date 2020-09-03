package com.credithc.baseapp.util;

import android.text.TextUtils;

import com.credithc.commonlib.util.SPManager;

/**
 * @author liyong
 * @date 2019/12/31 18:00
 * @description 获取用户相关信息
 */
public class UserUtil {

    public static final String USER = "user";
    public static final String TOKEN = "token";
    public static final String PHONE = "phone";
    public static final String CODE = "code";
    public static final String UID = "uid";
    public static final String GESTURES_PASSWORD = "GESTURES_PASSWORD";


    /**
     * 是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return !TextUtils.isEmpty(getToken());
    }

    /**
     * 保存uid
     *
     * @param uid
     */
    public static void saveUid(String uid) {
        SPManager.getManager(USER).commitString(UID, uid);
    }

    /**
     * 获取uid
     *
     * @return
     */
    public static String getUId() {
        return SPManager.getManager(USER).getString(UID, "");
    }

    /**
     * 保存token
     *
     * @param token
     */
    public static void saveToken(String token) {
        SPManager.getManager(USER).commitString(TOKEN, token);
    }

    /**
     * 获取token
     *
     * @return
     */
    public static String getToken() {
        return SPManager.getManager(USER).getString(TOKEN);
    }

    /**
     * 保存手机号
     *
     * @param phone
     */
    public static void savePhone(String phone) {
        SPManager.getManager(USER).commitString(PHONE, phone);
    }

    /**
     * 获取用户登录手机号
     *
     * @return
     */
    public static String getUserPhone() {
        return SPManager.getManager(USER).getString(PHONE);
    }

    /**
     * 保存接入一账通时的code
     * @param code
     */
    public static void saveCode(String code){
        SPManager.getManager(USER).commitString(CODE, code);
    }

    /**
     * 获取code
     *
     * @return
     */
    public static String getCode() {
        return SPManager.getManager(USER).getString(CODE);
    }

    /**
     * 清空用户数据
     */
    public static void clearUserData() {
        SPManager.getManager(USER).clear();
    }
}

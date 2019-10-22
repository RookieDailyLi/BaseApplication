package com.credithc.baseapp.util;

import com.credithc.baseapp.constant.SPConstants;
import com.credithc.baseapp.event.LoginEvent;
import com.credithc.commonlib.util.SPManager;
import com.credithc.commonlib.util.StringUtil;

import org.greenrobot.eventbus.EventBus;


/**
 * @author liyong
 * @date 2019/9/3
 * @des 登录工具类
 */
public class LoginUtil {

    public static final String TOKEN = "token";
    public static final String PHONE = "phone";
    public static final String PHONE_MI = "phone_other";

    public static boolean isLogin() {
        return StringUtil.isNotEmpty(getToken());
    }

    /**
     * 用户主动退出登录，清空当前用户数据
     */
    public static void logOut() {
        clearUserData();
        EventBus.getDefault().post(new LoginEvent.LoginOff());
    }

    public static void saveToken(String token) {
        SPManager.getManager(SPConstants.USER).commitString(TOKEN, token);
    }

    /**
     * 获取用户token
     *
     * @return
     */
    public static String getToken() {
        //获取token
        return SPManager.getManager(SPConstants.USER).getString(TOKEN);
    }

    public static void clearUserData() {
        SPManager.getManager(SPConstants.USER).clear();
    }
}

package com.credithc.baseapp.util;

import android.text.TextUtils;


import com.credithc.baseapp.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;


/**
 * @author liyong
 * @date 2019/9/3
 * @des 登录工具类
 */
public class LoginUtil {

    public static boolean isLogin() {
        return !TextUtils.isEmpty(UserUtil.getToken());
    }

    /**
     * 用户主动退出登录，清空当前用户数据
     */
    public static void logOut() {
        UserUtil.clearUserData();
        EventBus.getDefault().post(new LoginEvent.LoginOff());
    }
}

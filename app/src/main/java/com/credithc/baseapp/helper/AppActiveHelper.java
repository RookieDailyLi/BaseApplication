package com.credithc.baseapp.helper;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.credithc.baseapp.util.LoginUtil;
import com.credithc.baseapp.util.UserUtil;
import com.credithc.common.util.BroadcastUtil;
import com.credithc.common.util.SPManager;
import com.credithc.common.widget.DefaultActivityLifecycleCallbacks;

import java.util.Date;


/**
 * Created by lwj on 2018/10/26.
 * lwjfork@gmail.com
 * app 是否处于活动状态
 */
public class AppActiveHelper {
    private static boolean ismActive = false;
    private static Date time_restart;
    private static Date time_stop;
    private static boolean count_start = false;
    private static int mActivityCount;

    public static long EXIT_TIME = 60 * 1000L;// 毫秒  60S

    public static <T extends Application> void install(T application) {
        registerReceiver(application);
        //前后台监听
        //判断程序是否在前台
        application.registerActivityLifecycleCallbacks(new DefaultActivityLifecycleCallbacks() {
            @Override
            public void onActivityStarted(Activity activity) {
                goGesture(activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                onStopChangeState();
            }
        });
    }

    //计算时间差
    public static long timeCount() {
        if (time_restart != null && time_stop != null) {
            return time_restart.getTime() - time_stop.getTime();
        } else {
            return 0;
        }
    }

    public static void clearTime() {
        time_restart = null;
        time_stop = null;
    }

    public static <T extends Application> void registerReceiver(T application) {
        BroadcastUtil.registerGlobalReceiver(application, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 锁屏监听
                changeActiveStatus(false);

            }
        }, Intent.ACTION_SCREEN_OFF);
    }

    public static boolean isActive() {
        return ismActive;
    }

    public static void changeActiveStatus(boolean isActive) {
        ismActive = isActive;
    }


    public static void goGesture(Activity activity) {
        changeActiveStatus(true);
        if (mActivityCount == 0) {
            mActivityCount++;
            if (count_start) {
                count_start = false;
                time_restart = new Date(System.currentTimeMillis());//获取当前时间
                Long c = AppActiveHelper.timeCount();
                if (LoginUtil.isLogin() && c > EXIT_TIME) {
                    AppActiveHelper.clearTime();
                    if (activity == null) {
                        return;
                    }
                    // 没有设置过手势密码去设置手势密码
                    if (TextUtils.isEmpty(SPManager.getManager(UserUtil.USER).getString(UserUtil.GESTURES_PASSWORD))) {
//                        if (!(activity.getLocalClassName().contains(GesturesSetActivity.class.getSimpleName()))) {
//                            GesturesSetActivity.launch(activity, true);
//                        }
                        return;
                    }
                    // 已经设置去验证手势密码
//                    if (activity.getLocalClassName().contains(GesturesVerifyActivity.class.getSimpleName())) { //  当前就停留在验证手势密码页
//                        return;
//                    }
                    // 去验证手势密码
//                    GesturesVerifyActivity.launch(activity, true);
                }
            }
        } else {
            mActivityCount++;
        }
    }


    public static void onStopChangeState() {
        mActivityCount--;
        if (mActivityCount == 0) {
            time_stop = new Date(System.currentTimeMillis());//获取当前时间
            count_start = true;
        }

        changeActiveStatus(false);
    }
}

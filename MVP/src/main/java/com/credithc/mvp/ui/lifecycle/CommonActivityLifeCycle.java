package com.credithc.mvp.ui.lifecycle;

import android.app.Activity;
import android.view.WindowManager;

import com.credithc.common.util.ActivityManager;
import com.credithc.common.util.AppUtil;
import com.credithc.common.util.LoadingUtil;


/**
*@author liyong
*@date 2020/9/3
*@des
*/
public class CommonActivityLifeCycle implements IActivityLifecycle {

    @Override
    public void onCreate(Activity activity) {
        ActivityManager.push(activity);
    }

    @Override
    public void onResume(Activity activity) {

    }

    @Override
    public void onStart(Activity activity) {

    }

    @Override
    public void onRestart(Activity activity) {

    }

    @Override
    public void onPause(Activity activity) {

    }

    @Override
    public void onStop(Activity activity) {
        LoadingUtil.dismissLoading(activity);
        hideSoftInput(activity);
    }

    @Override
    public void onDestroy(Activity activity) {
        LoadingUtil.pop(activity);
        ActivityManager.pop(activity);
        hideSoftInput(activity);
    }

    @Override
    public void onNewIntent(Activity activity) {

    }


    private void hideSoftInput(Activity activity) {
        if (activity.getWindow() != null) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

    @Override
    public void onWindowFocusChanged(Activity activity, boolean hasFocus) {
        if (!hasFocus) {
            AppUtil.checkHijack();
        }
    }

}

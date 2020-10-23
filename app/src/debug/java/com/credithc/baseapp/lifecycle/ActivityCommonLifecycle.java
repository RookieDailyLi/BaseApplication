package com.credithc.baseapp.lifecycle;

import android.app.Activity;
import android.view.WindowManager;

import com.credithc.mvp.lifecycle.IActLifeSubscriber;
import com.credithc.common.util.ActivityManager;
import com.credithc.common.util.AppUtil;
import com.credithc.common.util.LoadingUtil;


/**
*@author liyong
*@date 2020/9/3
*@des
*/
public class ActivityCommonLifecycle implements IActLifeSubscriber {

    @Override
    public void onCreate(Activity activity) {
        ActivityManager.push(activity);
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

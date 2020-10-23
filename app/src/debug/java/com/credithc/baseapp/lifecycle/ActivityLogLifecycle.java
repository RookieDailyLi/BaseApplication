package com.credithc.baseapp.lifecycle;

import android.app.Activity;
import android.util.Log;

import com.credithc.mvp.lifecycle.IActLifeSubscriber;


/**
*@author liyong
*@date 2020/8/13
*@des
*/

public class ActivityLogLifecycle implements IActLifeSubscriber {

    private String seg = "  ";
    private String TAG = "Activity";


    public ActivityLogLifecycle() {
    }

    public ActivityLogLifecycle(String tag) {
        this.TAG = tag;
    }

    @Override
    public void onCreate(Activity activity) {
        log(activity, "onCreate");
    }

    @Override
    public void onResume(Activity activity) {
        log(activity, "onResume");
    }

    @Override
    public void onStart(Activity activity) {
        log(activity, "onStart");
    }

    @Override
    public void onRestart(Activity activity) {
        log(activity, "onRestart");
    }

    @Override
    public void onPause(Activity activity) {
        log(activity, "onPause");
    }

    @Override
    public void onStop(Activity activity) {
        log(activity, "onStop");
    }

    @Override
    public void onDestroy(Activity activity) {
        log(activity, "onDestroy");
    }

    @Override
    public void onNewIntent(Activity activity) {
        log(activity, "onNewIntent");
    }

    @Override
    public void onWindowFocusChanged(Activity activity, boolean hasFocus) {

    }

    private String getClassName(Activity activity) {
        return activity.getClass().getSimpleName() + seg + "--->";
    }

    @SuppressWarnings("all")
    private void log(Activity activity, String... strings) {
        StringBuilder sb = new StringBuilder(getClassName(activity));
        if (strings != null && strings.length >= 1) {
            int count = strings.length;
            for (int i = 0; i < count; i++) {
                sb.append(seg).append("%s");
            }
        }
        Log.d(TAG, String.format(sb.toString(), (Object[]) strings));
    }
}

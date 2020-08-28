package com.credithc.mvp.ui.lifecycle;

import android.app.Activity;

/**
 * @author liyong
 * @date 2020/8/10
 * @des
 */
public interface IActivityLifecycle {

    void onCreate(Activity activity);

    void onResume(Activity activity);

    void onStart(Activity activity);

    void onRestart(Activity activity);

    void onPause(Activity activity);

    void onStop(Activity activity);

    void onDestroy(Activity activity);

    void onNewIntent(Activity activity);

    void onWindowFocusChanged(Activity activity, boolean hasFocus);
}

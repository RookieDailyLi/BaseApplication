package com.credithc.mvp.lifecycle;

import android.app.Activity;

/**
 * @author liyong
 * @date 2020/8/10
 * @des
 */
public interface IActLifeSubscriber {

    default void onCreate(Activity activity) {
    }

    default void onResume(Activity activity) {
    }

    default void onStart(Activity activity) {
    }

    default void onRestart(Activity activity) {
    }

    default void onPause(Activity activity) {
    }

    default void onStop(Activity activity) {
    }

    default void onDestroy(Activity activity) {
    }

    default void onNewIntent(Activity activity) {
    }

    default void onWindowFocusChanged(Activity activity, boolean hasFocus) {
    }
}

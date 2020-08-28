package com.credithc.mvp.ui.lifecycle;

import android.app.Activity;

import java.util.ArrayList;

/**
*@author liyong
*@date 2020/8/10
*@des
*/
public class ActivityLifecycle {

    private static ArrayList<IActivityLifecycle> lifeCycleSubscribers = new ArrayList<>();

    public static void addSubscriber(IActivityLifecycle life) {
        synchronized (lifeCycleSubscribers) {
            lifeCycleSubscribers.add(life);
        }

    }

    public static void removeSubscriber(IActivityLifecycle life) {
        synchronized (lifeCycleSubscribers) {
            lifeCycleSubscribers.remove(life);
        }
    }

    public static void clearSubscriber() {
        synchronized (lifeCycleSubscribers) {
            lifeCycleSubscribers.clear();
        }

    }

    public static void onCreate(Activity activity) {
        for (IActivityLifecycle IActivityLifecycle : lifeCycleSubscribers) {
            IActivityLifecycle.onCreate(activity);
        }
    }

    public static void onResume(Activity activity) {
        for (IActivityLifecycle IActivityLifecycle : lifeCycleSubscribers) {
            IActivityLifecycle.onResume(activity);
        }
    }

    public static void onStart(Activity activity) {
        for (IActivityLifecycle IActivityLifecycle : lifeCycleSubscribers) {
            IActivityLifecycle.onStart(activity);
        }
    }

    public static void onRestart(Activity activity) {
        for (IActivityLifecycle IActivityLifecycle : lifeCycleSubscribers) {
            IActivityLifecycle.onRestart(activity);
        }
    }

    public static void onPause(Activity activity) {
        for (IActivityLifecycle IActivityLifecycle : lifeCycleSubscribers) {
            IActivityLifecycle.onPause(activity);
        }
    }

    public static void onStop(Activity activity) {
        for (IActivityLifecycle IActivityLifecycle : lifeCycleSubscribers) {
            IActivityLifecycle.onStop(activity);
        }
    }

    public static void onDestroy(Activity activity) {
        for (IActivityLifecycle IActivityLifecycle : lifeCycleSubscribers) {
            IActivityLifecycle.onDestroy(activity);
        }
    }

    public static void onNewIntent(Activity activity) {
        for (IActivityLifecycle IActivityLifecycle : lifeCycleSubscribers) {
            IActivityLifecycle.onNewIntent(activity);
        }
    }


    public static void onWindowFocusChanged(Activity activity, boolean hasFocus) {
        for (IActivityLifecycle IActivityLifecycle : lifeCycleSubscribers) {
            IActivityLifecycle.onWindowFocusChanged(activity, hasFocus);
        }
    }
}

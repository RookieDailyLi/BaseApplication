package com.credithc.mvp.lifecycle;

import android.app.Activity;

import java.util.ArrayList;

/**
*@author liyong
*@date 2020/8/10
*@des
*/
public class ActivityLifecyclePublisher {

    private static ArrayList<IActLifeSubscriber> lifeCycleSubscribers = new ArrayList<>();

    public static void addSubscriber(IActLifeSubscriber life) {
        synchronized (lifeCycleSubscribers) {
            lifeCycleSubscribers.add(life);
        }

    }

    public static void removeSubscriber(IActLifeSubscriber life) {
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
        for (IActLifeSubscriber IActLifeSubscriber : lifeCycleSubscribers) {
            IActLifeSubscriber.onCreate(activity);
        }
    }

    public static void onResume(Activity activity) {
        for (IActLifeSubscriber IActLifeSubscriber : lifeCycleSubscribers) {
            IActLifeSubscriber.onResume(activity);
        }
    }

    public static void onStart(Activity activity) {
        for (IActLifeSubscriber IActLifeSubscriber : lifeCycleSubscribers) {
            IActLifeSubscriber.onStart(activity);
        }
    }

    public static void onRestart(Activity activity) {
        for (IActLifeSubscriber IActLifeSubscriber : lifeCycleSubscribers) {
            IActLifeSubscriber.onRestart(activity);
        }
    }

    public static void onPause(Activity activity) {
        for (IActLifeSubscriber IActLifeSubscriber : lifeCycleSubscribers) {
            IActLifeSubscriber.onPause(activity);
        }
    }

    public static void onStop(Activity activity) {
        for (IActLifeSubscriber IActLifeSubscriber : lifeCycleSubscribers) {
            IActLifeSubscriber.onStop(activity);
        }
    }

    public static void onDestroy(Activity activity) {
        for (IActLifeSubscriber IActLifeSubscriber : lifeCycleSubscribers) {
            IActLifeSubscriber.onDestroy(activity);
        }
    }

    public static void onNewIntent(Activity activity) {
        for (IActLifeSubscriber IActLifeSubscriber : lifeCycleSubscribers) {
            IActLifeSubscriber.onNewIntent(activity);
        }
    }


    public static void onWindowFocusChanged(Activity activity, boolean hasFocus) {
        for (IActLifeSubscriber IActLifeSubscriber : lifeCycleSubscribers) {
            IActLifeSubscriber.onWindowFocusChanged(activity, hasFocus);
        }
    }
}

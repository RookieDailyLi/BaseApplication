package com.credithc.mvp.lifecycle;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

/**
 * @author liyong
 * @date 2020/8/10
 * @des
 */
public class FragmentLifecyclePublisher {

    private static ArrayList<IFragLifeSubscriber> LifeCycleSubscribers = new ArrayList<>();

    public static void addSubscriber(IFragLifeSubscriber life) {
        synchronized (LifeCycleSubscribers) {
            LifeCycleSubscribers.add(life);
        }
    }

    public static void removeSubscriber(IFragLifeSubscriber life) {
        synchronized (LifeCycleSubscribers) {
            LifeCycleSubscribers.remove(life);
        }
    }

    public static void clearSubscriber() {
        synchronized (LifeCycleSubscribers) {
            LifeCycleSubscribers.clear();
        }
    }

    public static void onAttach(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onAttach(fragment);
        }
    }

    public static void onCreate(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onCreate(fragment);
        }
    }

    public static void onCreateView(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onCreateView(fragment);
        }
    }

    public static void onViewCreated(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onViewCreated(fragment);
        }
    }

    public static void onActivityCreated(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onActivityCreated(fragment);
        }
    }

    public static void onStart(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onStart(fragment);
        }
    }

    public static void onResume(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onResume(fragment);
        }
    }

    public static void onPause(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onPause(fragment);
        }
    }

    public static void onStop(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onStop(fragment);
        }
    }

    public static void onDestroyView(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onDestroyView(fragment);
        }
    }

    public static void onDestroy(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onDestroy(fragment);
        }
    }

    public static void onDetach(Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onDetach(fragment);
        }
    }

    public static void setUserVisibleHint(boolean isVisibleToUser, Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.setUserVisibleHint(isVisibleToUser, fragment);
        }
    }

    public static void onHiddenChanged(boolean hidden, Fragment fragment) {
        for (IFragLifeSubscriber IFragLifeSubscriber : LifeCycleSubscribers) {
            IFragLifeSubscriber.onHiddenChanged(hidden, fragment);
        }
    }
}

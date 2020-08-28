package com.credithc.mvp.ui.lifecycle;

import android.support.v4.app.Fragment;

import java.util.ArrayList;

/**
 * @author liyong
 * @date 2020/8/10
 * @des
 */
public class FragmentLifecycle {

    private static ArrayList<IFragmentLifecycle> LifeCycleSubscribers = new ArrayList<>();

    public static void addSubscriber(IFragmentLifecycle life) {
        synchronized (LifeCycleSubscribers) {
            LifeCycleSubscribers.add(life);
        }
    }

    public static void removeSubscriber(IFragmentLifecycle life) {
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
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onAttach(fragment);
        }
    }

    public static void onCreate(Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onCreate(fragment);
        }
    }

    public static void onCreateView(Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onCreateView(fragment);
        }
    }

    public static void onViewCreated(Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onViewCreated(fragment);
        }
    }

    public static void onActivityCreated(Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onActivityCreated(fragment);
        }
    }

    public static void onStart(Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onStart(fragment);
        }
    }

    public static void onResume(Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onResume(fragment);
        }
    }

    public static void onPause(Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onPause(fragment);
        }
    }

    public static void onStop(Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onStop(fragment);
        }
    }

    public static void onDestroyView(Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onDestroyView(fragment);
        }
    }

    public static void onDestroy(Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onDestroy(fragment);
        }
    }

    public static void onDetach(Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onDetach(fragment);
        }
    }

    public static void setUserVisibleHint(boolean isVisibleToUser, Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.setUserVisibleHint(isVisibleToUser, fragment);
        }
    }

    public static void onHiddenChanged(boolean hidden, Fragment fragment) {
        for (IFragmentLifecycle IFragmentLifecycle : LifeCycleSubscribers) {
            IFragmentLifecycle.onHiddenChanged(hidden, fragment);
        }
    }
}

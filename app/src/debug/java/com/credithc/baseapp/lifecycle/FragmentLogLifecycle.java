package com.credithc.baseapp.lifecycle;

import androidx.fragment.app.Fragment;
import android.util.Log;

import com.credithc.mvp.lifecycle.IFragLifeSubscriber;


/**
*@author liyong
*@date 2020/8/13
*@des
*/
public class FragmentLogLifecycle implements IFragLifeSubscriber {

    private String seg = "  ";
    private String TAG = "Fragment";


    public FragmentLogLifecycle() {
    }

    public FragmentLogLifecycle(String tag) {
        this.TAG = tag;
    }

    @Override
    public void onAttach(Fragment fragment) {
        log(fragment, "onAttach");
    }

    @Override
    public void onCreate(Fragment fragment) {
        log(fragment, "onCreate");
    }

    @Override
    public void onCreateView(Fragment fragment) {
        log(fragment, "onCreateView");
    }

    @Override
    public void onViewCreated(Fragment fragment) {
        log(fragment, "onViewCreated");
    }

    @Override
    public void onActivityCreated(Fragment fragment) {
        log(fragment, "onActivityCreated");
    }

    @Override
    public void onStart(Fragment fragment) {
        log(fragment, "onStart");
    }

    @Override
    public void onResume(Fragment fragment) {
        log(fragment, "onResume");
    }

    @Override
    public void onPause(Fragment fragment) {
        log(fragment, "onPause");
    }

    @Override
    public void onStop(Fragment fragment) {
        log(fragment, "onStop");
    }

    @Override
    public void onDestroyView(Fragment fragment) {
        log(fragment, "onDestroyView");
    }

    @Override
    public void onDestroy(Fragment fragment) {
        log(fragment, "onDestroy");
    }

    @Override
    public void onDetach(Fragment fragment) {
        log(fragment, "onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser, Fragment fragment) {
        log(fragment, "setUserVisibleHint", isVisibleToUser + "");
    }


    @Override
    public void onHiddenChanged(boolean hidden, Fragment fragment) {
        log(fragment, "onHiddenChanged", hidden + "");
    }


    private String getClassName(Fragment fragment) {
        return fragment.getClass().getSimpleName() + seg + "--->";
    }


    @SuppressWarnings("all")
    private void log(Fragment fragment, String... strings) {
        StringBuilder sb = new StringBuilder(getClassName(fragment));
        if (strings != null && strings.length >= 1) {
            int count = strings.length;
            for (int i = 0; i < count; i++) {
                sb.append(seg).append("%s");
            }
        }

        Log.d(TAG, String.format(sb.toString(), (Object[]) strings));
    }

}

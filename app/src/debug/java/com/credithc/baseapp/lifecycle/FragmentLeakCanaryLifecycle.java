package com.credithc.baseapp.lifecycle;

import androidx.fragment.app.Fragment;

import com.credithc.baseapp.helper.LeakCanaryHelper;
import com.credithc.mvp.ui.lifecycle.IFragmentLifecycle;


/**
 * Created by lwj on 2018/10/31.
 * lwjfork@gmail.com
 */
public class FragmentLeakCanaryLifecycle implements IFragmentLifecycle {

    @Override
    public void onAttach(Fragment fragment) {

    }

    @Override
    public void onCreate(Fragment fragment) {

    }

    @Override
    public void onCreateView(Fragment fragment) {

    }

    @Override
    public void onViewCreated(Fragment fragment) {

    }

    @Override
    public void onActivityCreated(Fragment fragment) {

    }

    @Override
    public void onStart(Fragment fragment) {

    }

    @Override
    public void onResume(Fragment fragment) {

    }

    @Override
    public void onPause(Fragment fragment) {

    }

    @Override
    public void onStop(Fragment fragment) {

    }

    @Override
    public void onDestroyView(Fragment fragment) {

    }

    @Override
    public void onDestroy(Fragment fragment) {
        LeakCanaryHelper.watchReferences(fragment);
    }

    @Override
    public void onDetach(Fragment fragment) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser, Fragment fragment) {

    }

    @Override
    public void onHiddenChanged(boolean hidden, Fragment fragment) {

    }
}

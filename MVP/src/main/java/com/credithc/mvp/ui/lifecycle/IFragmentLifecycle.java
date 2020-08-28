package com.credithc.mvp.ui.lifecycle;

import android.support.v4.app.Fragment;

/**
*@author liyong
*@date 2020/8/10
*@des
*/
public interface IFragmentLifecycle {

    void onAttach(Fragment fragment);

    void onCreate(Fragment fragment);
    
    void onCreateView(Fragment fragment);

    void onViewCreated(Fragment fragment);

    void onActivityCreated(Fragment fragment);

    void onStart(Fragment fragment);

    void onResume(Fragment fragment);

    void onPause(Fragment fragment);

    void onStop(Fragment fragment);

    void onDestroyView(Fragment fragment);

    void onDestroy(Fragment fragment);

    void onDetach(Fragment fragment);

    void setUserVisibleHint(boolean isVisibleToUser, Fragment fragment);

    void onHiddenChanged(boolean hidden, Fragment fragment);

}

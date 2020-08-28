package com.credithc.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * @author liyong
 * @date 2020/8/27
 * @des
 */
public abstract class DefaultFragment extends LifecycleFragment {

    protected FragmentActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        initBundle(bundle);
    }

    protected void initBundle(@NonNull Bundle bundle) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isAdded() && !isHidden()) {
            onVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isVisible()) {
            onHidden();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onVisible();
        } else {
            onHidden();
        }
    }


    /**
     * Fragment可见的时候调用，包括从熄屏到点亮的时刻
     */
    protected void onVisible() {

    }

    /**
     * Fragment不可见的时候调用，包括从亮屏到熄屏的时刻
     */
    protected void onHidden() {

    }
}

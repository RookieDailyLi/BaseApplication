package com.credithc.mvp.ui.fragment;


import android.os.Bundle;
import android.view.View;

import com.credithc.mvp.presenter.RxBasePresenter;

import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.Nullable;


/**
 * @author liyong
 * @date 2018/11/16
 * @des 懒加载配合ViewPager
 */
public abstract class RxLazyRefreshFragment<T extends RxBasePresenter> extends RxRefreshFragment<T> {

    protected AtomicBoolean mIsLoaded = new AtomicBoolean(false);
    protected AtomicBoolean mIsViewCreated = new AtomicBoolean(false);

    @Override
    protected final void loadData() {
        if (getUserVisibleHint()) {
            mIsLoaded.set(true);
            lazyLoadData();
        }
    }

    protected abstract void lazyLoadData();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewCreated.set(true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (mIsViewCreated.get() && getUserVisibleHint() && !mIsLoaded.get()) {
            mIsLoaded.set(true);
            lazyLoadData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsLoaded.set(false);
        mIsViewCreated.set(false);
    }
}

package com.credithc.mvp.view;

import androidx.lifecycle.LifecycleObserver;

import com.credithc.mvp.error.base.ILoadCovers;
import com.credithc.mvp.error.base.ILoadEmpty;
import com.credithc.mvp.error.base.ILoadFailure;
import com.credithc.mvp.error.base.ILoading;
import com.credithc.mvp.error.base.INetException;
import com.credithc.mvp.error.base.IToast;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */

public interface BaseView extends ILoadEmpty, INetException, ILoadFailure, ILoadCovers, ILoading, IToast {

    void addObserver(LifecycleObserver observer);

    /**
     * 加载完成隐藏失败页面
     */
    void loadComplete();

    void showLoadCovers();
}

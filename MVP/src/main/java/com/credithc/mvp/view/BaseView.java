package com.credithc.mvp.view;

import androidx.lifecycle.LifecycleObserver;

import com.credithc.mvp.ui.exception.base.ILoadCovers;
import com.credithc.mvp.ui.exception.base.ILoadEmpty;
import com.credithc.mvp.ui.exception.base.ILoadFailure;
import com.credithc.mvp.ui.exception.base.ILoading;
import com.credithc.mvp.ui.exception.base.INetException;
import com.credithc.mvp.ui.exception.base.IToast;

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

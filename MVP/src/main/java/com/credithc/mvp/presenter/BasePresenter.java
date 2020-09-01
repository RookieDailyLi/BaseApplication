package com.credithc.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.credithc.mvp.model.BaseModel;
import com.credithc.mvp.ui.exception.base.ILoadCovers;
import com.credithc.mvp.ui.exception.base.ILoadEmpty;
import com.credithc.mvp.ui.exception.base.ILoadFailure;
import com.credithc.mvp.ui.exception.base.ILoading;
import com.credithc.mvp.ui.exception.base.INetException;
import com.credithc.mvp.ui.exception.base.IToast;
import com.credithc.mvp.view.BaseView;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public class BasePresenter<M extends BaseModel, V extends BaseView> implements LifecycleObserver{

    protected V mView;
    protected M model;

    public BasePresenter(M model, V mView) {
        attachView(mView);
        this.model = model;
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        detachView();
        if (model != null) {
            model.destroy();
            model = null;
        }
    }

    /**
     * 绑定view，一般在初始化中调用该方法
     *
     * @param view view
     */
    public void attachView(V view) {
        this.mView = view;
    }

    /**
     * 解除绑定view，一般在onDestroy中调用
     */

    public void detachView() {
        this.mView = null;
    }

    /**
     * View是否绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mView != null;
    }
}

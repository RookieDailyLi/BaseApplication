package com.credithc.commonlib.mvp.p;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import com.credithc.commonlib.mvp.m.BaseModel;
import com.credithc.commonlib.mvp.v.BaseView;
import com.credithc.commonlib.widget.excpetion.base.ILoadCovers;
import com.credithc.commonlib.widget.excpetion.base.ILoadEmpty;
import com.credithc.commonlib.widget.excpetion.base.ILoadFailure;
import com.credithc.commonlib.widget.excpetion.base.ILoading;
import com.credithc.commonlib.widget.excpetion.base.INetException;
import com.credithc.commonlib.widget.excpetion.base.IToast;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public class BasePresenter<M extends BaseModel, V extends BaseView> implements
        LifecycleObserver,
        ILoadFailure,
        ILoadEmpty,
        INetException,
        ILoadCovers,
        ILoading,
        IToast {

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


    //====================================== 下面方法是为了方便调用方法 MVP 中不需要===================================================================
    @Override
    public final void showLoading() {
        if (isViewAttached()) {
            mView.showLoading();
        }
    }

    @Override
    public final void showLoading(String title) {
        if (isViewAttached()) {
            mView.showLoading(title);
        }
    }

    @Override
    public final void showLoading(boolean cancelAble) {
        if (isViewAttached()) {
            mView.showLoading(cancelAble);
        }
    }

    @Override
    public final void showLoading(String tips, boolean cancelAble) {
        if (isViewAttached()) {
            mView.showLoading(tips, cancelAble);
        }
    }


    @Override
    public void showLoadingNoTips() {
        if (isViewAttached()) {
            mView.showLoadingNoTips();
        }
    }

    @Override
    public void showLoadingNoTips(boolean cancelAble) {
        if (isViewAttached()) {
            mView.showLoadingNoTips(cancelAble);
        }
    }

    @Override
    public final void dismissLoading() {
        if (isViewAttached()) {
            mView.dismissLoading();
        }
    }


    /**
     * 加载完成隐藏失败页面
     */
    @Override
    public final void loadComplete() {
        if (isViewAttached()) {
            mView.loadComplete();
        }
    }

    @Override
    public final void showEmpty(int errorIcon, String tv, String btn) {
        if (isViewAttached()) {
            mView.showEmpty(errorIcon, tv, btn);
        }
    }

    @Override
    public final void showLoadFailure(int errorIcon, String tv, String btn) {
        if (isViewAttached()) {
            mView.showLoadFailure(errorIcon, tv, btn);
        }
    }

    @Override
    public final void showNetException(int netIcon, String tv, String btn) {
        if (isViewAttached()) {
            mView.showNetException(netIcon, tv, btn);
        }
    }

    @Override
    public final void showLoadCovers(int color) {
        if (isViewAttached()) {
            mView.showLoadCovers(color);
        }
    }
}

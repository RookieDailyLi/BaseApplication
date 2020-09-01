package com.credithc.baseapp.net.callback;

import com.credithc.mvp.view.RxBaseView;
import com.credithc.netlib.bean.ResultModel;
import com.credithc.netlib.callback.ResponseCallBack;

/**
 * @author liyong
 * @date 2020/8/21 18:10
 * @description 默认接口回调，已经处理Loading
 */
public abstract class DefaultResponseCallBack<T> extends ResponseCallBack<T> {

    private RxBaseView mView;

    public DefaultResponseCallBack(RxBaseView mView) {
        this.mView = mView;
    }

    @Override
    public void onRequestStart() {
        if (mView != null) {
            mView.showLoading();
        }
    }

    @Override
    public void onRequestFinish() {
        if (mView != null) {
            mView.dismissLoading();
        }
    }
}

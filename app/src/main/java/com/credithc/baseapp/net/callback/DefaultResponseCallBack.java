package com.credithc.baseapp.net.callback;

import com.credithc.baseapp.event.LoginEvent;
import com.credithc.common.util.ToastUtil;
import com.credithc.mvp.view.RxBaseView;
import com.credithc.net.bean.ResultModel;
import com.credithc.net.callback.ResponseCallBack;

import org.greenrobot.eventbus.EventBus;

/**
 * @author liyong
 * @date 2020/8/21 18:10
 * @description 默认接口回调，已经处理Loading、RequestFail错误页、NetException错误页,
 * 如不需要展示错误页、网络异常页，仅需要Toast提示，请重写{@link #onRequestFail(ResultModel)}
 * 和{@link #onNetFail(ResultModel)}
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

    @Override
    public void onRequestFail(ResultModel resultModel) {
        if (mView != null) {
            mView.showLoadFailure();
        }
    }

    @Override
    public void onNetFail(ResultModel resultModel) {
        if (mView != null) {
            mView.showNetException();
        }
    }

    @Override
    public void logOut(ResultModel<T> resultModel) {
        ToastUtil.showToast(resultModel.getMessage());
        EventBus.getDefault().post(new LoginEvent.LoginOff());
    }
}

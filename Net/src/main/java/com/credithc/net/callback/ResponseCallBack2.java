package com.credithc.net.callback;

import com.credithc.net.bean.ResultModel;
import com.credithc.net.constant.APICode;

import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liyong
 * @date 2019/10/31
 * @des 网络请求回调类(支持背压Flowable)
 */
public abstract class ResponseCallBack2<T> extends DisposableSubscriber<ResultModel<T>> implements BaseResponseCallBack<T> {

    private static CommonWrapCallBack commonBack = new CommonWrapCallBack();

    @Override
    protected final void onStart() {
        super.onStart();
        commonBack.onStart(this);
    }

    @Override
    public final void onNext(ResultModel<T> tResultModel) {
        //请求成功
        if (isSuccess(tResultModel.getCode())) {
            onResponseSuccess(tResultModel);
            return;
        }

        //踢出登录
        if (isNeedLogin(tResultModel.getCode())) {
            logOut(tResultModel);
            return;
        }
        commonBack.onNext(tResultModel, this);
    }

    @Override
    public final void onError(Throwable e) {
        commonBack.onError(e, this);
        onComplete();
    }

    @Override
    public final void onComplete() {
        commonBack.onComplete(this);
    }

    public final boolean isSuccess(String code) {
        return APICode.CODE_SUCCESS.equals(code);
    }

    public final boolean isNeedLogin(String code) {
        return APICode.CODE_LOGIN.equals(code);
    }

    /**
     * 退出登录
     */
    public abstract void logOut(ResultModel<T> resultModel);
}

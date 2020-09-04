package com.credithc.netlib.callback;

import androidx.annotation.UiThread;

import com.credithc.netlib.bean.ResultModel;


/**
 * @author liyong
 * @date 2019/10/31
 * @des 请求回调基类
 */

public interface BaseResponseCallBack<T> {

    /**
     * 开始请求，在UI线程执行，例如展示dialog
     */
    @UiThread
    default void onRequestStart() {

    }

    @UiThread
    void onResponseSuccess(ResultModel<T> resultModel);

    /**
     * 解析异常
     */
    @UiThread
    void onRequestFail(ResultModel resultModel);

    /**
     * 网络异常
     */
    @UiThread
    void onNetFail(ResultModel resultModel);

    /**
     * 结束请求，在UI线程执行，例如关闭dialog
     */
    @UiThread
    default void onRequestFinish() {

    }
}

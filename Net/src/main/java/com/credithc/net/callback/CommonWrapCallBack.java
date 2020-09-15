package com.credithc.net.callback;


import com.credithc.common.util.LogUtil;
import com.credithc.net.bean.ResultModel;
import com.credithc.net.exception.ParseException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;
import retrofit2.Response;

/**
 * @author liyong
 * @date 2019/10/31
 * @des 通用回调处理类
 */
class CommonWrapCallBack {

    public final void onStart(BaseResponseCallBack realCallBack) {
        realCallBack.onRequestStart();
    }

    public final <T> void onNext(ResultModel<T> tResultModel, BaseResponseCallBack realCallBack) {
        realCallBack.onRequestFail(tResultModel);
    }

    public final void onError(Throwable e, BaseResponseCallBack realCallBack) {
        if (e instanceof HttpException) {
            Response<?> response = ((HttpException) e).response();
            try {
                if (LogUtil.isShowLog()) {
                    LogUtil.e("--->请求URL: %s \n" +
                                    "--->错误码:-- %s\n" +
                                    "--->错误信息:-- %s",
                            response.raw().request().url().toString(),
                            response.code(),
                            response.message()
                    );
                }
            } catch (Exception io) {
                io.printStackTrace();
            } finally {
                realCallBack.onRequestFail(ResultModel.createParseError());
            }
            return;
        }
        LogUtil.e(e.toString());
        if (e instanceof SocketTimeoutException) {
            realCallBack.onNetFail(ResultModel.createNetFail());
            return;
        }
        if (e instanceof ConnectException) {
            realCallBack.onNetFail(ResultModel.createNetFail());
            return;
        }
        if (e instanceof UnknownHostException) {
            realCallBack.onNetFail(ResultModel.createNetFail());
            return;
        }
        if (e instanceof SocketException) {
            realCallBack.onNetFail(ResultModel.createNetFail());
            return;
        }
        if (e instanceof IOException) {
            realCallBack.onNetFail(ResultModel.createNetFail());
            return;
        }
        if (e instanceof ParseException) {
            realCallBack.onRequestFail(((ParseException) e).getResultModel());
            return;

        }
        realCallBack.onRequestFail(ResultModel.createParseError());
    }

    public final void onComplete(BaseResponseCallBack realCallBack) {
        realCallBack.onRequestFinish();
    }
}

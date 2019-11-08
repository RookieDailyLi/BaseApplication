package com.credithc.netlib.callback;


import com.credithc.commonlib.event.LoginEvent;
import com.credithc.commonlib.util.LogUtil;
import com.credithc.commonlib.util.ToastUtil;
import com.credithc.netlib.bean.ResultModel;
import com.credithc.netlib.constant.APICode;
import com.credithc.netlib.exception.ParseException;

import org.greenrobot.eventbus.EventBus;

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
        if (isSuccess(tResultModel.getCode())) {
            realCallBack.onResponseSuccess(tResultModel);
            return;
        }

        // 重新登录
        if (isForcedLoginOut(tResultModel.getCode())) {
            loginOut(tResultModel);
            return;
        }
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

    public final boolean isSuccess(String code) {
        return APICode.CODE_SUCCESS.equals(code);
    }

    public final boolean isForcedLoginOut(String code) {
        return APICode.CODE_LOGIN.equals(code);
    }

    public final void loginOut(ResultModel tResultModel) {
        ToastUtil.showToast(tResultModel.getMessage());
        // 退出登录
        EventBus.getDefault().post(new LoginEvent.LoginOff());
    }
}

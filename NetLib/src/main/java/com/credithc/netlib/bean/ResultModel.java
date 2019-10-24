package com.credithc.netlib.bean;

import com.credithc.commonlib.util.ResUtil;
import com.credithc.netlib.R;
import com.credithc.netlib.constant.APICode;

import java.io.Serializable;


/**
 * @author liyong
 * @date 2019/10/23
 * @des 统一响应model
 */

public class ResultModel<T> implements Serializable {

    private String code = "";
    private String msg = "";
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {

        this.msg = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static ResultModel create(String code, String message) {
        ResultModel resultModel = new ResultModel();
        resultModel.setCode(code);
        resultModel.setMessage(message);
        return resultModel;
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultModel<T> createNetFail() {
        return (ResultModel<T>) create(APICode.CODE_NET_FAIL, ResUtil.getString(R.string.no_network_tips));
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultModel<T> createNetFail(String msg) {
        return (ResultModel<T>) create(APICode.CODE_NET_FAIL, msg);
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultModel<T> createParseError() {
        return (ResultModel<T>) create(APICode.CODE_PARSE_ERROR, ResUtil.getString(R.string.server_error_tips));
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultModel<T> createReLogin(String msg) {
        return (ResultModel<T>) create(APICode.CODE_LOGIN, msg);
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultModel<T> createSuccess() {
        return (ResultModel<T>) create(APICode.CODE_SUCCESS, "请求成功");
    }

    public boolean isSuccess() {
        return APICode.CODE_SUCCESS.equals(getCode());
    }

    public boolean isReLogin() {
        return APICode.CODE_LOGIN.equals(getCode());
    }

    public boolean isNetFail() {
        return APICode.CODE_NET_FAIL.equals(getCode()) || APICode.CODE_NO_NET.equals(getCode());
    }

    public boolean isParseError() {
        return APICode.CODE_PARSE_ERROR.equals(getCode());
    }
}

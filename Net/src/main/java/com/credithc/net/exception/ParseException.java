package com.credithc.net.exception;


import com.credithc.net.bean.ResultModel;

/**
*@author liyong
*@date 2019/10/23
*@des
*/

public class ParseException extends RuntimeException {
    private String code;
    private String message;

    private ResultModel resultModel;

    public ParseException(ResultModel resultModel) {
        super(resultModel.getMessage());
        this.code = resultModel.getCode();
        this.resultModel = resultModel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultModel getResultModel() {
        return resultModel;
    }

    public void setResultModel(ResultModel resultModel) {
        this.resultModel = resultModel;
    }
}

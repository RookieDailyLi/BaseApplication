package com.credithc.netlib.okhttp.converter;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
*@author liyong
*@date 2019/10/23
*@des 解析响应转换接口
*/

public interface IConvert<T> {

    T convert(ResponseBody responseBody)throws IOException;

}

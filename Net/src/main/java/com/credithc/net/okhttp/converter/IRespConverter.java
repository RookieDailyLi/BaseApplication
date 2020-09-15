package com.credithc.net.okhttp.converter;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * @author liyong
 * @date 2019/10/23
 * @des
 */
public interface IRespConverter {

    <T> T convert(ResponseBody responseBody, Type type) throws Exception;

}

package com.credithc.netlib.okhttp.converter;

import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author liyong
 * @date 2019/10/23
 * @des
 */
public interface IRespConverter {

    <T> T convert(ResponseBody responseBody, Type type) throws Exception;

}

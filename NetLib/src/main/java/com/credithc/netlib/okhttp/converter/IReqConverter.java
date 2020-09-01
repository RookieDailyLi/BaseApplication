package com.credithc.netlib.okhttp.converter;

import okhttp3.RequestBody;

/**
 * @author liyong
 * @date 2020/9/1 15:46
 * @description
 */
public interface IReqConverter {
    <T> RequestBody convert(T value);
}

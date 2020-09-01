package com.credithc.netlib.retrofit.converter;

import com.credithc.netlib.okhttp.converter.IReqConverter;

import java.io.IOException;

import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @author liyong
 * @date 2019/10/23
 * @des
 */
public class NetRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private IReqConverter requestConverter;

    public NetRequestBodyConverter(IReqConverter requestConverter) {
        this.requestConverter = requestConverter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        return requestConverter.convert(value);
    }
}

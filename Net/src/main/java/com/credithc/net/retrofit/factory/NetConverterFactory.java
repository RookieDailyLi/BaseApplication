package com.credithc.net.retrofit.factory;

import com.credithc.net.okhttp.converter.DefaultRequestConverter;
import com.credithc.net.okhttp.converter.DefaultResponseConverter;
import com.credithc.net.okhttp.converter.IReqConverter;
import com.credithc.net.okhttp.converter.IRespConverter;
import com.credithc.net.retrofit.converter.NetRequestBodyConverter;
import com.credithc.net.retrofit.converter.NetResponseBodyConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author liyong
 * @date 2019/10/23
 * @des Factory 处理请求体，解析响应
 */

public class NetConverterFactory extends Converter.Factory {

    private IReqConverter iReqConverter;
    private IRespConverter iRespConverter;

    private NetConverterFactory(IReqConverter requestConverter, IRespConverter responseConverter) {
        this.iReqConverter = requestConverter;
        this.iRespConverter = responseConverter;
    }

    /**
     * 创建默认Factory
     */
    public static NetConverterFactory create() {
        return new NetConverterFactory(new DefaultRequestConverter(), new DefaultResponseConverter());
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        return new NetRequestBodyConverter<>(iReqConverter);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {

        return new NetResponseBodyConverter<>(type, iRespConverter);
    }
}
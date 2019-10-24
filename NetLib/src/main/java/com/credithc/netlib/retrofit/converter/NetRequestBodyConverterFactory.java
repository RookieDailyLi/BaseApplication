package com.credithc.netlib.retrofit.converter;

import com.credithc.commonlib.util.GsonUtil;
import com.credithc.netlib.bean.IRequest;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.ByteString;
import retrofit2.Converter;

/**
 * @author liyong
 * @date 2019/10/23
 * @des
 */
public class NetRequestBodyConverterFactory<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/text; charset=UTF-8");


    public NetRequestBodyConverterFactory() {
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        if (value instanceof IRequest) {
            return ((IRequest) value).createReqBody();
        }

        return RequestBody.create(MEDIA_TYPE, ByteString.encodeUtf8(GsonUtil.obj2JsonStr(value)));
    }
}

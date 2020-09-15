package com.credithc.net.okhttp.converter;

import com.credithc.common.util.GsonUtil;
import com.credithc.net.bean.IRequest;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.ByteString;

/**
 * @author liyong
 * @date 2020/9/1 10:49
 * @description
 */
public class DefaultRequestConverter implements IReqConverter {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");


    @Override
    public <T> RequestBody convert(T value) {
        if (value instanceof IRequest) {
            return ((IRequest) value).createReqBody();
        }
        return RequestBody.create(MEDIA_TYPE, ByteString.encodeUtf8(GsonUtil.obj2JsonStr(value)));
    }
}

package com.credithc.baseapp.net.interceptor;

import com.credithc.baseapp.net.config.ServerHelper;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;

/**
*@author liyong
*@date 2019/10/22
*@des 请求参数加密拦截器
*/
public class RequestEncryptInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request req = chain.request();
        RequestBody reqBody = req.body();
        String reqMethod = req.method();
        String url = req.url().toString();
        if ("post".equalsIgnoreCase(reqMethod)) { // post 请求
//            String string = getReqContent(reqBody);
            String string = getFixedReqContent(reqBody);
            try {
                JSONObject jsonObject = new JSONObject(string);
                String encryptStr = ServerHelper.getInstance().encryptData(jsonObject.toString());

                req = req.newBuilder().post(RequestBody.create(reqBody.contentType(), encryptStr)).build();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chain.proceed(req);
    }

    private String getReqContent(RequestBody reqBody) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedSink sink = Okio.buffer(Okio.sink(byteArrayOutputStream));
        reqBody.writeTo(sink);
        Buffer buffer = sink.buffer();
        ByteString byteString = buffer.readByteString();
        return byteString.utf8();
    }

    private String getFixedReqContent(RequestBody reqBody) throws IOException {
        Buffer buffer = new Buffer();
        reqBody.writeTo(buffer);
        ByteString byteString = buffer.readByteString();
        return byteString.utf8();
    }


}

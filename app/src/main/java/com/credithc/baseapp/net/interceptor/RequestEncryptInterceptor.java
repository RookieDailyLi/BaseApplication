package com.credithc.baseapp.net.interceptor;

import com.credithc.baseapp.net.config.ServerHelper;
import com.credithc.common.util.LogUtil;
import com.credithc.common.util.StringUtil;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.ByteString;

/**
 * @author liyong
 * @date 2019/10/22
 * @des 请求参数加密拦截器
 */
public class RequestEncryptInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request req = chain.request();
        RequestBody reqBody = req.body();
        String reqMethod = req.method();
        String url = req.url().toString();
        Headers headers = req.headers();
        if ("post".equalsIgnoreCase(reqMethod)) { // post 请求
            String bodyString = getFixedReqContent(reqBody);
            try {
                String encryptStr = "";
                if (!StringUtil.isEmpty(bodyString)) {
                    encryptStr = ServerHelper.getInstance().encryptData(bodyString);
                }
                LogUtil.e("请求url = %s\n" +
                                "请求header = %s\n" +
                                "请求参数 = %s\n" +
                                "加密参数 = %s\n"
                        , url
                        , headers.toMultimap().toString()
                        , StringUtil.isEmpty(bodyString) ? "\"\"" : bodyString
                        , StringUtil.isEmpty(encryptStr) ? "\"\"" : encryptStr);
                req = req.newBuilder().post(RequestBody.create(reqBody.contentType(), encryptStr)).build();
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e("请求参数加密异常，exception = %s", e.getMessage());
            }
        }
        return chain.proceed(req);
    }

    private String getFixedReqContent(RequestBody reqBody) throws IOException {
        Buffer buffer = new Buffer();
        reqBody.writeTo(buffer);
        ByteString byteString = buffer.readByteString();
        return byteString.utf8();
    }


}

package com.credithc.baseapp.net.interceptor;

import com.credithc.baseapp.net.config.ServerHelper;
import com.credithc.common.util.LogUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author liyong
 * @date 2019/10/23
 * @des 响应解密拦截器
 */
public class ResponseDecryptInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        String url = request.url().toString();

        String contentType = response.header("Content-Type");
        if (!(contentType.contains("text") || contentType.contains("json"))) { // 非文本的不考虑
            return response;
        }

        if (!response.isSuccessful()) {
            return response;
        }

        String originRespData = responseBody.string();
        String responseData;
        try {
            responseData = ServerHelper.getInstance().decryptData(originRespData);
            LogUtil.e("响应url = %s\n" +
                            "响应header = %s\n" +
                            "响应body = %s\n"
                    , url
                    , response.headers().toMultimap().toString()
                    , responseData);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("响应解密异常，exception = %s", e.getMessage());
            return response;
        }
        return response.newBuilder()
                .body(ResponseBody.create(responseBody.contentType(), responseData))
                .build();
    }

}

package com.credithc.baseapp.net.interceptor;

import com.credithc.baseapp.util.ChannelUtils;
import com.credithc.baseapp.util.LoginUtil;
import com.credithc.commonlib.GlobalContext;
import com.credithc.commonlib.util.AppUtil;
import com.credithc.commonlib.util.OSUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author liyong
 * @date 2019/10/22 9:08
 * @description 默认header拦截器
 */
public class CommonHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        originRequest.newBuilder().headers(Headers.of(headers()));
        return chain.proceed(null);
    }


    private HashMap<String, String> headers() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("reqDate", System.currentTimeMillis() + "");
        headers.put("deviceId", AppUtil.getIMEI(GlobalContext.getContext()));
        headers.put("os", "android" + OSUtils.getSysVersion());
        headers.put("token", LoginUtil.getToken());
        headers.put("version", AppUtil.getVersionName() + "");
        headers.put("channel", ChannelUtils.getChannel());
        return headers;
    }
}

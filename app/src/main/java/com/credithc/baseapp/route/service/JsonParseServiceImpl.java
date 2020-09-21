package com.credithc.baseapp.route.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.credithc.baseapp.route.RouterConstants;
import com.credithc.baseapp.route.util.RouterHandler;
import com.google.gson.Gson;

import java.lang.reflect.Type;


/**
 * Created by lwj on 2018/11/6.
 * lwjfork@gmail.com
 * 后台参数--json 解析
 */
@Route(path = RouterConstants.Service.SERVICE_JSON)
public class JsonParseServiceImpl implements SerializationService {

    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return new Gson().fromJson(input, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return new Gson().toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return new Gson().fromJson(input, clazz);
    }

    @Override
    public void init(Context context) {
        RouterHandler.debug("ARouter json 解析服务初始化");
    }
}

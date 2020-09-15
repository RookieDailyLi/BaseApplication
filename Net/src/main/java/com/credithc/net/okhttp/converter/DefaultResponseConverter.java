package com.credithc.net.okhttp.converter;

import com.credithc.common.util.JSONUtil;
import com.credithc.net.bean.ResultModel;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * @author liyong
 * @date 2020/9/1 16:29
 * @description 默认网络响应处理，适用于格式
 * {
 * "code":"0000",
 * "msg":"成功",
 * "data":{"key":"value"}
 * }
 */
public class DefaultResponseConverter implements IRespConverter {
    @Override
    public <T> T convert(ResponseBody responseBody, Type type) throws Exception {
        String jsonStr = responseBody.string();
        responseBody.close();

        ResultModel resultModel = new ResultModel();
        JSONObject jsonObject = new JSONObject(jsonStr);
        String code = jsonObject.optString("code");
        String msg = jsonObject.optString("msg");
        JSONObject data = jsonObject.optJSONObject("data");
        resultModel.setCode(code);
        resultModel.setMessage(msg);
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type ownerType = parameterizedType.getActualTypeArguments()[0];
            if (data != null) {
                resultModel.setData(JSONUtil.jsonStr2Obj(data.toString(), ownerType));
            }
        }
        return (T) resultModel;
    }
}

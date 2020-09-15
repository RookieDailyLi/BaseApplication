package com.credithc.baseapp.net.converter;

import com.credithc.common.util.JSONUtil;
import com.credithc.net.bean.NoneBodyBean;
import com.credithc.net.bean.ResultModel;
import com.credithc.net.okhttp.converter.IRespConverter;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * @author liyong
 * @date 2020/9/1 10:56
 * @description 网络响应处理，适用于格式
 * {
 * "head":{"code":"0000","msg":"成功"},
 * "body":{"key":"value"}
 * }
 */
public class ResponseConverter implements IRespConverter {

    @Override
    public <T> T convert(ResponseBody responseBody, Type type) throws Exception {
        String jsonStr = responseBody.string();
        responseBody.close();

        ResultModel resultModel = new ResultModel();
        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONObject header = jsonObject.optJSONObject("head");
        String code = header.optString("code");
        String msg = header.optString("msg");
        JSONObject data = jsonObject.optJSONObject("body");
        resultModel.setCode(code);
        resultModel.setMessage(msg);
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type ownerType = parameterizedType.getActualTypeArguments()[0];
            if (data != null) {
                resultModel.setData(JSONUtil.jsonStr2Obj(data.toString(), ownerType));
            } else if (ownerType == NoneBodyBean.class) {
                resultModel.setData(new NoneBodyBean());
            }
        }
        return (T) resultModel;
    }
}

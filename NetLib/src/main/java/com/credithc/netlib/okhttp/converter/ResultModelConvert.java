package com.credithc.netlib.okhttp.converter;

import com.credithc.commonlib.util.GsonUtil;
import com.credithc.netlib.bean.ResultModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

/**
 * @author liyong
 * @date 2019/10/23
 * @des 解析ResponseBody
 */
public class ResultModelConvert extends StringConvert {

    private IModelConvert convert = new DefaultModelConvert();

    public static ResultModelConvert getInstance() {
        return ResultModelConvert.ConvertHolder.CONVERT;
    }

    ResultModelConvert() {
    }

    private static class ConvertHolder {
        private static final ResultModelConvert CONVERT = new ResultModelConvert();
    }

    public void setModelConvert(IModelConvert modelConvert) {
        convert = modelConvert;
    }


    public final <T> T convert(ResponseBody responseBody, Type type) throws IOException, JSONException {
        String string = StringConvert.getInstance().convert(responseBody);
        responseBody.close();
        return convert(string, type);
    }


    public final <T> T convert(String jsonStr, Type type) throws JSONException {
        return realConvert(jsonStr, type);
    }


    private <T> T realConvert(String jsonStr, Type type) throws JSONException {
        return convert.realConvert(jsonStr, type);
    }


    private class DefaultModelConvert implements IModelConvert {

        @Override
        @SuppressWarnings("unchecked")
        public <T> T realConvert(String jsonStr, Type type) throws JSONException {
            ResultModel resultModel = new ResultModel();
            JSONObject jsonResponse = new JSONObject(jsonStr);
            JSONObject jsonHeader = jsonResponse.getJSONObject("header");
            String code = jsonHeader.optString("code");
            String msg = jsonHeader.optString("msg");
            JSONObject data = jsonResponse.optJSONObject("data");
            resultModel.setCode(code);
            resultModel.setMessage(msg);
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type ownerType = parameterizedType.getActualTypeArguments()[0];
                if (data != null) {
                    resultModel.setData(GsonUtil.jsonStr2Obj(data.toString(), ownerType));
                }
            }
            return (T) resultModel;
        }
    }
}

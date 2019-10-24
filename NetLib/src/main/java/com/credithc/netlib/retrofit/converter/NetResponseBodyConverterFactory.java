package com.credithc.netlib.retrofit.converter;


import com.credithc.netlib.bean.ResultModel;
import com.credithc.netlib.exception.ParseException;
import com.credithc.netlib.okhttp.converter.ResultModelConvert;

import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author liyong
 * @date 2019/10/23
 * @des 响应装换器
 */
public class NetResponseBodyConverterFactory<T> implements Converter<ResponseBody, T> {

    private Type type;


    public NetResponseBodyConverterFactory(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) {
        try {
            return ResultModelConvert.getInstance().convert(value, type);
        } catch (IOException e) {
            e.printStackTrace();
            value.close();
            throw new ParseException(ResultModel.createParseError());
        } catch (JSONException e) {
            e.printStackTrace();
            value.close();
            throw new ParseException(ResultModel.createParseError());
        } catch (Exception e) {
            e.printStackTrace();
            value.close();
            throw new ParseException(ResultModel.createParseError());
        }
    }
}

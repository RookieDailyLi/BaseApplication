package com.credithc.net.retrofit.converter;


import com.credithc.net.bean.ResultModel;
import com.credithc.net.exception.ParseException;
import com.credithc.net.okhttp.converter.IRespConverter;

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
public class NetResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    private IRespConverter responseConverter;

    public NetResponseBodyConverter(Type type, IRespConverter responseConverter) {
        this.type = type;
        this.responseConverter = responseConverter;
    }

    @Override
    public T convert(ResponseBody value) {
        try {
            return responseConverter.convert(value, type);
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

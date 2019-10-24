package com.credithc.netlib.okhttp.converter;

import org.json.JSONException;

import java.lang.reflect.Type;

/**
*@author liyong
*@date 2019/10/23
*@des
*/
public interface IModelConvert {


    @SuppressWarnings("unchecked")
    public <T> T realConvert(String jsonStr, Type type) throws JSONException;


}

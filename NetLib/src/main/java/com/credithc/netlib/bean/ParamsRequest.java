package com.credithc.netlib.bean;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author liyong
 * @date 2019/10/23
 * @des
 */
public class ParamsRequest implements IRequest {

    private MediaType mediaType = MediaType.parse("application/test; charset=UTF-8");


    private JSONObject getReqParams() {
        JSONObject jsonObject = new JSONObject();
        try {
            setParams(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    protected void setParams(JSONObject jsonObject) throws JSONException {

    }

    @Override
    public MediaType mediaType() {
        return mediaType;
    }

    @Override
    public final RequestBody createReqBody() {
        return RequestBody.create(mediaType, getReqParams().toString());
    }


}

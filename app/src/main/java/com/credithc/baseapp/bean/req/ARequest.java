package com.credithc.baseapp.bean.req;

import com.credithc.net.bean.IRequest;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author liyong
 * @date 2020/9/1
 * @des
 */
public abstract class ARequest implements IRequest {

    private static final MediaType mediaType = MediaType.parse("application/json; charset=UTF-8");


    private JSONObject getReqParams() {
        JSONObject jsonObject = new JSONObject();
        try {
            setParams(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    abstract void setParams(JSONObject jsonObject) throws JSONException;

    @Override
    public final RequestBody createReqBody() {
        return RequestBody.create(mediaType, getReqParams().toString());
    }


}

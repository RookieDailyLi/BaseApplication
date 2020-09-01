package com.credithc.netlib.bean;

import java.io.Serializable;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author liyong
 * @date 2019/10/23
 * @des
 */
public interface IRequest extends Serializable {

    RequestBody createReqBody();

}

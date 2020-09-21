package com.credithc.baseapp.ui.web.bean;


import com.github.lzyzsd.jsbridge.CallBackFunction;

import java.io.Serializable;

/**
 * @author liyong
 * @date 2019/8/23 15:24
 * @description
 */
public class BridgeMessageBean implements Serializable {
    public String handlerName;
    public String data;
    public CallBackFunction function;
}
 
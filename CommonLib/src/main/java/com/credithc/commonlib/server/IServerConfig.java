package com.credithc.commonlib.server;

/**
 * @author liyong
 * @date 2019/8/31
 * @des 网络请求配置基类
 */
public interface IServerConfig {
    String serverTag();

    String getHostURL();

    String encryptAesKey();

    String encryptIv();

    String encryptWay();

    // 是否要加密
    boolean encryptAble();

}

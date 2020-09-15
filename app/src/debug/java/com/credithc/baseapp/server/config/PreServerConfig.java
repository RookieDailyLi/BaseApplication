package com.credithc.baseapp.server.config;

import com.credithc.baseapp.BuildConfig;
import com.credithc.common.server.IServerConfig;

/**
 * @author liyong
 * @date 2019/8/31
 * @des debug包预发布网络环境配置
 */
public class PreServerConfig implements IServerConfig {


    @Override
    public boolean encryptAble() {
        return Boolean.parseBoolean(BuildConfig.pre_encrypt);
    }

    @Override
    public String encryptAesKey() {
        return BuildConfig.pre_AES_KEY;
    }

    @Override
    public String encryptWay() {
        return BuildConfig.pre_WAY;
    }

    @Override
    public String encryptIv() {
        return BuildConfig.pre_IV;
    }

    @Override
    public String serverTag() {
        return BuildConfig.pre_serverTag;
    }

    @Override
    public String getHostURL() {
        return BuildConfig.pre_host;
    }


}

package com.credithc.baseapp.net.config;


import com.credithc.baseapp.BuildConfig;
import com.credithc.common.server.IServerConfig;

/**
 * @author liyong
 * @date 2019/8/31
 * @des 生产环境网络配置
 */
public class ReleaseServerConfig implements IServerConfig {

    @Override
    public boolean encryptAble() {
        return Boolean.parseBoolean(BuildConfig.release_encrypt);
    }

    @Override
    public String encryptAesKey() {
        return BuildConfig.release_AES_KEY;
    }

    @Override
    public String encryptWay() {
        return BuildConfig.release_WAY;
    }

    @Override
    public String encryptIv() {
        return BuildConfig.release_IV;
    }

    @Override
    public String serverTag() {
        return BuildConfig.release_serverTag;
    }

    @Override
    public String getHostURL() {
        return BuildConfig.release_host;
    }


}

package com.credithc.baseapp.server.config;


import com.credithc.baseapp.BuildConfig;
import com.credithc.common.server.IServerConfig;

/**
 * @author liyong
 * @date 2019/8/31
 * @des debug包测试网络环境配置
 */
public class TestServerConfig implements IServerConfig {

    @Override
    public boolean encryptAble() {
        return Boolean.parseBoolean(BuildConfig.test_encrypt);
    }

    @Override
    public String encryptAesKey() {
        return BuildConfig.test_AES_KEY;
    }

    @Override
    public String encryptWay() {
        return BuildConfig.test_WAY;
    }

    @Override
    public String encryptIv() {
        return BuildConfig.test_IV;
    }

    @Override
    public String serverTag() {
        return BuildConfig.test_serverTag;
    }

    @Override
    public String getHostURL() {
        return BuildConfig.test_host;
    }


}

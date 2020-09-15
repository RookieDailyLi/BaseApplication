package com.credithc.common.server;

/**
 * @author liyong
 * @date 2019/8/31
 * @des
 */
public class HCServerHelper<T extends IServerConfig> implements IServerConfig {

    public static String AES_KEY = "";
    public static String IV = "";
    public static String WAY = "";

    protected T serverConfig;

    public HCServerHelper(T serverConfig) {
        this.serverConfig = serverConfig;
        configServer();
    }

    public T getServerConfig() {
        return serverConfig;
    }

    public void setServerConfig(T serverConfig) {
        this.serverConfig = serverConfig;
        configServer();
    }

    public void configServer() {
        AES_KEY = encryptAesKey();
        IV = encryptIv();
        WAY = encryptWay();
    }

    @Override
    public String serverTag() {
        return serverConfig.serverTag();
    }

    @Override
    public String getHostURL() {
        return serverConfig.getHostURL();
    }

    @Override
    public String encryptAesKey() {
        return serverConfig.encryptAesKey();
    }

    @Override
    public String encryptIv() {
        return serverConfig.encryptIv();
    }

    @Override
    public String encryptWay() {
        return serverConfig.encryptWay();
    }

    @Override
    public boolean encryptAble() {
        return serverConfig.encryptAble();
    }
}

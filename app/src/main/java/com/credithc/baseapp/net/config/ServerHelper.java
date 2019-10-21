package com.credithc.baseapp.net.config;

import com.credithc.commonlib.server.HCServerHelper;
import com.credithc.commonlib.util.AES;

/**
 * @author liyong
 * @date 2019/8/31
 * @des 网络配置管理类，打release包默认是生产环境
 */
public class ServerHelper extends HCServerHelper {

    private ServerHelper() {
        super(new ReleaseServerConfig());
    }

    private static class Creator {
        private static ServerHelper instance = new ServerHelper();
    }

    public static ServerHelper getInstance() {
        return Creator.instance;
    }

    public String encryptData(String content) { //加密数据
        if (serverConfig.encryptAble()) {
            return AES.getHttpAes(AES_KEY, IV, WAY).encryptAES(content);
        } else {
            return content;
        }
    }

    public String decryptData(String content) { //解密数据
        if (serverConfig.encryptAble()) {
            return AES.getHttpAes(AES_KEY, IV, WAY).decryptAES(content);
        } else {
            return content;
        }
    }

}

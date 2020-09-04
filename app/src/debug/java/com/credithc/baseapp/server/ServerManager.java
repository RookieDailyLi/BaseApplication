package com.credithc.baseapp.server;

import androidx.annotation.StringDef;

import com.credithc.baseapp.net.config.ReleaseServerConfig;
import com.credithc.baseapp.net.config.ServerHelper;
import com.credithc.baseapp.server.config.PreServerConfig;
import com.credithc.baseapp.server.config.TestServerConfig;
import com.credithc.commonlib.server.IServerConfig;

import java.lang.annotation.Retention;
import java.util.HashMap;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * @author liyong
 * @date 2019/8/31
 * @des debug包网络配置管理类
 */
public class ServerManager {

    public static final String ServerTestType = "测试"; //测试环境
    public static final String ServerPreType = "预发布";  // 预发布环境
    public static final String ServerReleaseType = "生产"; // 生产环境

    @Retention(SOURCE)
    @StringDef({ServerTestType, ServerPreType, ServerReleaseType})
    public @interface ServerType {

    }

    static final HashMap<String, IServerConfig> configs = new HashMap<>();

    static {
        configs.put(ServerTestType, new TestServerConfig());
        configs.put(ServerPreType, new PreServerConfig());
        configs.put(ServerReleaseType, new ReleaseServerConfig());
    }

    public static void config(@ServerType String serverType) {
        IServerConfig iServerConfig = configs.get(serverType);
        ServerHelper.getInstance().setServerConfig(iServerConfig);
    }

}

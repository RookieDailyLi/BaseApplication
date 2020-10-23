package com.credithc.baseapp.server;

import android.text.TextUtils;

import com.credithc.baseapp.lifecycle.ActivityServerConfigLifecycle;
import com.credithc.baseapp.net.config.ReleaseServerConfig;
import com.credithc.baseapp.net.config.ServerHelper;
import com.credithc.baseapp.server.config.PreServerConfig;
import com.credithc.baseapp.server.config.TestServerConfig;
import com.credithc.common.server.IServerConfig;
import com.credithc.common.util.SPManager;

import java.lang.annotation.Retention;
import java.util.HashMap;

import androidx.annotation.StringDef;

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

    public static String currentServerType = ServerTestType;//当前环境，默认测试环境

    @Retention(SOURCE)
    @StringDef({ServerTestType, ServerPreType, ServerReleaseType})
    public @interface ServerType {

    }

    static final HashMap<String, IServerConfig> serverTypeDictionary = new HashMap<>();

    static {
        serverTypeDictionary.put(ServerTestType, new TestServerConfig());
        serverTypeDictionary.put(ServerPreType, new PreServerConfig());
        serverTypeDictionary.put(ServerReleaseType, new ReleaseServerConfig());
    }

    public static void config(@ServerType String serverType) {
        String preServerType = SPManager.getManager(ActivityServerConfigLifecycle.SERVER_CONFIG).getString(ActivityServerConfigLifecycle.SERVER_TYPE);

        if (TextUtils.isEmpty(preServerType)) {
            currentServerType = serverType;
        } else {
            currentServerType = preServerType;
        }

        IServerConfig iServerConfig = serverTypeDictionary.get(currentServerType);
        ServerHelper.getInstance().setServerConfig(iServerConfig);
    }

}

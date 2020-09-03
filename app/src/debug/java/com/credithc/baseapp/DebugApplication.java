package com.credithc.baseapp;

import com.credithc.baseapp.helper.BlockCanaryHelper;
import com.credithc.baseapp.helper.LeakCanaryHelper;
import com.credithc.baseapp.helper.RouterDebugHelper;
import com.credithc.baseapp.lifecycle.FragmentLeakCanaryLifecycle;
import com.credithc.baseapp.server.ServerManager;
import com.credithc.commonlib.util.ResUtil;
import com.credithc.mvp.ui.lifecycle.ActivityLogLifecycle;
import com.credithc.mvp.ui.lifecycle.FragmentLogLifecycle;

/**
 * @author liyong
 * @date 2019/10/21 14:33
 * @description
 */
public class DebugApplication extends MyApplication {

    @Override
    protected void onceInstall() {
        RouterDebugHelper.install(this);
        super.onceInstall();
        LeakCanaryHelper.install(this);
        BlockCanaryHelper.install(this);
        addActivityLifeCycle(new ActivityLogLifecycle(ResUtil.getString(R.string.app_name)));
        addFragmentLifeCycle(new FragmentLogLifecycle(ResUtil.getString(R.string.app_name)));
        addFragmentLifeCycle(new FragmentLeakCanaryLifecycle());
        ServerManager.config(ServerManager.ServerTestType);
    }
}

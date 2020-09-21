package com.credithc.baseapp.route.executor;

import android.content.Context;

import com.credithc.common.util.ActivityManager;
import com.credithc.common.util.SysIntentUtil;

import java.util.Map;

/**
 * @author liyong
 * @date 2019/12/13
 * @des
 */
public class CallPhoneRouter implements IAppRouter {

    @Override
    public void navigation(String url, Map<String, String> params) {
        navigation(ActivityManager.current(), url, params);
    }

    @Override
    public void navigation(Context context, String url, Map<String, String> params) {
        String number = params.get("number");
        SysIntentUtil.openTel(context, number);
    }
}

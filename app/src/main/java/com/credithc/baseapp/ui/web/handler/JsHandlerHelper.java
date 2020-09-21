package com.credithc.baseapp.ui.web.handler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.alibaba.android.arouter.launcher.ARouter;
import com.credithc.baseapp.route.RouterConstants;
import com.credithc.baseapp.ui.web.activity.WebViewActivity;
import com.credithc.baseapp.ui.web.fragment.WebViewFragment;
import com.credithc.baseapp.util.UserUtil;
import com.credithc.common.util.AppUtil;
import com.credithc.common.util.ChannelUtils;
import com.credithc.common.util.DeviceUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author liyong
 * @date 2019/9/7 14:59
 * @description
 */
public class JsHandlerHelper {

    /**
     * 设置标题栏显示或隐藏
     *
     * @param webViewFragment
     * @param data
     */
    public static void handleTitle(WebViewFragment webViewFragment, String data) {
        try {
            JSONObject object = new JSONObject(data);
            int hidden = 0;
            if (object.has("hiddenBar")) {
                hidden = object.getInt("hiddenBar");
            }
            boolean hiddenBar = hidden == 1 ? true : false;
            if (hiddenBar) {
            } else {
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置标题栏显示或隐藏
     *
     * @param webActivity
     * @param data
     */
    public static void handleTitle(WebViewActivity webActivity, String data) {
        try {
            JSONObject object = new JSONObject(data);
            int hidden = 0;
            if (object.has("hiddenBar")) {
                hidden = object.getInt("hiddenBar");
            }
            boolean hiddenBar = hidden == 1 ? true : false;
            if (hiddenBar) {
                webActivity.setTitleBarGone();
            } else {
                webActivity.setTitleBarVisible();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setAppTitle(WebViewFragment webViewFragment, String data) {
        try {
            JSONObject object = new JSONObject(data);
            String title = "";
            if (object.has("title")) {
                title = object.getString("title");
            }
//            webViewFragment.setAppTitle(title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setAppTitle(WebViewActivity webActivity, String data) {
        try {
            JSONObject object = new JSONObject(data);
            String title = "";
            if (object.has("title")) {
                title = object.getString("title");
            }
            webActivity.setAppTitle(title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * 新打开WebViewActivity页面
     *
     * @param context
     * @param data
     */
    public static void openWebView(Context context, String data) {
        try {
            JSONObject object = new JSONObject(data);
            String url = object.getString("url");
            int hidden = 0;
            if (object.has("hiddenBar")) {
                hidden = object.getInt("hiddenBar");
            }
            boolean hiddenBar = hidden == 1 ? true : false;
            ARouter.getInstance()
                    .build(RouterConstants.ROUTER_NAME_WEBVIEW_KEY)
                    .withString(WebViewActivity.URL, url)
                    .withBoolean(WebViewActivity.HIDDEN_BAR, hiddenBar)
                    .navigation(context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void callPhone(Context context, String data) {
        try {
            JSONObject object = new JSONObject(data);
            String phone = object.getString("phone");
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + phone);
            intent.setData(uri);
            context.startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据解析data中的action决定跳转到指定界面
     *
     * @param activity
     * @param data
     */
    public static void openWin(Activity activity, String data) {
        JSONObject jsonObject = null;
        String action = "";
        JSONObject params = new JSONObject();
        int adressId = 0;
        try {
            jsonObject = new JSONObject(data);
            action = jsonObject.getString("winName");
            params = jsonObject.getJSONObject("params");
            adressId = params.has("adressId") ? params.getInt("adressId") : 0;
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取app信息
     */
    public static String getAppInfo(Context context) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", UserUtil.getToken());
            jsonObject.put("deviceVer", DeviceUtil.getDeviceType() + "_" + DeviceUtil.getSystemVersion());
            jsonObject.put("appName", AppUtil.getAppName());
            jsonObject.put("appVer", AppUtil.getVersionName());
            jsonObject.put("appMarket", ChannelUtils.getChannel("bassApp"));
            jsonObject.put("uid", UserUtil.getUId());
            jsonObject.put("iphoneidfa", AppUtil.getIMEI(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public static void tokenInvalid(Context context, String data) {
        UserUtil.clearUserData();
    }
}

package com.credithc.baseapp.ui.web.global;

/**
 * @author liyong
 * @date 2019/8/30 17:23
 * @description
 */
public class WebViewConstant {
    /**js调用java方法名*/
    public static final String HANDLER_NAME_CONTROL_TITLE = "controlAppNavType"; //控制app标题栏
    public static final String HANDLER_NAME_CLOSE_CUR_WIN = "closeCurWin"; //关闭当前窗口
    public static final String HANDLER_NAME_GET_APP_INFO = "getAppInfo";//获取app信息
    public static final String HANDLER_NAME_OPEN_WIN = "openWin";//打开某个页面
    public static final String HANDLER_NAME_OPEN_WEBVIEW = "openWebView";//打开WebView
    public static final String HANDLER_NAME_SEND_JS_TITLE = "sendJSTitle";//设置app标题
    public static final String HANDLER_NAME_CALL_PHONE = "callPhoneModal";//打电话
    public static final String HANDLER_NAME_DOWNLOAD_APP = "downloadApp";//下载
    public static final String HANDLER_NAME_CHOOSE_PHOTO = "choosePhoto";//选择照片
    public static final String HANDLER_NAME_TOKEN_INVALID = "h5TokenInvalid";//token失效



    /**java调用js方法名*/
    public static final String HANDLER_NAME_USER_ADDRESS = "getUserAddr"; //用户收货地址传给js
    public static final String HANDLER_NAME_REFRESH_WEB = "refreshJSWeb"; //刷新web
    public static final String HANDLER_NAME_PAY_EVENT = "payEvent"; //支付完成
}

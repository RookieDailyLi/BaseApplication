package com.credithc.baseapp.ui.web.handler;

import android.os.Message;

import com.credithc.baseapp.ui.web.activity.WebViewActivity;
import com.credithc.baseapp.ui.web.bean.BridgeMessageBean;
import com.credithc.baseapp.ui.web.global.WebViewConstant;
import com.credithc.common.widget.WeakActivityHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;

/**
 * @author liyong
 * @date 2019/8/23 15:55
 * @description
 */
public class JsHandlerForActivity extends WeakActivityHandler<WebViewActivity> {
    public JsHandlerForActivity(WebViewActivity target) {
        super(target);
    }

    @Override
    protected void handleMsg(Message message, WebViewActivity webActivity) {
        if (message.obj != null && message.obj instanceof BridgeMessageBean) {
            BridgeMessageBean bridgeMessageBean = (BridgeMessageBean) message.obj;
            String handlerName = bridgeMessageBean.handlerName;
            String data = bridgeMessageBean.data;
            CallBackFunction function = bridgeMessageBean.function;
            switch (handlerName) {
                case WebViewConstant.HANDLER_NAME_CONTROL_TITLE://控制app标题栏
                    JsHandlerHelper.handleTitle(webActivity, data);
                    function.onCallBack("{}");
                    break;
                case WebViewConstant.HANDLER_NAME_SEND_JS_TITLE://设置app标题
                    JsHandlerHelper.setAppTitle(webActivity, data);
                    break;
                case WebViewConstant.HANDLER_NAME_CLOSE_CUR_WIN://关闭当前窗口
                    webActivity.finish();
                    break;
                case WebViewConstant.HANDLER_NAME_GET_APP_INFO://获取app信息
                    function.onCallBack(JsHandlerHelper.getAppInfo(webActivity));
                    break;
                case WebViewConstant.HANDLER_NAME_OPEN_WIN://打开某个页面
                    JsHandlerHelper.openWin(webActivity, data);
                    break;
                case WebViewConstant.HANDLER_NAME_OPEN_WEBVIEW://新打开WebViewActivity页
                    JsHandlerHelper.openWebView(webActivity, data);
                    break;
                case WebViewConstant.HANDLER_NAME_CALL_PHONE://打电话
                    JsHandlerHelper.callPhone(webActivity, data);
                    break;
                case WebViewConstant.HANDLER_NAME_TOKEN_INVALID://token失效
                    JsHandlerHelper.tokenInvalid(webActivity, data);
                    break;
            }
        }
    }
}
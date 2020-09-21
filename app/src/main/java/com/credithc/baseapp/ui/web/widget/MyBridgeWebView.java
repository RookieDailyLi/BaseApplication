package com.credithc.baseapp.ui.web.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.credithc.baseapp.ui.web.bean.BridgeMessageBean;
import com.credithc.baseapp.ui.web.global.WebPageListener;
import com.credithc.common.GlobalContext;
import com.credithc.common.util.LogUtil;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.github.lzyzsd.jsbridge.DefaultHandler;

import java.util.List;

/**
 * @author liyong
 * @date 2019/8/23 14:52
 * @description
 */
public class MyBridgeWebView extends BridgeWebView {

    private WebPageListener mPageListener;

    public MyBridgeWebView(Context context) {
        super(context);
        initWebView();
    }

    public MyBridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebView();
    }

    public MyBridgeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWebView();
    }


    public void setPageListener(WebPageListener pageListener) {
        this.mPageListener = pageListener;
    }

    private void initWebView() {
        getSettings().setJavaScriptEnabled(true);
        getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //双击缩放
        getSettings().setSupportZoom(true);
        //手势缩放
        getSettings().setBuiltInZoomControls(true);
        //隐藏缩放工具
        getSettings().setDisplayZoomControls(false);
        //扩大比例的缩放
        getSettings().setUseWideViewPort(true);
        //设置user-agent
        getSettings().setUserAgentString(getSettings().getUserAgentString() + "stageMall");
        //自适应屏幕
        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        getSettings().setLoadWithOverviewMode(true);
        setWebViewClient(new BridgeWebViewClient(this) {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (mPageListener != null) {
                    mPageListener.pageStart(view, url);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (mPageListener != null) {
                    mPageListener.pageFinish(view, url);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (mPageListener != null) {
                    mPageListener.pageError();
                }
            }
        });
        setWebChromeClient(new WebChromeClient() {
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title) && !view.getUrl().contains(title)) {
                    super.onReceivedTitle(view, title);
                    if (mPageListener != null) {
                        mPageListener.pageTitle(view, title);
                    }
                }
            }

            public void onProgressChanged(WebView view, int newProgress) {
                if (mPageListener != null) {
                    mPageListener.pageProgress(view, newProgress);
                }
            }

        });
        getSettings().setDomStorageEnabled(true); // 开启 DOM storage API 功能
        getSettings().setDatabaseEnabled(true);   //开启 database storage API 功能
        getSettings().setAppCacheEnabled(true);//开启 Application Caches 功能
        setDefaultHandler(new DefaultHandler());
        //内容混合模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    /**
     * 清空cookie
     */
    public void clear() {
        CookieSyncManager.createInstance(GlobalContext.getContext());
        CookieSyncManager.getInstance().sync();
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        setWebChromeClient(null);
        setWebViewClient(null);
        getSettings().setJavaScriptEnabled(false);
        clearCache(true);
        clearFormData();
    }

    /**
     * 注册本地java方法，以供js端调用
     *
     * @param handlerName 方法名称
     * @param handler     处理msg
     */
    public void registerHandler(final String handlerName, Handler handler) {
        registerHandler(handlerName, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                if (handler != null) {
                    Message msg = handler.obtainMessage();
                    BridgeMessageBean bridgeMessageBean = new BridgeMessageBean();
                    bridgeMessageBean.handlerName = handlerName;
                    bridgeMessageBean.data = data;
                    bridgeMessageBean.function = function;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    /**
     * 批量注册本地java方法，以供js端调用
     *
     * @param handlerNames 方法名称数组
     * @param handler      处理msg
     */
    public void registerHandlers(final List<String> handlerNames, Handler handler) {
        if (handler != null) {
            for (final String handlerName : handlerNames) {
                registerHandler(handlerName, new BridgeHandler() {
                    @Override
                    public void handler(String data, CallBackFunction function) {
                        LogUtil.e("JsCallJava, handlerName = %s, data = %s", handlerName, data);
                        if (handler != null) {
                            Message msg = handler.obtainMessage();
                            BridgeMessageBean bridgeMessageBean = new BridgeMessageBean();
                            bridgeMessageBean.handlerName = handlerName;
                            bridgeMessageBean.data = data;
                            bridgeMessageBean.function = function;
                            msg.obj = bridgeMessageBean;
                            handler.sendMessage(msg);
                        }
                    }
                });
            }
        }
    }

}

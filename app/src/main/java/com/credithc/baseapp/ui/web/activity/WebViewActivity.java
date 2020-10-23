package com.credithc.baseapp.ui.web.activity;

import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.credithc.baseapp.R;
import com.credithc.baseapp.route.RouterConstants;
import com.credithc.baseapp.ui.web.global.WebPageListener;
import com.credithc.baseapp.ui.web.global.WebViewConstant;
import com.credithc.baseapp.ui.web.handler.JsHandlerForActivity;
import com.credithc.baseapp.ui.web.widget.MyBridgeWebView;
import com.credithc.common.util.NetUtil;
import com.credithc.mvp.presenter.BasePresenter;
import com.credithc.mvp.view.activity.BaseFragmentActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;

@Route(path = RouterConstants.ROUTER_PATH_WEBVIEW, name = RouterConstants.ROUTER_NAME_WEBVIEW_KEY)
public class WebViewActivity extends BaseFragmentActivity implements WebPageListener {
    public static final String URL = "url";
    public static final String DATA = "data";
    public static final String HIDDEN_BAR = "hiddenBar";

    MyBridgeWebView mBridgeWebView;
    public String url;
    private JSONObject data;
    private JsHandlerForActivity mHandler;
    private List<String> jsHandlerNameList;
    public boolean hiddenBar;

    @Override
    protected int layoutContentId() {
        return R.layout.activity_webview;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initBundle(@NonNull Bundle bundle) {
        url = bundle.getString(URL);
        hiddenBar = bundle.getBoolean(HIDDEN_BAR, false);
        if (getIntent().hasExtra(DATA)) {
            String dataStr = getIntent().getStringExtra(DATA);
            if (dataStr == null) {
                return;
            }
            try {
                data = new JSONObject(dataStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void setUpView() {
        initJsHandler();
        mBridgeWebView = findViewById(R.id.web_view);
        mBridgeWebView.setPageListener(this);
        mBridgeWebView.registerHandlers(jsHandlerNameList, mHandler);

        mTitleBar.setLeftBackClickListener(v -> {
            if (mBridgeWebView.canGoBack()) {
                mBridgeWebView.goBack();
            } else {
                finish();
            }
        });
    }

    /**
     * 添加js调用java方法
     */
    private void initJsHandler() {
        jsHandlerNameList = new ArrayList<>();
        //控制app标题栏
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_CONTROL_TITLE);
        //关闭当前窗口
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_CLOSE_CUR_WIN);
        //获取app信息
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_GET_APP_INFO);
        //打开某个页面
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_OPEN_WIN);
        //新打开WebView页面
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_OPEN_WEBVIEW);
        //设置页面标题
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_SEND_JS_TITLE);
        //打电话
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_CALL_PHONE);
        //下载app
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_DOWNLOAD_APP);
        //选择照片
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_CHOOSE_PHOTO);
        //token失效
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_TOKEN_INVALID);

        mHandler = new JsHandlerForActivity(this);
    }


    @Override
    public void loadData() {
        showLoadCovers();
        if (!NetUtil.isNetConnected()) {
            mTitleBar.setTitleText("");
            showNetException();
            return;
        }

        if (!URLUtil.isValidUrl(url)) {
            mTitleBar.setTitleText("");
            showLoadFailure();
            return;
        }

        if (data == null) {
            mBridgeWebView.loadUrl(url);
        } else {
            doPost();
        }
    }

    private void doPost() {
        StringBuilder stringBuilder = new StringBuilder();
        if (data != null) {
            Iterator it = data.keys();
            while (it.hasNext()) {
                String key = (String) it.next();
                stringBuilder.append(key).append("=").append(URLEncoder.encode(data.optString(key))).append("&");
            }
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        mBridgeWebView.postUrl(url, stringBuilder.toString().getBytes());
    }

    @Override
    public void pageStart(WebView view, String url) {
        showLoading();
    }

    @Override
    public void pageProgress(WebView view, int newProgress) {

    }

    @Override
    public void pageFinish(WebView view, String url) {
        loadComplete();
    }

    @Override
    public void pageError() {
        mTitleBar.setTitleText("");
        showLoadFailure();
    }

    @Override
    public void pageTitle(WebView view, String title) {
        mTitleBar.setTitleText(title);
    }

    public void setAppTitle(String title) {
        mTitleBar.setTitleText(title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清空所有Cookie
        mBridgeWebView.clear();
    }

    @Override
    public void onBackPressed() {
        if (mBridgeWebView.canGoBack()) {
            mBridgeWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

package com.credithc.baseapp.ui.web.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.credithc.baseapp.R;
import com.credithc.baseapp.ui.web.global.WebPageListener;
import com.credithc.baseapp.ui.web.global.WebViewConstant;
import com.credithc.baseapp.ui.web.handler.JsHandlerForFragment;
import com.credithc.baseapp.ui.web.widget.MyBridgeWebView;
import com.credithc.common.util.NetUtil;
import com.credithc.mvp.presenter.BasePresenter;
import com.credithc.mvp.ui.fragment.RxFragment;
import com.credithc.mvp.view.RxBaseView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * @author liyong
 * @date 2019/8/22 20:47
 * @description
 */
public class WebViewFragment extends RxFragment implements RxBaseView, WebPageListener {
    private static final String URL = "url";
    private static final String DATA = "data";
    MyBridgeWebView mBridgeWebView;
    private JSONObject data;
    private JsHandlerForFragment mHandler;
    private List<String> jsHandlerNameList;
    private String url;

    public static WebViewFragment newInstance(String url) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle args = new Bundle();
        args.putString(URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initBundle(@NonNull Bundle bundle) {
        String jsonData = bundle.getString(DATA);
        url = bundle.getString(URL);
        if (!TextUtils.isEmpty(jsonData)) {
            try {
                data = new JSONObject(jsonData);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void initView(View view) {
        initJsHandler();
        initWebView(view);
    }

    /**
     * 添加js调用java方法
     */
    private void initJsHandler() {
        jsHandlerNameList = new ArrayList<>();
        //控制app标题栏
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_CONTROL_TITLE);
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
        //token失效
        jsHandlerNameList.add(WebViewConstant.HANDLER_NAME_TOKEN_INVALID);
        mHandler = new JsHandlerForFragment(this);
    }


    private void initWebView(View view) {
        mBridgeWebView = view.findViewById(R.id.web_view);
        mBridgeWebView.setPageListener(this);
        mBridgeWebView.registerHandlers(jsHandlerNameList, mHandler);
        mBridgeWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    @Override
    protected int layoutContentId() {
        return R.layout.fragment_webview;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void loadData() {
        if (!NetUtil.isNetConnected()) {
            showNetException();
            return;
        }

        if (!URLUtil.isValidUrl(url)) {
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
        dismissLoading();
        showLoadFailure();
    }

    @Override
    public void pageTitle(WebView view, String title) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //清空所有Cookie
        mBridgeWebView.clear();
    }
}

package com.credithc.baseapp.ui.web.global;

import android.webkit.WebView;

/**
 * @author liyong
 * @date 2019/8/22 21:15
 * @description
 */
public interface WebPageListener {
    void pageStart(WebView view, String url);

    void pageProgress(WebView view, int newProgress);

    void pageFinish(WebView view, String url);

    void pageError();

    void pageTitle(WebView view, String title);
}

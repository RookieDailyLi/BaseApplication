package com.credithc.common.listener;

import android.view.View;

/**
 * @author liyong
 * @date 2019/10/12 8:34
 * @description 按钮去重点击listener
 */
public abstract class OnFastClickListener implements View.OnClickListener {
    private long lastClickTime = 0;

    @Override
    public void onClick(View v) {
        long currentClickTime = System.currentTimeMillis();
        long timeSpace = currentClickTime - lastClickTime;
        if (timeSpace >= 1000) {
            noFastClick();
        }
        lastClickTime = currentClickTime;
    }

    public abstract void noFastClick();
}

package com.credithc.baseapp;

import android.view.View;

/**
 * @author liyong
 * @date 2019/10/12 8:34
 * @description
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

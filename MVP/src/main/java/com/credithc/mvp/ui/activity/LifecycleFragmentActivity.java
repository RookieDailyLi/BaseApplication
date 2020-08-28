package com.credithc.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.credithc.mvp.ui.lifecycle.ActivityLifecycle;

/**
 * @author liyong
 * @date 2020/8/13
 * @des
 */

public abstract class LifecycleFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLifecycle.onCreate(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ActivityLifecycle.onRestart(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ActivityLifecycle.onResume(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityLifecycle.onStart(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        ActivityLifecycle.onPause(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        ActivityLifecycle.onStop(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityLifecycle.onDestroy(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ActivityLifecycle.onNewIntent(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ActivityLifecycle.onWindowFocusChanged(this, hasFocus);
    }
}
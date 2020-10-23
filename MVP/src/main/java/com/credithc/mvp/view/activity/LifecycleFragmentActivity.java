package com.credithc.mvp.view.activity;

import android.content.Intent;
import android.os.Bundle;

import com.credithc.mvp.lifecycle.ActivityLifecyclePublisher;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;


/**
 * @author liyong
 * @date 2020/8/13
 * @des
 */

public abstract class LifecycleFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLifecyclePublisher.onCreate(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        ActivityLifecyclePublisher.onRestart(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ActivityLifecyclePublisher.onResume(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ActivityLifecyclePublisher.onStart(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        ActivityLifecyclePublisher.onPause(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        ActivityLifecyclePublisher.onStop(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityLifecyclePublisher.onDestroy(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ActivityLifecyclePublisher.onNewIntent(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ActivityLifecyclePublisher.onWindowFocusChanged(this, hasFocus);
    }
}
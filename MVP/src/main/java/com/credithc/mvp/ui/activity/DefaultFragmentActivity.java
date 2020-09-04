package com.credithc.mvp.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

/**
 * @author liyong
 * @date 2020/8/13 17:16
 * @description
 */
public abstract class DefaultFragmentActivity extends LifecycleFragmentActivity {
    protected FragmentActivity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(getActivityLayoutId());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            initBundle(bundle);
        }
    }

    protected abstract int getActivityLayoutId();

    @Override
    protected final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            initNewBundle(bundle);
        }
    }

    protected void initBundle(@NonNull Bundle bundle) {

    }

    protected void initNewBundle(@NonNull Bundle bundle) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 设置显示、字体大小不跟随系统设置而改变
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration config = new Configuration();
            config.setToDefaults();//设置默认
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
        return res;
    }
}

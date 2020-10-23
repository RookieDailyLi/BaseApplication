package com.credithc.baseapp.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.IntDef;

import com.credithc.baseapp.R;
import com.credithc.baseapp.ui.main.fragment.HomeFragment;
import com.credithc.baseapp.ui.main.fragment.MineFragment;
import com.credithc.baseapp.ui.main.fragment.ProjectFragment;
import com.credithc.common.util.AppUtil;
import com.credithc.common.util.ToastUtil;
import com.credithc.common.widget.TabManager;
import com.credithc.mvp.presenter.BasePresenter;
import com.credithc.mvp.view.activity.RxFragmentActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainActivity extends RxFragmentActivity {

    TabManager tabManager;
    private int currentIndex = -1;

    @Override
    protected int layoutContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void setUpView() {
        setTitleBarGone();
        configTab();
    }

    private void configTab() {
        tabManager = new TabManager(this, findViewById(android.R.id.tabhost), android.R.id.tabcontent);
        tabManager.addTab(R.string.app_tab_home, R.layout.main_tab_home, HomeFragment.class);
        tabManager.addTab(R.string.app_tab_project, R.layout.main_tab_project, ProjectFragment.class);
        tabManager.addTab(R.string.app_tab_mine, R.layout.main_tab_mine, MineFragment.class);
        if (currentIndex > 0) {
            tabManager.setCurrentTab(currentIndex);
        }
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showToast("再按一次退出");
            exitTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();
            AppUtil.shutDownApp();
        }
    }


    public static final int MAIN_HOME = 0;
    public static final int MAIN_PROJECT = 1;
    public static final int MAIN_MINE = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MAIN_HOME, MAIN_PROJECT, MAIN_MINE})
    public @interface MainIndex {
    }

    public static final String INDEX = "INDEX";

    /**
     * 跳转到首页某个tab页面
     *
     * @param context
     * @param index   tab索引
     */
    public static void launch(Context context, @MainIndex int index) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        intent.setClass(context, MainActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void loadData() {

    }
}

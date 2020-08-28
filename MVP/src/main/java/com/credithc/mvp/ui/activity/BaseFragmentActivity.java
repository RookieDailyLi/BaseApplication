package com.credithc.mvp.ui.activity;

import android.arch.lifecycle.LifecycleObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.credithc.commonlib.util.LoadingUtil;
import com.credithc.commonlib.util.ResUtil;
import com.credithc.commonlib.util.ViewUtil;
import com.credithc.commonlib.widget.TitleBar;
import com.credithc.mvp.R;
import com.credithc.mvp.presenter.BasePresenter;
import com.credithc.mvp.ui.exception.BaseExceptionLayout;
import com.credithc.mvp.ui.exception.ExceptionLayout;
import com.credithc.mvp.view.BaseView;


/**
 * @author liyong
 * @date 2019/11/6
 * @des
 */
public abstract class BaseFragmentActivity<P extends BasePresenter> extends DefaultFragmentActivity implements BaseView {
    protected P presenter;
    protected TitleBar mTitleBar;
    protected FrameLayout mContent;//具体内容页
    protected RelativeLayout mContentRoot;
    protected BaseExceptionLayout exceptionLayout;
    protected View.OnClickListener DefaultOnClickListener = v -> loadAgain();
    protected View.OnClickListener emptyOnClickListener = DefaultOnClickListener;  // 空白点击重试
    protected View.OnClickListener loadFailOnClickListener = DefaultOnClickListener; // 错误点击重试
    protected View.OnClickListener netExceptionOnClickListener = DefaultOnClickListener; // 网络异常点击重试
    protected int errorTopMargin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        addObserver(presenter);
        initRootView();
        setUpRootView();
        setUpView();
        loadData();
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_base;
    }

    protected void initRootView() {
        mTitleBar = findViewById(R.id.titleBar);
        mContent = findViewById(R.id.base_activity_content);
        mContentRoot = findViewById(R.id.rl_root);
    }

    protected void setUpRootView() {
        setTitleBarVisible();
        mContent.removeAllViews();
        mContent.addView(View.inflate(this, layoutContentId(), null));
    }

    protected abstract P createPresenter();

    protected abstract int layoutContentId();

    protected abstract void setUpView();

    protected abstract void loadData();


    /**
     * 绑定Activity生命周期,在onDestroy()解绑View,Model层
     */
    @Override
    public void addObserver(LifecycleObserver observer) {
        if (observer != null) {
            getLifecycle().addObserver(observer);
        }
    }

    protected void setTitleBarGone() {
        ViewUtil.setGone(mTitleBar);
        errorTopMargin = 0;
    }

    protected void setTitleBarVisible() {
        ViewUtil.setVisible(mTitleBar);
        errorTopMargin = ResUtil.getDimen(R.dimen.title_bar_height);
    }

    @Override
    public void showLoading(String tips, boolean cancelAble) {
        LoadingUtil.showLoading(this, tips, cancelAble, R.style.confirm_dialog);
    }

    @Override
    public void dismissLoading() {
        LoadingUtil.dismissLoading(this);
    }

    /**
     * 加载完成
     */
    @Override
    public void loadComplete() {
        if (exceptionLayout != null) {
            exceptionLayout.dismiss();
        }
        showLoadDataView();
    }

    protected void loadAgain() {
        loadData();
    }

    // 添加遮罩
    @Override
    public void showLoadCovers() {
        showLoadCovers(Color.WHITE);
    }

    @Override
    public void showLoadCovers(int color) {
        showExceptionView(false);
        exceptionLayout.showLoadCovers(color);
    }

    // 加载无数据
    @Override
    public void showEmpty(int errorIcon, String tv, String btn) {
        showExceptionView();
        exceptionLayout.showEmpty(errorIcon, tv, btn);
        exceptionLayout.setLoadEmptyOnClickListener(emptyOnClickListener);
    }


    @Override
    public void showNetException(int netIcon, String tv, String btn) {
        showExceptionView();
        exceptionLayout.showNetException(netIcon, tv, btn);
        exceptionLayout.setNetExceptionOnClickListener(netExceptionOnClickListener);
    }

    @Override
    public void showLoadFailure(int errorIcon, String tv, String btn) {
        showExceptionView();
        exceptionLayout.showLoadFailure(errorIcon, tv, btn);
        exceptionLayout.setLoadFailureOnClickListener(loadFailOnClickListener);
    }

    // 显示有数据的View
    protected void showLoadDataView() {
        ViewUtil.setVisible(mContent);
        dismissLoading();
    }

    // 显示错误的View
    protected void showExceptionView() {
        showExceptionView(true);
    }

    // 显示错误的View
    protected void showExceptionView(boolean disMissLoading) {
        ViewUtil.setGone(mContent);
        addExceptionLayout();
        if (disMissLoading) {
            dismissLoading();
        }
    }

    protected void addExceptionLayout() {
        if (exceptionLayout == null) {
            exceptionLayout = createExceptionLayout();
        }
        exceptionLayout.showAsDropDown(mContentRoot, errorTopMargin);
    }

    protected BaseExceptionLayout createExceptionLayout() {
        ExceptionLayout exceptionLayout = new ExceptionLayout(this);
        exceptionLayout.setBackgroundColor(Color.WHITE);
        return exceptionLayout;
    }
}

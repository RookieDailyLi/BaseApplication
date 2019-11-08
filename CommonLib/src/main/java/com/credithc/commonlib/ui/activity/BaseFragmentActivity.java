package com.credithc.commonlib.ui.activity;

import android.arch.lifecycle.Lifecycle;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.credithc.commonlib.R;
import com.credithc.commonlib.mvp.p.BasePresenter;
import com.credithc.commonlib.mvp.v.BaseView;
import com.credithc.commonlib.util.LoadingUtil;
import com.credithc.commonlib.util.ResUtil;
import com.credithc.commonlib.util.ViewUtil;
import com.credithc.commonlib.widget.TitleBar;
import com.credithc.commonlib.widget.excpetion.BaseExceptionLayout;
import com.credithc.commonlib.widget.excpetion.ExceptionLayout;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * @author liyong
 * @date 2019/11/6
 * @des
 */
public abstract class BaseFragmentActivity<P extends BasePresenter> extends FragmentActivity implements BaseView {
    protected P presenter;
    protected TitleBar mTitleBar;
    protected FrameLayout mContent;//具体内容页
    protected FragmentActivity mActivity;
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
        mActivity = this;
        presenter = createPresenter();
        setContentView(getActivityLayoutId());
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            initBundle(bundle);
        }
        initRootView();
        setUpRootView();
        setUpView();
        loadData();
    }

    protected void initBundle(@NonNull Bundle bundle) {

    }

    protected int getActivityLayoutId() {
        return R.layout.activity_bases;
    }

    protected void initRootView() {
        mTitleBar = ViewUtil.findViewById(this, R.id.titleBar);
        mContent = ViewUtil.findViewById(this, R.id.base_activity_content);
        mContentRoot = ViewUtil.findViewById(this, R.id.rl_root);
    }

    protected void setUpRootView() {
        setTitleBarVisible();
        mContent.removeAllViews();
        mContent.addView(View.inflate(this, layoutContentId(), null));
    }

    protected abstract int layoutContentId();

    protected abstract P createPresenter();

    protected abstract void setUpView();


    /**
     * 首次请求
     */
    protected abstract void loadData();

    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T>
     * @return
     */
    @Override
    public final <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }


    protected void setTitleBarGone() {
        ViewUtil.setGone(mTitleBar);
        errorTopMargin = 0;
    }

    protected void setTitleBarVisible() {
        ViewUtil.setVisible(mTitleBar);
        errorTopMargin = ResUtil.getDimen(R.dimen.title_bar_height);
    }

    protected void onCoversHiddenChanged(boolean isHidden) {
        if (isHidden) {
            setTitleBarVisible();
        }
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
            exceptionLayout.dissmiss();
        }
        showLoadDataView();
    }


    protected void loadAgain() {

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
    protected void showExceptionView(boolean isdissMissLoading) {
        ViewUtil.setGone(mContent);
        addExceptionLayout();
        if (isdissMissLoading) {
            dismissLoading();
        }
    }


    protected void addExceptionLayout() {
        if (exceptionLayout == null) {
            exceptionLayout = createExceptionLayout();
        }
        exceptionLayout.showAsDropDown(mContentRoot, errorTopMargin);
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

    protected BaseExceptionLayout createExceptionLayout() {
        ExceptionLayout exceptionLayout = new ExceptionLayout(this);
        exceptionLayout.setBackgroundColor(Color.WHITE);
        return exceptionLayout;
    }
}

package com.credithc.mvp.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.credithc.common.util.LoadingUtil;
import com.credithc.common.util.ViewUtil;
import com.credithc.mvp.R;
import com.credithc.mvp.presenter.BasePresenter;
import com.credithc.mvp.error.BaseExceptionLayout;
import com.credithc.mvp.error.ExceptionLayout;
import com.credithc.mvp.view.BaseView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;

/**
 * @author liyong
 * @date 2020/8/27
 * @des
 */

public abstract class BaseFragment<P extends BasePresenter> extends DefaultFragment implements BaseView {

    protected P presenter;
    protected View contentView;//具体内容页
    protected FrameLayout rootView;// 根View
    protected BaseExceptionLayout exceptionLayout;

    protected View.OnClickListener DefaultOnClickListener = v -> loadAgain();
    protected View.OnClickListener emptyOnClickListener = DefaultOnClickListener;  // 空白点击重试
    protected View.OnClickListener loadFailOnClickListener = DefaultOnClickListener; // 错误点击重试
    protected View.OnClickListener netExceptionOnClickListener = DefaultOnClickListener; // 网络异常点击重试

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        addObserver(presenter);
    }

    @Override
    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (FrameLayout) createRootView();
        return rootView;
    }

    // fix 重新创建
    protected View createRootView() {
        if (rootView != null) {
            return rootView;
        }
        FrameLayout rootView = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_base, null);
        setUpRootView(rootView);
        return rootView;
    }

    protected void setUpRootView(ViewGroup rootView) {
        contentView = LayoutInflater.from(getActivity()).inflate(layoutContentId(), null);
        rootView.addView(contentView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    @Override
    public void addObserver(LifecycleObserver observer) {
        if (observer != null) {
            getLifecycle().addObserver(observer);
        }
    }

    protected abstract int layoutContentId();

    public abstract P createPresenter();

    protected abstract void initView(View view);

    protected abstract void loadData();


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
        ViewUtil.setVisible(contentView);
        dismissLoading();
    }

    // 显示错误的View
    protected void showExceptionView() {
        showExceptionView(true);
    }

    // 显示错误的View
    protected void showExceptionView(boolean isdissMissLoading) {
        ViewUtil.setGone(contentView);
        addExceptionLayout();
        if (isdissMissLoading) {
            dismissLoading();
        }
    }

    protected final void addExceptionLayout() {
        if (exceptionLayout == null) {
            exceptionLayout = createExceptionLayout();
        }
        exceptionLayout.show(rootView);
    }

    protected BaseExceptionLayout createExceptionLayout() {
        ExceptionLayout exceptionLayout = new ExceptionLayout(mActivity);
        exceptionLayout.setBackgroundColor(Color.WHITE);
        return exceptionLayout;
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

    protected <T extends View> T findViewById(@IdRes int viewId) {
        return ViewUtil.findViewById(rootView, viewId);
    }
}

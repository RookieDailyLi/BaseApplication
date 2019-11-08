package com.credithc.commonlib.ui.fragment;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.credithc.commonlib.R;
import com.credithc.commonlib.mvp.p.RxBasePresenter;
import com.credithc.commonlib.mvp.v.BaseView;
import com.credithc.commonlib.util.LoadingUtil;
import com.credithc.commonlib.util.ViewUtil;
import com.credithc.commonlib.widget.excpetion.BaseExceptionLayout;
import com.credithc.commonlib.widget.excpetion.ExceptionLayout;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * @author liyong
 * @date 2019/11/8
 * @des
 */
public abstract class BaseFragment<P extends RxBasePresenter> extends Fragment implements BaseView {
    protected P presenter;
    protected View mContent;//具体内容页
    protected View rootView;// 根View
    protected FragmentActivity mActivity;
    protected FrameLayout rootContentView; // 具体内容页包裹View  异常View 父布局
    protected BaseExceptionLayout exceptionLayout;

    protected View.OnClickListener DefaultOnClickListener = v -> loadAgain();
    protected View.OnClickListener emptyOnClickListener = DefaultOnClickListener;  // 空白点击重试
    protected View.OnClickListener loadFailOnClickListener = DefaultOnClickListener; // 错误点击重试
    protected View.OnClickListener netExceptionOnClickListener = DefaultOnClickListener; // 网络异常点击重试

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle == null) {
            return;
        }
        initBundle(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return createView(inflater, container, savedInstanceState);
    }

    public View createView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = createRootView();
        if (presenter == null) {
            presenter = createPresenter();
        }
        return rootView;
    }

    // fix 重新创建
    protected View createRootView() {
        if (rootView != null) {
            return rootView;
        }
        View rootView = ViewUtil.inflate(layoutRootViewId());
        initRootView(rootView);
        setUpRootView(rootView);
        return rootView;
    }

    protected int layoutRootViewId() {
        return R.layout.fragment_root;
    }

    protected void initRootView(View rootView) {
        rootContentView = ViewUtil.findFLById(rootView, R.id.fl_root);
        mContent = ViewUtil.inflate(layoutContentId());
        rootContentView.addView(mContent, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    protected void initBundle(@NonNull Bundle bundle) {

    }

    protected void setUpRootView(View rootView) {

    }

    protected abstract int layoutContentId();

    protected abstract void setUpView(View contentView);

    protected abstract P createPresenter();

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

    @Override
    public void onResume() {
        super.onResume();
        if (isAdded() && !isHidden()) {
            onVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isVisible()) {
            onHidden();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            onVisible();
        } else {
            onHidden();
        }
    }

    /**
     * fragment可见的时候操作，且在可见状态切换到可见的时候调用，解决重复调用
     */
    protected void onVisible() {

    }

    /**
     * fragment不可见的时候操作调用
     */
    protected void onHidden() {
        dismissLoading();
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
        ExceptionLayout exceptionLayout = new ExceptionLayout(mActivity);
        exceptionLayout.setBackgroundColor(Color.WHITE);
        return exceptionLayout;
    }


    protected final void addExceptionLayout() {
        if (exceptionLayout == null) {
            exceptionLayout = createExceptionLayout();
        }
        exceptionLayout.show(rootContentView);
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

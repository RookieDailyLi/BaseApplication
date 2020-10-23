package com.credithc.mvp.view.fragment;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.credithc.common.util.NetUtil;
import com.credithc.common.util.ToastUtil;
import com.credithc.common.util.ViewUtil;
import com.credithc.mvp.R;
import com.credithc.mvp.presenter.RxBasePresenter;
import com.credithc.mvp.view.RxBaseView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * @author liyong
 * @date 2020/8/27
 * @des
 */

public abstract class RxRefreshFragment<P extends RxBasePresenter> extends RxFragment<P> implements RxBaseView {

    protected SmartRefreshLayout smartRefreshLayout;

    protected int getFragmentLayoutId() {
        return R.layout.fragment_refresh_base;
    }

    @Override
    protected void setUpRootView(ViewGroup rootView) {
        View refreshRootView = LayoutInflater.from(mActivity).inflate(getFragmentLayoutId(),null);
        rootView.removeAllViews();
        rootView.addView(refreshRootView);
        smartRefreshLayout = (SmartRefreshLayout) refreshRootView;
        contentView = rootView.findViewById(R.id.base_fragment_content);
        ViewGroup contentViewGroup = (ViewGroup) contentView;
        contentViewGroup.removeAllViews();
        LayoutInflater.from(mActivity).inflate(layoutContentId(), contentViewGroup);
        //设置刷新相关
        setEnableRefresh(true);
        setEnableLoadMore(false);
        setDefaultRefreshHeaderFooter();
        setOnRefreshLoadMoreListener();
        //不启用列表惯性滑动到底部时自动加载更多
        smartRefreshLayout.setEnableAutoLoadMore(false);
    }

    /**
     * 设置下拉刷新是否可用
     *
     * @param enable
     */
    protected void setEnableRefresh(boolean enable) {
        smartRefreshLayout.setEnableRefresh(enable);
    }

    /**
     * 设置上拉加载是否可用
     *
     * @param enable
     */
    protected void setEnableLoadMore(boolean enable) {
        smartRefreshLayout.setEnableLoadMore(enable);
    }

    /**
     * 设置通用的刷新header和footer
     */
    protected void setDefaultRefreshHeaderFooter() {
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(mActivity));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(mActivity));
    }

    /**
     * 设置下拉刷新和上拉加载监听
     */
    private void setOnRefreshLoadMoreListener() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (!NetUtil.isNetConnected()) {
                    ToastUtil.showToast(mActivity.getString(R.string.net_exception_msg));
                    onRefreshComplete(100);
                    return;
                }
                pullDownRefresh();

            }
        });

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (!NetUtil.isNetConnected()) {
                    ToastUtil.showToast(mActivity.getString(R.string.net_exception_msg));
                    onLoadMoreComplete(100);
                    return;
                }
                pullUpLoadMore();
            }
        });
    }

    /**
     * 下拉刷新
     */
    @CallSuper
    protected void pullDownRefresh() {
        smartRefreshLayout.setNoMoreData(false);
    }

    /**
     * 上拉加载
     */
    protected void pullUpLoadMore() {
    }

    public void onRefreshComplete(int delayTime) {
        if (smartRefreshLayout != null && smartRefreshLayout.getState().isOpening) {
            smartRefreshLayout.finishRefresh(delayTime);
        }
    }

    public void onLoadMoreComplete(int delayTime) {
        if (smartRefreshLayout != null && smartRefreshLayout.getState().isOpening) {
            smartRefreshLayout.finishLoadMore(delayTime);
        }
    }

    public void onLoadMoreComplete(int delayTime, boolean noMoreData) {
        if (smartRefreshLayout != null && smartRefreshLayout.getState().isOpening) {
            smartRefreshLayout.finishLoadMore(delayTime, true, noMoreData);
        }
    }

    @Override
    protected void showLoadDataView() {
        super.showLoadDataView();
        ViewUtil.setVisible(smartRefreshLayout);
    }

    @Override
    protected void showExceptionView() {
        super.showExceptionView();
        ViewUtil.setGone(smartRefreshLayout);
    }

    @Override
    public void dismissLoading() {
        super.dismissLoading();
        onRefreshComplete(300);
        onLoadMoreComplete(300);
    }
}

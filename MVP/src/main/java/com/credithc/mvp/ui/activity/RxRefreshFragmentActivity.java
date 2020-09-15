package com.credithc.mvp.ui.activity;

import androidx.annotation.CallSuper;

import com.credithc.common.util.ViewUtil;
import com.credithc.mvp.R;
import com.credithc.mvp.presenter.RxBasePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * @author liyong
 * @date 2020/8/20
 * @des
 */
public abstract class RxRefreshFragmentActivity<P extends RxBasePresenter> extends RxFragmentActivity<P> {


    protected SmartRefreshLayout smartRefreshLayout;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_refresh_base;
    }


    @Override
    protected void initRootView() {
        super.initRootView();
        smartRefreshLayout = ViewUtil.findViewById(this, R.id.smartRefreshLayout);
    }


    @Override
    protected void setUpView() {
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
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
    }

    /**
     * 设置下拉刷新和上拉加载监听
     */
    private void setOnRefreshLoadMoreListener() {
        smartRefreshLayout.setOnRefreshListener(refreshLayout ->
                pullDownRefresh()
        );

        smartRefreshLayout.setOnLoadMoreListener(refreshLayout ->
                pullUpLoadMore()
        );
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
        onLoadMoreComplete(delayTime, false);
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
        ViewUtil.setGone(exceptionLayout);
    }

    @Override
    protected void showExceptionView() {
        super.showExceptionView();
        ViewUtil.setGone(smartRefreshLayout, mContent);
    }

    @Override
    public void dismissLoading() {
        super.dismissLoading();
        onRefreshComplete(200);
        onLoadMoreComplete(200);
    }
}

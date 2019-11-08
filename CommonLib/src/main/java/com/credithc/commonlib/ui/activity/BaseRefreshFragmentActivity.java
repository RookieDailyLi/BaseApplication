package com.credithc.commonlib.ui.activity;

import android.os.Handler;
import android.support.annotation.CallSuper;

import com.credithc.commonlib.R;
import com.credithc.commonlib.mvp.p.RxBasePresenter;
import com.credithc.commonlib.util.ViewUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * @author liyong
 * @date 2019/11/6
 * @des 自带刷新Activity，需要刷新和分页的继成该类
 */
public abstract class BaseRefreshFragmentActivity<P extends RxBasePresenter> extends BaseFragmentActivity<P> {


    protected SmartRefreshLayout smartRefreshLayout;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_refresh_base;
    }


    @Override
    protected void initRootView() {
        super.initRootView();
        smartRefreshLayout = ViewUtil.findViewById(this, R.id.smartRefreshLayout);
        smartRefreshLayout.setEnableAutoLoadMore(false);
    }

    @Override
    protected void setUpRootView() {
        super.setUpRootView();
        //设置刷新相关
        setEnableRefresh(true);
        setEnableLoadMore(false);
        setDefaultRefreshHeaderFooter();
        setOnRefreshLoadMoreListener();
        enableAutoLoadMore();
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
     * /是否启用列表惯性滑动到底部时自动加载更多
     */
    protected void enableAutoLoadMore() {
        smartRefreshLayout.setEnableAutoLoadMore(false);
    }

    /**
     * 刷新
     */
    protected void pullDownRefresh() {
    }

    /**
     * 更多
     */
    protected void pullUpLoadMore() {
    }

    /**
     * 刷新完成
     */
    public void onRefreshComplete(int delayTime) {
        if (smartRefreshLayout.getState().isOpening) {

            smartRefreshLayout.finishRefresh(delayTime);
        }
    }

    /**
     * 更多完成
     */
    public void onLoadMoreComplete(int delayTime) {
        if (smartRefreshLayout.getState().isOpening) {

            smartRefreshLayout.finishLoadMore(delayTime);
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

    public void setNoMoreData(boolean isHaveMoreData) {
        smartRefreshLayout.finishLoadMore(300, true, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (smartRefreshLayout != null) {
                    smartRefreshLayout.setNoMoreData(isHaveMoreData);
                }
            }
        }, 800L);
    }
}

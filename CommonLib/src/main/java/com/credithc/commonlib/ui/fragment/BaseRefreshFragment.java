package com.credithc.commonlib.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.credithc.commonlib.R;
import com.credithc.commonlib.mvp.p.RxBasePresenter;
import com.credithc.commonlib.mvp.v.BaseView;
import com.credithc.commonlib.util.ViewUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * @author liyong
 * @date 2019/11/8
 * @des 自带刷新Fragment，需要刷新和分页的继成该类
 */

public abstract class BaseRefreshFragment<P extends RxBasePresenter> extends BaseFragment<P> implements BaseView {
    protected SmartRefreshLayout smartRefreshLayout;

    protected int getFragmentLayoutId() {
        return R.layout.fragment_bases;
    }

    @Override
    protected void initRootView(View rootView) {
        rootContentView = ViewUtil.findFLById(rootView, R.id.fl_root);
        View refreshLayout = ViewUtil.inflate(getFragmentLayoutId());
        rootContentView.addView(refreshLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mContent = ViewUtil.findViewById(refreshLayout, R.id.base_fragment_content);
        smartRefreshLayout = ViewUtil.findViewById(refreshLayout, R.id.smartRefreshLayout);
    }

    @Override
    protected void setUpRootView(View rootView) {
        ViewGroup viewGroup = (ViewGroup) mContent;
        viewGroup.removeAllViews();
        LayoutInflater.from(mActivity).inflate(layoutContentId(), viewGroup);
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
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(mActivity));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(mActivity));
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

    protected void pullDownRefresh() {
        smartRefreshLayout.setNoMoreData(false);
    }

    public void onRefreshComplete(int delayTime) {
        if (smartRefreshLayout.getState().isOpening) {

            smartRefreshLayout.finishRefresh(delayTime);
        }
    }

    public void onLoadMoreComplete(int delayTime) {
        if (smartRefreshLayout.getState().isOpening) {

            smartRefreshLayout.finishLoadMore(delayTime);
        }
    }

    protected void pullUpLoadMore() {
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
        onRefreshComplete(200);
        onLoadMoreComplete(200);
    }

    public void setNoMoreData(boolean state) {
        smartRefreshLayout.setNoMoreData(state);
        smartRefreshLayout.finishLoadMore(300, true, state);
    }

}

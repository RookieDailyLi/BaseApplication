package com.credithc.baseapp.ui.main.fragment;

import android.view.View;

import com.credithc.baseapp.R;
import com.credithc.mvp.presenter.BasePresenter;
import com.credithc.mvp.view.fragment.RxRefreshFragment;

/**
 * @author liyong
 * @date 2020/9/1 18:10
 * @description
 */
public class ProjectFragment extends RxRefreshFragment {
    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int layoutContentId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadData() {

    }
}

package com.credithc.baseapp.example.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.credithc.baseapp.bean.HomeBannerListBean;
import com.credithc.baseapp.example.contract.ExampleContract;
import com.credithc.baseapp.example.model.ExampleModel;
import com.credithc.baseapp.example.presenter.ExamplePresenter;
import com.credithc.mvp.ui.fragment.RxRefreshFragment;
import com.credithc.netlib.bean.ResultModel;

/**
 * @author liyong
 * @date 2019/11/11 11:28
 * @description
 */
public class ExampleApiFragment extends RxRefreshFragment<ExamplePresenter> implements ExampleContract.View {


    @Override
    public ExamplePresenter createPresenter() {
        return new ExamplePresenter(new ExampleModel(),this);
    }

    @Override
    protected int layoutContentId() {
        return 0;
    }

    @Override
    protected void initBundle(@NonNull Bundle bundle) {
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showBanner(ResultModel<HomeBannerListBean> resultModel) {

    }
}

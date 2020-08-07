package com.credithc.baseapp.example.fragment;

import android.view.View;

import com.credithc.baseapp.R;
import com.credithc.baseapp.bean.HomeBannerListBean;
import com.credithc.baseapp.example.contract.ExampleContract;
import com.credithc.baseapp.example.model.ExampleModel;
import com.credithc.baseapp.example.presenter.ExamplePresenter;
import com.credithc.commonlib.ui.fragment.BaseRefreshFragment;
import com.credithc.netlib.bean.ResultModel;

/**
 * @author liyong
 * @date 2019/11/11 11:28
 * @description
 */
public class ExampleApiFragment extends BaseRefreshFragment<ExamplePresenter> implements ExampleContract.View {

    @Override
    protected int layoutContentId() {
        return R.layout.fragment_example;
    }

    @Override
    protected void setUpView(View contentView) {

    }

    @Override
    protected ExamplePresenter createPresenter() {
        return new ExamplePresenter(new ExampleModel(),this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void showBanner(ResultModel<HomeBannerListBean> resultModel) {

    }
}

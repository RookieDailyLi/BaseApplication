package com.credithc.baseapp.example.activity;

import android.view.View;

import com.credithc.baseapp.R;
import com.credithc.baseapp.bean.resp.HomeBannerListBean;
import com.credithc.baseapp.example.contract.ExampleContract;
import com.credithc.baseapp.example.model.ExampleModel;
import com.credithc.baseapp.example.presenter.ExamplePresenter;
import com.credithc.commonlib.util.ToastUtil;
import com.credithc.mvp.ui.activity.RxRefreshFragmentActivity;
import com.credithc.netlib.bean.ResultModel;

/**
 * @author liyong
 * @date 2019/11/6 16:41
 * @description
 */
public class ExampleApiActivity extends RxRefreshFragmentActivity<ExamplePresenter> implements ExampleContract.View {


    @Override
    protected int layoutContentId() {
        return R.layout.activity_example;
    }

    @Override
    protected ExamplePresenter createPresenter() {
        return new ExamplePresenter(new ExampleModel(), this);
    }

    @Override
    protected void setUpView() {
        findViewById(R.id.button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.testApi();
                    }
                });

    }


    @Override
    protected void loadData() {
    }


    @Override
    public void testApiResponse(ResultModel<HomeBannerListBean> resultModel) {
        ToastUtil.showToast("code: " + resultModel.getCode() + " msg: " + resultModel.getMessage());
    }
}

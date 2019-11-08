package com.credithc.baseapp.example;

import android.widget.Button;

import com.credithc.baseapp.R;
import com.credithc.baseapp.bean.HomeBannerListBean;
import com.credithc.baseapp.example.contract.ExampleContract;
import com.credithc.baseapp.example.model.ExampleModel;
import com.credithc.baseapp.example.presenter.ExamplePresenter;
import com.credithc.commonlib.ui.activity.BaseRefreshFragmentActivity;
import com.credithc.commonlib.util.ToastUtil;
import com.credithc.netlib.bean.ResultModel;

/**
 * @author liyong
 * @date 2019/11/6 16:41
 * @description
 */
public class ExampleApiActivity extends BaseRefreshFragmentActivity<ExamplePresenter> implements ExampleContract.View {


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
        Button button = findViewById(R.id.button);

    }


    @Override
    protected void loadData() {
        presenter.reqBanner();
    }


    @Override
    public void showBanner(ResultModel<HomeBannerListBean> resultModel) {
        ToastUtil.showToast("code: " + resultModel.getCode() + " msg: " + resultModel.getMessage());
    }
}

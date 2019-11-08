package com.credithc.baseapp.example.presenter;

import com.credithc.baseapp.bean.HomeBannerListBean;
import com.credithc.baseapp.example.contract.ExampleContract;
import com.credithc.commonlib.mvp.p.RxBasePresenter;
import com.credithc.netlib.bean.ResultModel;
import com.credithc.netlib.callback.ResponseCallBack;

/**
 * @author liyong
 * @date 2019/11/6 16:39
 * @description
 */
public class ExamplePresenter extends RxBasePresenter<ExampleContract.Model, ExampleContract.View> implements ExampleContract.Presenter {
    public ExamplePresenter(ExampleContract.Model model, ExampleContract.View mView) {
        super(model, mView);
    }

    @Override
    public void reqBanner() {
        model.reqBanner()
                .compose(observable_io_main())
                .as(bindAutoDispose())
                .subscribe(new ResponseCallBack<HomeBannerListBean>() {
                    @Override
                    public void onRequestStart() {
                        showLoading();
                    }

                    @Override
                    public void onResponseSuccess(ResultModel<HomeBannerListBean> resultModel) {
                        if (isViewAttached()) {
                            mView.showBanner(resultModel);
                        }
                    }

                    @Override
                    public void onRequestFail(ResultModel resultModel) {
                        showLoadFailure();
                    }

                    @Override
                    public void onNetFail(ResultModel resultModel) {
                        showNetException();
                    }

                    @Override
                    public void onRequestFinish() {
                        loadComplete();
                    }
                });
    }
}

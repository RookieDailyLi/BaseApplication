package com.credithc.baseapp.example.presenter;

import com.credithc.baseapp.bean.resp.HomeBannerListBean;
import com.credithc.baseapp.example.contract.ExampleContract;
import com.credithc.baseapp.net.callback.DefaultResponseCallBack;
import com.credithc.mvp.presenter.RxBasePresenter;
import com.credithc.net.bean.ResultModel;

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
    public void testApi() {
        model.testApiReq()
                .compose(observable_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new DefaultResponseCallBack<HomeBannerListBean>(mView) {
                    @Override
                    public void onResponseSuccess(ResultModel<HomeBannerListBean> resultModel) {
                        if(isViewAttached()){
                            mView.testApiResponse(resultModel);
                        }
                    }

                    @Override
                    public void onRequestFail(ResultModel resultModel) {
                        if(isViewAttached()){
                            mView.showLoadFailure();
                        }
                    }

                    @Override
                    public void onNetFail(ResultModel resultModel) {
                        if(isViewAttached()){
                            mView.showNetException();
                        }
                    }
                });
    }
}

package com.credithc.baseapp.example.contract;

import com.credithc.baseapp.bean.HomeBannerListBean;
import com.credithc.commonlib.mvp.m.BaseModel;
import com.credithc.commonlib.mvp.v.BaseView;
import com.credithc.netlib.bean.ResultModel;

import io.reactivex.Observable;

/**
 * @author liyong
 * @date 2019/11/6 16:43
 * @description
 */
public interface ExampleContract {
    interface Model extends BaseModel {
        Observable<ResultModel<HomeBannerListBean>> reqBanner();
    }

    interface View extends BaseView {
        void showBanner(ResultModel<HomeBannerListBean> resultModel);
    }

    interface Presenter {
        void reqBanner();
    }
}

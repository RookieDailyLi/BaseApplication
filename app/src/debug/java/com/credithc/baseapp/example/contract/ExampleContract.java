package com.credithc.baseapp.example.contract;

import com.credithc.baseapp.bean.resp.HomeBannerListBean;
import com.credithc.mvp.model.BaseModel;
import com.credithc.mvp.view.RxBaseView;
import com.credithc.net.bean.ResultModel;

import io.reactivex.Observable;

/**
 * @author liyong
 * @date 2019/11/6 16:43
 * @description
 */
public interface ExampleContract {
    interface Model extends BaseModel {
        Observable<ResultModel<HomeBannerListBean>> testApiReq();
    }

    interface View extends RxBaseView {
        void testApiResponse(ResultModel<HomeBannerListBean> resultModel);
    }

    interface Presenter {
        void testApi();
    }
}

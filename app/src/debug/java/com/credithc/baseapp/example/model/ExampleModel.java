package com.credithc.baseapp.example.model;

import com.credithc.baseapp.bean.HomeBannerListBean;
import com.credithc.baseapp.example.contract.ExampleContract;
import com.credithc.baseapp.net.RetrofitClient;
import com.credithc.netlib.bean.ResultModel;

import io.reactivex.Observable;

/**
 * @author liyong
 * @date 2019/11/6 16:39
 * @description
 */
public class ExampleModel implements ExampleContract.Model {
    @Override
    public Observable<ResultModel<HomeBannerListBean>> reqBanner() {
        //如果接口有请求参数，在这里组装请求参数
        return RetrofitClient.createService().reqBanner();
    }
}
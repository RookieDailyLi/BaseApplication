package com.credithc.baseapp.net;

import com.credithc.baseapp.bean.resp.HomeBannerListBean;
import com.credithc.netlib.bean.ResultModel;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * @author liyong
 * @date 2019/10/18
 * @des
 */
public interface RetrofitService {

    @POST("sysBanner/getReleaseBanners")
    Observable<ResultModel<HomeBannerListBean>> reqBanner();
}

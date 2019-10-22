package com.credithc.baseapp.net;

import com.credithc.baseapp.bean.ARequest;
import com.credithc.baseapp.bean.BannerBean;
import com.credithc.baseapp.bean.UserBean;
import com.credithc.netlib.bean.ResultBean;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author liyong
 * @date 2019/10/18
 * @des
 */
public interface RetrofitService {

    @POST("sysBanner/getReleaseBanners")
    Observable<BannerBean> reqBanner(@Body ARequest request);
}

package com.credithc.baseapp;

import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;

import com.credithc.baseapp.bean.HomeBannerListBean;
import com.credithc.baseapp.net.RetrofitClient;
import com.credithc.commonlib.listener.OnFastClickListener;
import com.credithc.commonlib.util.DisplayUtil;
import com.credithc.commonlib.util.LogUtil;
import com.credithc.netlib.bean.ResultModel;
import com.credithc.netlib.callback.ResponseCallBack;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.e("Screen height = %d", DisplayUtil.getScreenHeight());
        findViewById(R.id.button).setOnClickListener(new OnFastClickListener() {
            @Override
            public void noFastClick() {
                test6();
            }
        });
    }

    private void test6() {
        RetrofitClient.createService()
                .reqBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseCallBack<HomeBannerListBean>() {
                    @Override
                    public void onRequestStart() {
                        LogUtil.e("onRequestStart,is main thread ? %s", Looper.getMainLooper().getThread() == Thread.currentThread() ? "yes" : "no");
                    }

                    @Override
                    public void onRequestFinish() {
                    }

                    @Override
                    public void onResponseSuccess(ResultModel<HomeBannerListBean> resultModel) {
                        LogUtil.e("onNext, code = %s,msg = %s", resultModel.getCode(), resultModel.getMessage());
                    }

                    @Override
                    public void onRequestFail(ResultModel resultModel) {
                    }

                    @Override
                    public void onNetFail(ResultModel resultModel) {
                    }
                });
    }
}

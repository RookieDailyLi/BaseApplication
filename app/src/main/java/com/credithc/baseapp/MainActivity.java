package com.credithc.baseapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.credithc.baseapp.bean.HomeBannerListBean;
import com.credithc.baseapp.net.RetrofitClient;
import com.credithc.commonlib.listener.OnFastClickListener;
import com.credithc.commonlib.util.LogUtil;
import com.credithc.netlib.bean.ResultModel;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                .subscribe(new Observer<ResultModel<HomeBannerListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResultModel<HomeBannerListBean> resultModel) {
                        LogUtil.e("onNext, code = %s,msg = %s", resultModel.getCode(), resultModel.getMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("onError,exception = %s", e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

package com.credithc.mvp.presenter;

import com.credithc.mvp.model.BaseModel;
import com.credithc.mvp.view.RxBaseView;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public class RxBasePresenter<M extends BaseModel, V extends RxBaseView> extends BasePresenter<M, V> {

    public RxBasePresenter(M model, V mView) {
        super(model, mView);
    }


    /**
     * Observable线程切换
     */
    protected <T> ObservableTransformer<T, T> observable_io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    /**
     * Flowable线程切换
     */
    protected <T> FlowableTransformer<T, T> flowable_io_main() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}

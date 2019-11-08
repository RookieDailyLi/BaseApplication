package com.credithc.commonlib.mvp.p;

import com.credithc.commonlib.mvp.m.BaseModel;
import com.credithc.commonlib.mvp.v.BaseView;
import com.credithc.commonlib.scheduler.RxScheduler;
import com.uber.autodispose.AutoDisposeConverter;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public class RxBasePresenter<M extends BaseModel, V extends BaseView> extends BasePresenter<M, V> {

    public RxBasePresenter(M model, V mView) {
        super(model, mView);
    }

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    protected <T> ObservableTransformer<T, T> observable_io_main() {
        return RxScheduler.Obs_io_main();
    }

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return FlowableTransformer
     */
    protected <T> FlowableTransformer<T, T> flowable_io_main() {
        return RxScheduler.Flo_io_main();
    }


    protected <T> AutoDisposeConverter<T> bindAutoDispose() {
        return mView.bindAutoDispose();
    }
}

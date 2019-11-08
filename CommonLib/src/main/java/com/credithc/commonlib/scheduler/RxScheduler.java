package com.credithc.commonlib.scheduler;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * @author liyong
 * @date 2019/11/4
 * @des 组合线程调度
 */
public class RxScheduler {
    private static ObservableTransformer SchedulersObservableTransformer;
    private static FlowableTransformer SchedulersFlowableTransformer;

    /**
     * 统一线程处理
     *
     * @param <T> 指定的泛型类型
     * @return FlowableTransformer
     */
    @SuppressWarnings("unchecked")
    public static <T> FlowableTransformer<T, T> Flo_io_main() {
        if (SchedulersFlowableTransformer == null) {
            SchedulersFlowableTransformer = new FlowableTransformer<T, T>() {
                @Override
                public Publisher<T> apply(@NonNull Flowable<T> upstream) {
                    return upstream.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                }
            };
        }
        return (FlowableTransformer<T, T>) SchedulersFlowableTransformer;
    }

    /**
     * 统一线程处理
     * 优化线程处理 不再单独创建了
     *
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> Obs_io_main() {
        if (SchedulersObservableTransformer == null) {
            SchedulersObservableTransformer = new ObservableTransformer() {
                @Override
                public ObservableSource apply(Observable upstream) {
                    return upstream.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread());
                }
            };
        }

        return (ObservableTransformer<T, T>) SchedulersObservableTransformer;
    }
}

package com.credithc.mvp.view.fragment;

import androidx.lifecycle.Lifecycle;

import com.credithc.mvp.presenter.RxBasePresenter;
import com.credithc.mvp.view.RxBaseView;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * @author liyong
 * @date 2020/8/27
 * @des
 */

public abstract class RxFragment<P extends RxBasePresenter> extends BaseFragment<P> implements RxBaseView {

    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T>
     * @return
     */
    @Override
    public final <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY));
    }


}

package com.credithc.mvp.ui.activity;

import android.arch.lifecycle.Lifecycle;

import com.credithc.mvp.presenter.RxBasePresenter;
import com.credithc.mvp.view.RxBaseView;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * @author liyong
 * @date 2020/8/20
 * @des
 */
public abstract class RxFragmentActivity<P extends RxBasePresenter> extends BaseFragmentActivity<P> implements RxBaseView {


    @Override
    protected void addExceptionLayout() {
        super.addExceptionLayout();
        exceptionLayout.showAsDropDown(mContentRoot, errorTopMargin);
    }

    /**
     * 绑定生命周期 防止MVP内存泄漏
     */
    @Override
    public final <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY));
    }
}

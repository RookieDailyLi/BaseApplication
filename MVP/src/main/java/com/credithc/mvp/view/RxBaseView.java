package com.credithc.mvp.view;

import com.uber.autodispose.AutoDisposeConverter;

/**
 * @author liyong
 * @date 2020/8/19
 * @des
 */
public interface RxBaseView extends BaseView {


    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();


}
package com.credithc.commonlib.mvp.v;

import com.credithc.commonlib.widget.excpetion.base.ILoadCovers;
import com.credithc.commonlib.widget.excpetion.base.ILoadEmpty;
import com.credithc.commonlib.widget.excpetion.base.ILoadFailure;
import com.credithc.commonlib.widget.excpetion.base.ILoading;
import com.credithc.commonlib.widget.excpetion.base.INetException;
import com.credithc.commonlib.widget.excpetion.base.IToast;
import com.uber.autodispose.AutoDisposeConverter;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */

public interface BaseView extends ILoadEmpty, INetException, ILoadFailure, ILoadCovers, ILoading, IToast {

    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();


    /**
     * 加载完成隐藏失败页面
     */
    void loadComplete();

    void showLoadCovers();
}

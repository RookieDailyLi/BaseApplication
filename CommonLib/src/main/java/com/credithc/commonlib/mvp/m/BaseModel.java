package com.credithc.commonlib.mvp.m;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

/**
*@author liyong
*@date 2019/11/4
*@des
*/
public interface BaseModel {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    default void destroy() {

    }

}

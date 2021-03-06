package com.credithc.common.widget;

import android.app.Activity;

import com.credithc.common.util.ActivityUtil;


/**
 * Created by lwj on 2018/8/21.
 * lwjfork@gmail.com
 */

public abstract class WeakActivityHandler<T extends Activity> extends WeakHandler<T> {
    
    
    public WeakActivityHandler(T target) {
        super(target);
    }
    
    
    @Override
    protected T isNeedHandle() {
        T weakObj = super.isNeedHandle();
        if(weakObj != null && ActivityUtil.isRunning(weakObj)) {
            return weakObj;
        }
        return null;
    }
}

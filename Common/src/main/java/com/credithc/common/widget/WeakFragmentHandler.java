package com.credithc.common.widget;


import com.credithc.common.util.FragmentUtil;

import androidx.fragment.app.Fragment;

/**
*@author liyong
*@date 2019/8/23
*@des
*/
public abstract class WeakFragmentHandler<T extends Fragment> extends WeakHandler<T> {


    public WeakFragmentHandler(T target) {
        super(target);
    }


    @Override
    protected T isNeedHandle() {
        T weakObj = super.isNeedHandle();
        if (weakObj != null && FragmentUtil.isRunning(weakObj)) {
            return weakObj;
        }
        return null;
    }
}

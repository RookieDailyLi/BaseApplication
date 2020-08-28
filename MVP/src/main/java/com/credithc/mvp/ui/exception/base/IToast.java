package com.credithc.mvp.ui.exception.base;

import android.content.Context;
import android.widget.Toast;

import com.credithc.commonlib.GlobalContext;
import com.credithc.commonlib.util.ResUtil;
import com.credithc.commonlib.util.ToastUtil;

/**
*@author liyong
*@date 2019/11/4
*@des
*/
public interface IToast {

    default void showToast(Context mContext, String text, int duration) {
        ToastUtil.showToast(mContext, text, duration);
    }


    default void showToast(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    default void showToast(String text, int duration) {
        showToast(GlobalContext.getContext(), text, duration);
    }

    default void showToast(int resId, int duration) {
        showToast(GlobalContext.getContext(), ResUtil.getString(resId), duration);
    }

    default void showToast(int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }


    /**
     * 显示无网络提示
     */
    default void showNetErrorToast(String errorMsg) {
        ToastUtil.showNetError(errorMsg);
    }


    default void showNetErrorToast() {
        ToastUtil.showNetError();
    }


}

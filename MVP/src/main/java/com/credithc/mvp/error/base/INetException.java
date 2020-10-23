package com.credithc.mvp.error.base;

import androidx.annotation.DrawableRes;

import com.credithc.common.util.ResUtil;
import com.credithc.mvp.R;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public interface INetException {


    default void showNetException() {
        showNetException(ResUtil.getString(R.string.net_exception_msg));
    }

    default void showNetException(String s) {
        showNetException(s, ResUtil.getString(R.string.click_refresh));
    }

    // 显示重试按钮
    default void showNetException(String tv, String btn) {
        showNetException(R.drawable.ic_net_exception, tv, btn);
    }

    // 显示重试按钮
    default void showNetException(@DrawableRes int netIcon) {
        showNetException(netIcon, ResUtil.getString(R.string.net_exception_msg), ResUtil.getString(R.string.click_refresh));
    }

    // 显示重试按钮
    default void showNetException(@DrawableRes int netIcon, String tv) {
        showNetException(netIcon, tv, ResUtil.getString(R.string.click_refresh));
    }


    void showNetException(@DrawableRes int netIcon, String tv, String btn);

}

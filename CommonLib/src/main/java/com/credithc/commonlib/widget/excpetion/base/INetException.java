package com.credithc.commonlib.widget.excpetion.base;

import android.support.annotation.DrawableRes;

import com.credithc.commonlib.R;
import com.credithc.commonlib.util.ResUtil;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public interface INetException {


    default void showNetException() {
        showNetException(ResUtil.getString(R.string.net_resp_prompt));
    }

    default void showNetException(String s) {
        showNetException(s, ResUtil.getString(R.string.net_resp_btn_refresh));
    }

    // 显示重试按钮
    default void showNetException(String tv, String btn) {
        showNetException(R.drawable.ic_net_exception, tv, btn);
    }

    // 显示重试按钮
    default void showNetException(@DrawableRes int netIcon) {
        showNetException(netIcon, ResUtil.getString(R.string.net_resp_prompt), ResUtil.getString(R.string.net_resp_btn_refresh));
    }

    // 显示重试按钮
    default void showNetException(@DrawableRes int netIcon, String tv) {
        showNetException(netIcon, tv, ResUtil.getString(R.string.net_resp_btn_refresh));
    }


    void showNetException(@DrawableRes int netIcon, String tv, String btn);

}

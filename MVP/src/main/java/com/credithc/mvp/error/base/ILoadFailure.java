package com.credithc.mvp.error.base;

import androidx.annotation.DrawableRes;

import com.credithc.common.util.ResUtil;
import com.credithc.mvp.R;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public interface ILoadFailure {

    default void showLoadFailure() {
        showLoadFailure(ResUtil.getString(R.string.server_exception_msg));
    }

    default void showLoadFailure(String s) {
        showLoadFailure(s, ResUtil.getString(R.string.click_refresh));
    }

    // 显示重试按钮
    default void showLoadFailure(String tv, String btn) {
        showLoadFailure(R.drawable.ic_failure, tv, btn);
    }

    // 显示重试按钮
    default void showLoadFailure(@DrawableRes int errorIcon) {
        showLoadFailure(errorIcon, ResUtil.getString(R.string.server_exception_msg), ResUtil.getString(R.string.click_refresh));
    }

    // 显示重试按钮
    default void showLoadFailure(@DrawableRes int errorIcon, String tv) {
        showLoadFailure(errorIcon, tv, ResUtil.getString(R.string.click_refresh));
    }


    void showLoadFailure(@DrawableRes int errorIcon, String tv, String btn);


}

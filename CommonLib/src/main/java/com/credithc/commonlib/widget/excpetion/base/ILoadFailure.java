package com.credithc.commonlib.widget.excpetion.base;

import android.support.annotation.DrawableRes;

import com.credithc.commonlib.R;
import com.credithc.commonlib.util.ResUtil;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public interface ILoadFailure {

    default void showLoadFailure() {
        showLoadFailure(ResUtil.getString(R.string.error_resp_prompt));
    }

    default void showLoadFailure(String s) {
        showLoadFailure(s, ResUtil.getString(R.string.error_resp_btn_refresh));
    }

    // 显示重试按钮
    default void showLoadFailure(String tv, String btn) {
        showLoadFailure(R.drawable.ic_failure, tv, btn);
    }

    // 显示重试按钮
    default void showLoadFailure(@DrawableRes int errorIcon) {
        showLoadFailure(errorIcon, ResUtil.getString(R.string.error_resp_prompt), ResUtil.getString(R.string.error_resp_btn_refresh));
    }

    // 显示重试按钮
    default void showLoadFailure(@DrawableRes int errorIcon, String tv) {
        showLoadFailure(errorIcon, tv, ResUtil.getString(R.string.error_resp_btn_refresh));
    }


    void showLoadFailure(@DrawableRes int errorIcon, String tv, String btn);


}

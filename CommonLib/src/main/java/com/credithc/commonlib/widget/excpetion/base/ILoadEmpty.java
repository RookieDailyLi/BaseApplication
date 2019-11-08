package com.credithc.commonlib.widget.excpetion.base;

import android.support.annotation.DrawableRes;

import com.credithc.commonlib.R;
import com.credithc.commonlib.util.ResUtil;


/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public interface ILoadEmpty {


    /**
     * showEmpty 方法用于请求的数据为空的状态
     */
    // 不显示重试按钮
    default void showEmpty() {
        showEmpty(ResUtil.getString(R.string.empty_data_prompt));
    }

    // 不显示重试按钮
    default void showEmpty(String tv) {
        showEmpty(R.drawable.ic_empty, tv, "");
    }

    // 不显示重试按钮
    default void showEmpty(@DrawableRes int errorIcon) {
        showEmpty(errorIcon, ResUtil.getString(R.string.empty_data_prompt), "");
    }

    // 不显示重试按钮
    default void showEmpty(@DrawableRes int errorIcon, String tv) {
        showEmpty(errorIcon, tv, "");
    }

    // 显示重试按钮
    default void showEmpty(String tv, String btn) {
        showEmpty(R.drawable.ic_empty, tv, btn);
    }

    void showEmpty(@DrawableRes int errorIcon, String tv, String btn);


}

package com.credithc.mvp.error.base;

import android.graphics.Color;
import androidx.annotation.ColorInt;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public interface ILoadCovers {


    // 添加遮罩
    default void showLoadCovers() {
        showLoadCovers(Color.WHITE);
    }

    void showLoadCovers(@ColorInt int color);


}


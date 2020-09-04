package com.credithc.commonlib.util;

import androidx.annotation.Nullable;

/**
 * Created by lwj on 2018/10/31.
 * lwjfork@gmail.com
 */
public final class ExceptionUtil {

    public static <T> T checkNotNUll(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }


    public static void illegalArgument(String message) {
        throw new IllegalArgumentException(message);
    }


    public static void printStackTrace(@Nullable Throwable throwable) {
        if (throwable != null) {
            throwable.printStackTrace();
            LogUtil.e(throwable.getMessage());
        }
    }

}

package com.credithc.common.util;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import android.widget.TextView;

/**
 * Created:2018/6/19
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public final class DrawableUtil {


    public static Drawable getBoundDrawable(@DrawableRes int id) {
        return getBoundDrawable(ResUtil.getDrawable(id));
    }

    public static Drawable getBoundDrawable(Drawable sourceDrawable) {
        if (sourceDrawable == null) {
            return null;
        }
        sourceDrawable.setBounds(0, 0, sourceDrawable.getMinimumWidth(), sourceDrawable.getMinimumHeight());
        return sourceDrawable;
    }

    public static <T extends TextView> void setCompoundDrawables(T view, @Nullable Drawable leftRes, @Nullable Drawable topRes, @Nullable Drawable rightRes, @Nullable Drawable bottomRes) {
        view.setCompoundDrawables(getBoundDrawable(leftRes),
                getBoundDrawable(topRes),
                getBoundDrawable(rightRes),
                getBoundDrawable(bottomRes));
    }


    public static <T extends TextView> void setCompoundDrawables(T view, int leftRes, int topRes, int rightRes, int bottomRes) {
        view.setCompoundDrawables(getBoundDrawable(leftRes),
                getBoundDrawable(topRes),
                getBoundDrawable(rightRes),
                getBoundDrawable(bottomRes));
    }


    public static <T extends TextView> void setLeftDrawable(T view, @DrawableRes int id) {
        view.setCompoundDrawables(DrawableUtil.getBoundDrawable(id), null, null, null);
    }

    public static <T extends TextView> void setTopDrawable(T view, @DrawableRes int id) {
        view.setCompoundDrawables(null, DrawableUtil.getBoundDrawable(id), null, null);
    }

    public static <T extends TextView> void setRightDrawable(T view, @DrawableRes int id) {
        view.setCompoundDrawables(null, null, DrawableUtil.getBoundDrawable(id), null);
    }

    public static <T extends TextView> void setBottomDrawable(T view, @DrawableRes int id) {
        view.setCompoundDrawables(null, null, null, DrawableUtil.getBoundDrawable(id));
    }

    public static <T extends TextView> void setLeftDrawable(T view, Drawable drawable) {
        view.setCompoundDrawables(DrawableUtil.getBoundDrawable(drawable), null, null, null);
    }

    public static <T extends TextView> void setTopDrawable(T view, Drawable drawable) {
        view.setCompoundDrawables(null, DrawableUtil.getBoundDrawable(drawable), null, null);
    }

    public static <T extends TextView> void setRightDrawable(T view, Drawable drawable) {
        view.setCompoundDrawables(null, null, DrawableUtil.getBoundDrawable(drawable), null);
    }

    public static <T extends TextView> void setBottomDrawable(T view, Drawable drawable) {
        view.setCompoundDrawables(null, null, null, DrawableUtil.getBoundDrawable(drawable));
    }


    public static Drawable getDrawable(@ColorInt int color, int width, int height) {
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(color);
        shape.setSize(width, height);
        return shape;
    }


    public static Drawable getDrawableByHeight(@ColorInt int color, int height) {
        return getDrawable(color, 0, height);
    }

    public static Drawable getDrawableByWidth(@ColorInt int color, int width) {
        return getDrawable(color, width, 0);
    }

    public static Drawable getDrawable(int width, int height) {
        return getDrawable(Color.TRANSPARENT, width, height);
    }


    public static Drawable getDrawableByHeight(int height) {
        return getDrawable(0, height);
    }

    public static Drawable getDrawableByWidth(int width) {
        return getDrawable(width, 0);
    }

    /**
     * 清除图片
     *
     * @param view
     * @param <T>
     */
    public static <T extends TextView> void clearCompoundDrawables(T view) {
        setCompoundDrawables(view, null, null, null, null);
    }


    public static DrawableBuilder getStateDrawableBuilder() {
        return new DrawableBuilder();
    }

    public static class DrawableBuilder {
        StateListDrawable stateListDrawable = new StateListDrawable();

        private DrawableBuilder() {

        }

        public DrawableBuilder addDrawableState(Drawable drawable, int... stateSet) {
            stateListDrawable.addState(stateSet, drawable);
            return this;
        }


        public DrawableBuilder addDrawableResState(@DrawableRes int id, int... stateSet) {
            stateListDrawable.addState(stateSet, ResUtil.getDrawable(id));
            return this;
        }


        public StateListDrawable buildDrawable() {
            return stateListDrawable;
        }
    }


}

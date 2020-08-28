package com.credithc.commonlib.widget;

import android.graphics.drawable.ShapeDrawable;

/**
 * Created by lwj on 2019/4/18.
 * lwjfork@gmail.com
 */
public class LinearDrawable extends ShapeDrawable {
    private int mHeight;

    public LinearDrawable(int cursorColor, int cursorWidth, int cursorHeight) {
        mHeight = cursorHeight;
        setDither(false);
        getPaint().setColor(cursorColor);
        setIntrinsicWidth(cursorWidth);
    }

    public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.setBounds(paramInt1, paramInt2, paramInt3, this.mHeight + paramInt2);
    }

}
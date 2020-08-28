package com.credithc.commonlib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by lwj on 2018/12/26.
 * lwjfork@gmail.com
 */
public class MeasureChildListView extends MeasuredListView {

    public MeasureChildListView(Context context) {
        super(context);
    }

    public MeasureChildListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureChildListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}

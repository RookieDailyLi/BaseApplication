package com.credithc.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.credithc.common.R;


public class LimitScrollView extends ScrollView {

    private int maxHeight = -1;

    public LimitScrollView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LimitScrollView);
        maxHeight = array.getDimensionPixelSize(R.styleable.LimitScrollView_maxHeight, -1);
        array.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (maxHeight > 0) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMaxHeight(int max) {
        if (max == maxHeight) {
            return;
        }
        this.maxHeight = max;
        requestLayout();
    }
}

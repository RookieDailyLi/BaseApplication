package com.credithc.commonlib.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author liyong
 * @date 2018/11/12
 * @des 解决ScrollView嵌套GridView冲突
 */
public class MeasuredGridView extends GridView {
    public MeasuredGridView(Context context) {
        super(context);
    }

    public MeasuredGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasuredGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public MeasuredGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}

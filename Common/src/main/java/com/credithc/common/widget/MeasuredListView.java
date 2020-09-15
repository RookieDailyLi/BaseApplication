package com.credithc.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author liyong
 * @date 2018/11/12
 * @des 解决ScrollView嵌套ListView冲突
 */
public class MeasuredListView extends ListView {
    public MeasuredListView(Context context) {
        super(context);
    }
    
    public MeasuredListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public MeasuredListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @TargetApi(21)
    public MeasuredListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST));
    }
}

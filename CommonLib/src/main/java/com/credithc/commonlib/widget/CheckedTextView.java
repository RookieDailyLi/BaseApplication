package com.credithc.commonlib.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lwj on 2019/4/2.
 * lwjfork@gmail.com
 */
@SuppressWarnings("AppCompatCustomView")
public class CheckedTextView extends android.widget.CheckedTextView {


    public CheckedTextView(Context context) {
        this(context, null);
    }

    public CheckedTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public CheckedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        if (onCheckedListener != null) {
            onCheckedListener.onChecked(checked);
        }
    }

    public void setOnCheckedListener(CheckView.OnCheckedListener onCheckedListener) {
        this.onCheckedListener = onCheckedListener;
    }

    CheckView.OnCheckedListener onCheckedListener;

    public interface OnCheckedListener {
        void onChecked(boolean isChecked);
    }
}

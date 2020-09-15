package com.credithc.common.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by lwj on 2019/1/8.
 * lwjfork@gmail.com
 */
@SuppressWarnings("all")
public class CheckView extends ImageView {
    public CheckView(Context context) {
        this(context, null);
    }

    public CheckView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public CheckView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSelected()) {
                    setSelected(false);
                } else {
                    setSelected(true);
                }
            }
        });
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (onCheckedListener != null) {
            onCheckedListener.onChecked(selected);
        }
    }


    public void setOnCheckedListener(OnCheckedListener onCheckedListener) {
        this.onCheckedListener = onCheckedListener;
    }

    OnCheckedListener onCheckedListener;

    public interface OnCheckedListener {
        void onChecked(boolean isChecked);
    }
}

package com.credithc.mvp.error.common;

import android.annotation.TargetApi;
import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.credithc.mvp.error.base.ILoadCoversView;


/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public class LoadCoversView extends View implements ILoadCoversView {


    public LoadCoversView(Context context) {
        super(context);
    }

    public LoadCoversView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadCoversView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public LoadCoversView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void showLoadCovers(int color) {
        setBackgroundColor(color);
    }

    @Override
    public View getCoversView() {
        return this;
    }

    @Override
    public void hideCovers() {
        setVisibility(GONE);
    }
}


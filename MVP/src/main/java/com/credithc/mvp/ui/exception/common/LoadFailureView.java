package com.credithc.mvp.ui.exception.common;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.credithc.mvp.R;
import com.credithc.mvp.ui.exception.base.ILoadFailureView;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public class LoadFailureView extends LinearLayout implements ILoadFailureView {

    Context context;

    private TextView tv_failure_txt;
    private TextView btn_failure_again;
    private ImageView iv_failure;

    public LoadFailureView(Context context) {
        this(context, null);
    }

    public LoadFailureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadFailureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        inflate(context, getLayoutId(), this);
        initView();
    }

    protected int getLayoutId() {
        return R.layout.layout_failure;
    }

    protected void initView() {
        tv_failure_txt = findViewById(R.id.tv_failure_txt);
        iv_failure = findViewById(R.id.iv_failure);
        btn_failure_again = findViewById(R.id.btn_failure_again);
    }


    @Override
    public View getLoadFailureView() {
        return this;
    }


    @Override
    public void showLoadFailure(int errorIcon, String tv, String btn) {
        showLoadFailureView();
        tv_failure_txt.setText(tv);
        btn_failure_again.setText(btn);
        iv_failure.setImageResource(errorIcon);
        if (TextUtils.isEmpty(btn)) {
            btn_failure_again.setVisibility(GONE);
        }
    }

    @Override
    public void setLoadFailureOnClickListener(OnClickListener onClickListener) {
        btn_failure_again.setOnClickListener(onClickListener);
    }

    @Override
    public void hideLoadFailureView() {
        setVisibility(GONE);
    }

    @Override
    public void showLoadFailureView() {
        setVisibility(VISIBLE);
    }
}

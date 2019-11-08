package com.credithc.commonlib.widget.excpetion.common;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.credithc.commonlib.R;
import com.credithc.commonlib.util.StringUtil;
import com.credithc.commonlib.util.ViewUtil;
import com.credithc.commonlib.widget.excpetion.base.INetExceptionView;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public class LoadNetExceptionView extends LinearLayout implements INetExceptionView {
    Context context;

    private TextView tv_net_exception_txt;
    private TextView btn_net_exception_again;
    private ImageView iv_error_icon;

    public LoadNetExceptionView(Context context) {
        this(context, null);
    }

    public LoadNetExceptionView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadNetExceptionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        return R.layout.layout_net_exception;
    }

    protected void initView() {
        tv_net_exception_txt = ViewUtil.findTVById(this, R.id.tv_net_exception_txt);
        btn_net_exception_again = ViewUtil.findTVById(this, R.id.btn_net_exception_again);
        iv_error_icon = ViewUtil.findIVById(this, R.id.iv_error_icon);
    }


    @Override
    public View getNetExceptionView() {
        return this;
    }


    @Override
    public void showNetException(int netIcon, String tv, String btn) {
        showNetExceptionView();
        tv_net_exception_txt.setText(tv);
        btn_net_exception_again.setText(btn);
        iv_error_icon.setImageResource(netIcon);
        if (StringUtil.isEmpty(btn)) {
            btn_net_exception_again.setVisibility(GONE);
        }
    }

    @Override
    public void setNetExceptionOnClickListener(OnClickListener onClickListener) {
        btn_net_exception_again.setOnClickListener(onClickListener);
    }

    @Override
    public void hideNetExceptionView() {
        setVisibility(GONE);
    }

    @Override
    public void showNetExceptionView() {
        setVisibility(VISIBLE);
    }
}

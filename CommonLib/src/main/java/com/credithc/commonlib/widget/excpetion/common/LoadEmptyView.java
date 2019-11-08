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
import com.credithc.commonlib.widget.excpetion.base.ILoadEmptyView;


/**
*@author liyong
*@date 2019/11/4
*@des
*/
public class LoadEmptyView extends LinearLayout implements ILoadEmptyView {


    Context context;
    private TextView btn_empty_again;
    private TextView tv_empty_txt;
    private ImageView iv_empty;

    public LoadEmptyView(Context context) {
        this(context, null);
    }

    public LoadEmptyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public LoadEmptyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    protected void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        inflate(context, getLayoutId(), this);
        initView();
    }

    protected int getLayoutId() {
        return R.layout.layout_empty;
    }

    protected void initView() {
        btn_empty_again = ViewUtil.findTVById(this, R.id.btn_empty_again);
        tv_empty_txt = ViewUtil.findTVById(this, R.id.tv_empty_txt);
        iv_empty = ViewUtil.findIVById(this, R.id.iv_empty);
    }


    @Override
    public View getEmptyView() {
        return this;
    }


    @Override
    public void setLoadEmptyOnClickListener(OnClickListener onClickListener) {
        btn_empty_again.setOnClickListener(onClickListener);
    }

    @Override
    public void showEmpty(int errorIcon, String tv, String btn) {
        iv_empty.setImageResource(errorIcon);
        tv_empty_txt.setText(tv);
        btn_empty_again.setText(btn);
        if (StringUtil.isEmpty(btn)) {
            btn_empty_again.setVisibility(GONE);
        }
    }

    @Override
    public void hideEmptyView() {
        setVisibility(GONE);
    }

    @Override
    public void showEmptyView() {
        setVisibility(VISIBLE);
    }
}

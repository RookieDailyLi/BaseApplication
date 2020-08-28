package com.credithc.commonlib.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.credithc.commonlib.R;
import com.credithc.commonlib.util.StringUtil;


/**
 * 加载进度框，默认点击返回按钮退出页面
 */
public class LoadingDialog extends Dialog {

    private Activity context;

    private TextView txt;
    private String title;

    private ProgressView pv_circle;

    private boolean cancelable = true;

    public LoadingDialog(Activity context) {
        super(context);
        this.context = context;
    }

    public LoadingDialog(Activity context, int theme, String title) {
        super(context, theme);
        this.context = context;
        this.title = title;
    }

    public LoadingDialog(Activity context, int theme, int title) {
        super(context, theme);
        this.context = context;
        this.title = (String) context.getText(title);
    }

    public LoadingDialog(Activity context, int theme) {
        super(context, theme);
        this.context = context;
    }

    public LoadingDialog(Activity context, int theme, boolean cancelable) {
        super(context, theme);
        this.context = context;
        this.cancelable = cancelable;
    }

    public LoadingDialog(Activity context, String title, int theme, boolean cancelable) {
        super(context, theme);
        this.context = context;
        this.title = title;
        this.cancelable = cancelable;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        txt = (TextView) findViewById(R.id.txt);
        if (!TextUtils.isEmpty(title)) {
            txt.setText(title);
            txt.setVisibility(View.VISIBLE);
        } else {
            txt.setVisibility(View.GONE);
        }
        setCanceledOnTouchOutside(false);
        pv_circle = (ProgressView) findViewById(R.id.img);

        if (cancelable) {
            setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                        if (cancelable) {
                            dismiss();
                        }
                    }
                    return false;
                }
            });
        }
        setCancelable(cancelable);
    }


    @Override
    public void show() {
        super.show();
        // 每次显示进度条都重新设置进度动画
        pv_circle.setAnimation();

    }


    public void setTitle(String title) {
        this.title = title;
        if (txt == null) {
            return;
        }
        if (StringUtil.isNotEmpty(title)) {
            txt.setText(title);
            txt.setVisibility(View.VISIBLE);
        } else {
            txt.setVisibility(View.GONE);
        }
    }
}

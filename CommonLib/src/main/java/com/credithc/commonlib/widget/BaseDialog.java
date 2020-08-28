package com.credithc.commonlib.widget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * 描述: dialog基类
 */

public abstract class BaseDialog extends DialogFragment {
    protected View rootView;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().setOnCancelListener(null);
        getDialog().setOnDismissListener(null);
        getDialog().setOnShowListener(null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDialogStyle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 设置圆角需要
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        getDialog().setCanceledOnTouchOutside(getCanceledOnTouchOutside());
        setCancelable(getCancelable());

        rootView = inflater.inflate(getLayoutId(), container, false);
        initView();
        setListener();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        // 重置宽高
        Window window = getDialog().getWindow();
        fixLayoutParams(window);
    }

    protected void fixLayoutParams(Window window) {
        if (window != null) {
            window.setLayout(getWidth(), getHeight());
        }
    }

    /**
     * 显示Dialog
     */
    public void show(FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment pre = fragmentManager.findFragmentByTag(getFragmentTag());
        if (pre != null) {
            transaction.remove(pre);
        }

        // onSaveInstance后commit无法提交
        try {
            show(transaction, getFragmentTag());
        } catch (IllegalStateException e) {
            //  容错处理,不做操作
        }
    }

    /**
     * 显示Dialog
     */
    public void show(@NonNull FragmentActivity activity) {
        show(activity.getSupportFragmentManager());
    }

    /**
     * 避免莫名其妙的空指针
     * onSaveInstance后commit无法提交
     */
    @Override
    public void dismiss() {
        if (getFragmentManager() == null) {
            return;
        }
        dismissAllowingStateLoss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void setListener();

    protected String getFragmentTag() {
        return getClass().getSimpleName();
    }

    /**
     * override
     * 配置对话框风格
     */
    protected void setDialogStyle() {
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    /**
     * override
     * 配置点击对话框外部是否消失
     */
    protected boolean getCanceledOnTouchOutside() {
        return false;
    }

    /**
     * override
     * 配置返回键是否可以关闭对话框
     */
    protected boolean getCancelable() {
        return true;
    }

    /**
     * override
     * 配置宽度
     */
    protected int getWidth() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    /**
     * override
     * 配置高度
     */
    protected int getHeight() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

}

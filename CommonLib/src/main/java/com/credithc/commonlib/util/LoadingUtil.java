package com.credithc.commonlib.util;

import android.app.Activity;
import android.content.Context;
import androidx.fragment.app.Fragment;

import com.credithc.commonlib.R;
import com.credithc.commonlib.widget.LoadingDialog;

import java.util.HashMap;


/**
 * @author liyong
 * @date 2019/11/6
 * @des
 */
public class LoadingUtil {

    public static HashMap<Context, LoadingDialog> loadingDialogs = new HashMap<>();


    private static void push(Context context, LoadingDialog loadingDialog) {
        loadingDialogs.put(context, loadingDialog);
    }

    public static void pop(Context context) {
        loadingDialogs.remove(context);
    }

    public static void pop(Fragment fragment) {
        pop(fragment.getActivity());
    }


    public static void showLoading(Fragment fragment, String title, boolean cancelable, int style) {
        Activity context = fragment.getActivity();
        showLoading(context, title, cancelable, style);
    }

    public static void showLoading(Fragment fragment, String title) {
        Activity context = fragment.getActivity();
        showLoading(context, title);
    }

    public static void showLoading(Fragment fragment, String title, boolean cancelable) {
        Activity context = fragment.getActivity();
        showLoading(context, title, cancelable, R.style.confirm_dialog);
    }

    public static void showLoading(Context context, String title) {
        showLoading(context, title, false, R.style.confirm_dialog);
    }

    /**
     * 显示进度条
     *
     * @param title      提示语
     * @param cancelable 返回键是否可点击
     * @param style      样式 全透明-R.style.dialog，半透明-R.style.dialog2
     */
    public static void showLoading(Context activity, String title, boolean cancelable, int style) {
        if (!(activity instanceof Activity)) {
            throw new IllegalArgumentException("Context must be Activity");
        }
        // fix Activity 已经进入销毁阶段，弹窗显示导致内存泄漏问题
        if (!ActivityUtil.isRunning((Activity) activity)) {
            return;
        }

        LoadingDialog loadingDialog = loadingDialogs.get(activity);
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog((Activity) activity, title, style, cancelable);
            push(activity, loadingDialog);
        } else {
            loadingDialog.setCancelable(cancelable);
            loadingDialog.setTitle(title);
        }

        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }


    public static void showLoading(Context context) {
        showLoading(context, false);
    }


    public static void showLoading(Context context, boolean cancelable) {
        showLoading(context, ResUtil.getString(R.string.dialog_title), cancelable, R.style.confirm_dialog);
    }

    public static void showLoading(Context context, String title, boolean cancelable) {
        showLoading(context, title, cancelable, R.style.confirm_dialog);
    }


    public static void showLoading(Fragment fragment) {
        showLoading(fragment, false);
    }


    public static void showLoading(Fragment fragment, boolean cancelable) {
        showLoading(fragment, ResUtil.getString(R.string.dialog_title), cancelable, R.style.confirm_dialog);
    }

    public static void dismissLoading(Context context) {
        LoadingDialog loadingDialog = loadingDialogs.get(context);
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public static void dismissLoading(Fragment fragment) {
        dismissLoading(fragment.getActivity());
    }
}

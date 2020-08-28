package com.credithc.commonlib.widget;

import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.credithc.commonlib.R;


/**
 * Created by lwj on 16/5/5.
 * Des:   通用的底部弹出的 dialog
 */
public abstract class BaseBottomUpDialog extends BaseDialog {



    @Override
    protected void setDialogStyle() {
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.edit_bottom_up_dialog);
    }




    @Override
    protected void fixLayoutParams(Window window) {
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = getWidth();
        lp.height = getHeight();
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);
    }

    @Override
    protected int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }
}

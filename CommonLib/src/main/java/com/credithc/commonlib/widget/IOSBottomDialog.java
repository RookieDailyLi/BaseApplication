package com.credithc.commonlib.widget;

import androidx.annotation.ColorInt;
import androidx.annotation.Px;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.credithc.commonlib.R;
import com.credithc.commonlib.util.ViewUtil;


/**
 * Created by lwj on 2019/3/20.
 * lwjfork@gmail.com
 */
public class IOSBottomDialog extends BaseBottomUpDialog {


    private TextView tv_cancel;
    private LinearLayout ll_container;

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_ios_bottom;
    }

    @Override
    protected void initView() {
        tv_cancel = ViewUtil.findViewById(rootView, R.id.tv_cancel);
        ll_container = ViewUtil.findViewById(rootView, R.id.ll_container);
    }

    protected void setCancelStyle(String txt, @Px int textSize, @ColorInt int textColor) {
        tv_cancel.setText(txt);
        tv_cancel.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tv_cancel.setTextColor(textColor);
    }


    @Override
    protected void setListener() {

    }

}

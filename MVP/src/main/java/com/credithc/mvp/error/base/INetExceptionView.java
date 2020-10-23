package com.credithc.mvp.error.base;

import android.view.View;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public interface INetExceptionView extends INetException {

    View getNetExceptionView();


    // 重试按钮点击事件
    void setNetExceptionOnClickListener(View.OnClickListener onClickListener);


    void hideNetExceptionView();

    void showNetExceptionView();
}

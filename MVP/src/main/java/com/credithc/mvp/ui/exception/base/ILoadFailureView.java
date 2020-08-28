package com.credithc.mvp.ui.exception.base;

import android.view.View;

/**
*@author liyong
*@date 2019/11/4
*@des
*/
public interface ILoadFailureView extends ILoadFailure {


    View getLoadFailureView();


    // 重试按钮点击事件
    void setLoadFailureOnClickListener(View.OnClickListener onClickListener);


    void hideLoadFailureView();

    void showLoadFailureView();

}

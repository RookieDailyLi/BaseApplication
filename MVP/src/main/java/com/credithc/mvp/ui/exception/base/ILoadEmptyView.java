package com.credithc.mvp.ui.exception.base;

import android.view.View;

/**
*@author liyong
*@date 2019/11/4
*@des
*/
public interface ILoadEmptyView extends ILoadEmpty {

    View getEmptyView();

    // 重试按钮点击事件
    void setLoadEmptyOnClickListener(View.OnClickListener onClickListener);


    void hideEmptyView();

    void showEmptyView();
}

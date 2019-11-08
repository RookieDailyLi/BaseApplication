package com.credithc.commonlib.widget.excpetion.base;

import android.view.ViewGroup;

/**
*@author liyong
*@date 2019/11/4
*@des
*/
public interface IShow {

    // 覆盖显示并在 anchor 的下部显示，当topMargin为0时则直接覆盖显示
    void showAsDropDown(ViewGroup parent, int topMargin);

    void show(ViewGroup parent);


}

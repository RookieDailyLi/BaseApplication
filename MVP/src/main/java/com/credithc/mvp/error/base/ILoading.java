package com.credithc.mvp.error.base;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public interface ILoading {

    // 显示loading
    default void showLoading() {
        showLoading(false);
    }

    // 隐藏 loading
    void dismissLoading();


    default void showLoading(boolean cancelAble) {
        showLoading("加载中", cancelAble);
    }


    void showLoading(String tips, boolean cancelAble);

    default void showLoading(String tips) {
        showLoading(tips, false);
    }

    default void showLoadingNoTips() {
        showLoadingNoTips(false);
    }

    default void showLoadingNoTips(boolean cancelAble) {
        showLoading("", cancelAble);
    }

    default void loadComplete() {

    }

}

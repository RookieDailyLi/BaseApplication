package com.credithc.baseapp.route.executor;

import android.content.Context;

import java.util.Map;

/**
*@author liyong
*@date 2019/12/13
*@des
*/
public interface IAppRouter {


    default void navigation(String url, Map<String, String> params) {
        // Nothing to do
    }

    /**
     * Navigation to the route with path in postcard.
     *
     * @param context Activity and so on.
     */
    default void navigation(Context context, String url, Map<String, String> params) {
        // Nothing to do
    }


}

package com.credithc.baseapp.route;

import com.credithc.common.util.StringUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

import androidx.annotation.IntDef;

/**
 * @author liyong
 * @date 2019/8/30
 * @des
 */
public class RouterConstants {

    //Router name
    public static final String ROUTER_NAME_INVALID_KEY = "nonTransition"; // 无效url 不处理
    public static final String ROUTER_NAME_WEBVIEW_KEY = "WebView";//通用webview页
    public static final String ROUTER_NAME_MAIN_KEY = "MAIN";// 主页面，自定义的主页面
    public static final String ROUTER_NAME_HOME_PAGE_KEY = "homePage";// 首页
    public static final String ROUTER_NAME_PROJECT_PAGE_KEY = "projectPage";// 分类
    public static final String ROUTER_NAME_MINE_PAGE_KEY = "minePage";// 我的
    public static final String ROUTER_NAME_CALLSERVICE_KEY = "callService";//  打电话

    /**
     * !!!如果是不同module,group一定不能相同
     */
    //Router path
    public static final String ROUTER_PATH_INVALID = ""; // 无效url 不处理
    public static final String ROUTER_PATH_MAIN = "/app/MainActivity";// 主页面，自定义的主页面
    public static final String ROUTER_PATH_WEBVIEW = "/web/WebViewActivity";//通用webview页

    private static final HashMap<String, String> pageMap = new HashMap<String, String>() {{
        put(ROUTER_NAME_INVALID_KEY, ROUTER_PATH_INVALID); // 无效url 不处理
        put(ROUTER_NAME_WEBVIEW_KEY, ROUTER_PATH_WEBVIEW);//通用webview页
        put(ROUTER_NAME_MAIN_KEY, ROUTER_PATH_MAIN);
    }};


    public static String getRealPath(String originPath) {
        String realPath = pageMap.get(originPath);
        if (StringUtil.isEmpty(realPath)) { // 未找到，
            realPath = originPath;// 原样返回
        }
        return realPath;
    }

    public static final int MAIN_HOME = 0;
    public static final int MAIN_PROJECT = 1;
    public static final int MAIN_MINE = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({MAIN_HOME, MAIN_PROJECT, MAIN_MINE})
    public @interface MainIndex {
    }

    /**
     * 拦截服务
     */
    public static class Service {
        // url 重定向
        public static final String PATH_REPLACE_SERVICE = "/replace/path";
        public static final String DEGRADE_SERVICE = "/degrade/service";
        public static final String SERVICE_JSON = "/service/json";
    }

}

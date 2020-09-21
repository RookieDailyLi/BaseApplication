package com.credithc.baseapp.route.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.credithc.baseapp.route.RouterConstants;
import com.credithc.baseapp.route.util.RouterHandler;


/**
 * Created by lwj on 2018/11/2.
 * lwjfork@gmail.com
 * 全局降级策略，当无 patch 匹配时
 */
@Route(path = RouterConstants.Service.DEGRADE_SERVICE)
public class DegradeServiceImpl implements DegradeService {
    
    @Override
    public void onLost(Context context, Postcard postcard) {
        RouterHandler.onLost(context, postcard);
    }
    
    @Override
    public void init(Context context) {
        RouterHandler.debug("降级策略初始化");
    }
}

package com.credithc.baseapp.helper;

import android.app.Application;

import com.credithc.baseapp.AppBlockCanaryContext;
import com.github.moduth.blockcanary.BlockCanary;


/**
 * Created by lwj on 2018/10/26.
 * lwjfork@gmail.com
 */
public class BlockCanaryHelper {

    public static <T extends Application> void install(T application) {

        BlockCanary.install(application, new AppBlockCanaryContext()).start();
    }

}

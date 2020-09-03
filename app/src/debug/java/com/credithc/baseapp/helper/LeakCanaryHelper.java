package com.credithc.baseapp.helper;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by lwj on 2018/10/26.
 * lwjfork@gmail.com
 */
public class LeakCanaryHelper {
    
    private static RefWatcher refWatcher;
    
    public static <T extends Application> void install(T application) {
        if(LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(application);
        
    }
    
    public static void watchReferences(Object object) {
        refWatcher.watch(object);
    }
}

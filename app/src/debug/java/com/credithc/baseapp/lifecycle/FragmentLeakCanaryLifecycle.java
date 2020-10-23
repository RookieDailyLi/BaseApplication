package com.credithc.baseapp.lifecycle;

import androidx.fragment.app.Fragment;

import com.credithc.baseapp.helper.LeakCanaryHelper;
import com.credithc.mvp.lifecycle.IFragLifeSubscriber;


/**
 * Created by lwj on 2018/10/31.
 * lwjfork@gmail.com
 */
public class FragmentLeakCanaryLifecycle implements IFragLifeSubscriber {

    @Override
    public void onDestroy(Fragment fragment) {
        LeakCanaryHelper.watchReferences(fragment);
    }

}

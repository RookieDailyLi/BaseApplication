package com.credithc.common.image.download;

import android.graphics.Bitmap;

/**
 * Created by liuwenjie on 2017/9/20.
 * lwjfork@gmail.com
 */

public interface DownLoadListener {
    
    void onSuccess(Bitmap bitmap);
    
    void onFail(Throwable throwable);
    
    void onStart();
}

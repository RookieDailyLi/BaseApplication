package com.credithc.common.widget.fresco;

import android.app.ActivityManager;
import android.os.Build;
import android.util.Log;

import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.imagepipeline.cache.MemoryCacheParams;

/**
 * Created by liuwenjie on 2017/9/15.
 * lwjfork@gmail.com
 * <p>
 * DefaultBitmapMemoryCacheParamsSupplier  256 ／ 56
 * 5.0 以上手机内存不断扩大 设置为 56 将其降低大小
 *
 * @see MemoryCacheParams#maxCacheEntries
 * 1. 内存最大缓存空间
 * 2. 内存最多缓存多少张图片
 * 3. 内存缓存中准备清除但尚未清除的图片所占内存大小
 * 4. 内存缓存中准备清除但尚未清除的图片数量
 * 5. 缓存每张图片所占最大空间大小
 */

public class LollipopBitmapMemoryCacheSupplier implements Supplier<MemoryCacheParams> {
    
    
    public static final int MAX_CACHE_ENTRIES = 56; // 内存中最多缓存多少张
    public static final int MAX_CACHE_EVICTION_SIZE = 30 * ByteConstants.MB; // 内存缓存中准备清除但未清除的总图片空间大小--单位byte
    public static final int MAX_CACHE_EVICTION_ENTRIES = 30; // 内存缓存中准备清除但未清除的图片数量
    public static final int MAX_CACHE_ENTRY_SIZE = Integer.MAX_VALUE; // 每张图内存所占最大空间
    
    private ActivityManager activityManager;
    private boolean isLog;
    
    public LollipopBitmapMemoryCacheSupplier(ActivityManager activityManager, boolean isLog) {
        this.activityManager = activityManager;
        this.isLog = isLog;
    }
    
    @Override
    public MemoryCacheParams get() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new MemoryCacheParams(
                    getMaxCacheSize(),
                    MAX_CACHE_ENTRIES,
                    MAX_CACHE_EVICTION_SIZE,
                    MAX_CACHE_EVICTION_ENTRIES,
                    MAX_CACHE_ENTRY_SIZE);
        }
        else {
            return new MemoryCacheParams(
                    getMaxCacheSize(),
                    256,
                    Integer.MAX_VALUE,
                    Integer.MAX_VALUE,
                    Integer.MAX_VALUE);
        }
    }
    
    private int getMaxCacheSize() {
        final int maxMemory = Math.min(activityManager.getMemoryClass() * ByteConstants.MB, Integer.MAX_VALUE);
        int memoryM = 4;
        if(maxMemory < 32 * ByteConstants.MB) {
            memoryM = 4;
        }
        else if(maxMemory < 64 * ByteConstants.MB) {
            memoryM = 6;
        }
        else if(maxMemory < 128 * ByteConstants.MB) {
            memoryM = 18;
        }
        else {
            if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.HONEYCOMB) {
                memoryM = 8;
            }
            else {
                memoryM = 26;
            }
        }
        if(isLog) {
            Log.d("Fresco Cache Memory -->", memoryM + " MB ");
        }
        return memoryM * ByteConstants.MB;
    }
}

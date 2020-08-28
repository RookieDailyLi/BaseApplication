package com.credithc.commonlib.widget.fresco;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.request.ImageRequest;

import java.io.File;

import okhttp3.OkHttpClient;

/**
 * Created by liuwenjie on 2016/12/29.
 * lwjfork@gmail.com
 * fresco 默认配置
 */

public class FrescoConfig {
    public static String IMAGE_CACHE_DIR = "image";
    public static String IMAGE_SMALL_CACHE_DIR = "image_small";

    public static int MAX_DISK_SMALL_CACHE_SIZE = 10 * ByteConstants.MB;
    public static int MAX_DISK_SMALL_LOW_CACHE_SIZE = 5 * ByteConstants.MB;
    public static int MAX_DISK_SMALL_VERY_LOW_CACHE_SIZE = 2 * ByteConstants.MB;

    public static int MAX_DISK_CACHE_SIZE = 50 * ByteConstants.MB;
    public static int MAX_DISK_LOW_CACHE_SIZE = 25 * ByteConstants.MB;
    public static int MAX_DISK_VERY_LOW_CACHE_SIZE = 5 * ByteConstants.MB;


    static ImagePipelineConfig sImagePipelineConfig;

    public static ImagePipelineConfig getFrescoConfig(File baseDir, Context context, boolean isLog) {
        if (sImagePipelineConfig == null) {
            if (baseDir.exists() && !baseDir.isDirectory()) {
                throw new IllegalArgumentException("this cache dir must be directory ！");
            }
            sImagePipelineConfig = ImagePipelineConfig.newBuilder(context.getApplicationContext())
                    .setResizeAndRotateEnabledForNetwork(true)
                    .setDownsampleEnabled(true) //设置是否当这次请求是从网络中加载图片时，来对三级缓存中的编码图片重新改
                    .setBitmapsConfig(Bitmap.Config.RGB_565) //所加载图片的配置，默认为Bitmap.Config.ARGB_8888
                    .setMemoryTrimmableRegistry(getMemoryTrimmableRegistry())//设置内存监听
                    .setMainDiskCacheConfig(getMainCacheConfig(baseDir, context))//设置硬盘缓存
                    .setSmallImageDiskCacheConfig(getSmallCacheConfig(baseDir, context))//设置硬盘缓存
                    .setBitmapMemoryCacheParamsSupplier(new LollipopBitmapMemoryCacheSupplier((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE), isLog))
                    .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig()) // 设置渐进式展示
                    .build();
        }
        return sImagePipelineConfig;
    }

    public static ImagePipelineConfig getFrescoConfig(File baseDir, OkHttpClient okHttpClient, Context context, boolean isLog) {
        if (okHttpClient == null) {
            return getFrescoConfig(baseDir, context, isLog);
        }
        if (sImagePipelineConfig == null) {
            if (baseDir.exists() && !baseDir.isDirectory()) {
                throw new IllegalArgumentException("this cache dir must be directory ！");
            }
            sImagePipelineConfig = OkHttpImagePipelineConfigFactory.newBuilder(context, okHttpClient)
                    .setResizeAndRotateEnabledForNetwork(true)
                    .setDownsampleEnabled(true) //设置是否当这次请求是从网络中加载图片时，来对三级缓存中的编码图片重新改
                    .setBitmapsConfig(Bitmap.Config.RGB_565) //所加载图片的配置，默认为Bitmap.Config.ARGB_8888
                    .setMemoryTrimmableRegistry(getMemoryTrimmableRegistry())//设置内存监听
                    .setMainDiskCacheConfig(getMainCacheConfig(baseDir, context))//设置硬盘缓存
                    .setSmallImageDiskCacheConfig(getSmallCacheConfig(baseDir, context))//设置硬盘缓存
                    .setBitmapMemoryCacheParamsSupplier(new LollipopBitmapMemoryCacheSupplier((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE), isLog))
                    .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig()) // 设置渐进式展示
                    .build();
        }
        return sImagePipelineConfig;
    }


    public static ImagePipelineConfig getFrescoConfig(File baseDir, Context context) {

        return getFrescoConfig(baseDir, context, false);
    }

    /**
     * 硬盘缓存  主文件
     * 相对次文件来说是变动比较大
     *
     * @param baseDir
     * @param context
     * @return
     * @see ImageRequest.CacheChoice#DEFAULT
     */
    public static DiskCacheConfig getMainCacheConfig(File baseDir, Context context) {
        return DiskCacheConfig.newBuilder(context)
                .setBaseDirectoryName(IMAGE_CACHE_DIR)
                .setBaseDirectoryPath(baseDir)
                .setMaxCacheSize(MAX_DISK_CACHE_SIZE)
                .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_LOW_CACHE_SIZE)
                .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_VERY_LOW_CACHE_SIZE)
                .build();
    }

    /**
     * 硬盘缓存  次文件
     * 相对主文件来说是变动比较小，可以将一些不经常变化的文件放入其中
     *
     * @param baseDir
     * @param context
     * @return
     * @see ImageRequest.CacheChoice#SMALL
     */
    public static DiskCacheConfig getSmallCacheConfig(File baseDir, Context context) {

        return DiskCacheConfig.newBuilder(context)
                .setBaseDirectoryPath(baseDir)
                .setBaseDirectoryName(IMAGE_SMALL_CACHE_DIR)
                .setMaxCacheSize(MAX_DISK_SMALL_CACHE_SIZE)
                .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_SMALL_LOW_CACHE_SIZE)
                .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_SMALL_VERY_LOW_CACHE_SIZE)
                .build();
    }

    // 当内存紧张时采取的措施-清除内存缓存
    public static MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        MemoryTrimmableRegistry memoryTrimmableRegistry = NoOpMemoryTrimmableRegistry.getInstance();
        memoryTrimmableRegistry.registerMemoryTrimmable(new MemoryTrimmable() {
            @Override
            public void trim(MemoryTrimType trimType) {
                final double suggestedTrimRatio = trimType.getSuggestedTrimRatio();
                if (MemoryTrimType.OnCloseToDalvikHeapLimit.getSuggestedTrimRatio() == suggestedTrimRatio
                        || MemoryTrimType.OnSystemLowMemoryWhileAppInBackground.getSuggestedTrimRatio() == suggestedTrimRatio
                        || MemoryTrimType.OnSystemLowMemoryWhileAppInForeground.getSuggestedTrimRatio() == suggestedTrimRatio
                        ) {
                    Fresco.getImagePipeline().clearMemoryCaches();
                }
            }
        });
        return memoryTrimmableRegistry;
    }

    public static void setDiskCacheFileName(String fileName) {
        IMAGE_CACHE_DIR = fileName;
    }

    public static void setSmallDiskCacheFileName(String fileName) {
        IMAGE_SMALL_CACHE_DIR = fileName;
    }

}

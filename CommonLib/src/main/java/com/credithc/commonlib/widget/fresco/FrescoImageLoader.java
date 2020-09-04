package com.credithc.commonlib.widget.fresco;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.credithc.commonlib.R;
import com.credithc.commonlib.image.base.IBaseImageLoader;
import com.credithc.commonlib.image.base.IImageLoaderHelper;
import com.credithc.commonlib.image.base.ImageType;
import com.credithc.commonlib.image.base.LoadImageUrlConverter;
import com.credithc.commonlib.image.download.DownLoadListener;
import com.credithc.commonlib.image.util.ReSize;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.util.Map;

import okhttp3.OkHttpClient;


/**
 * Created by liuwenjie on 2016/12/30.
 * lwjfork@gmail.com
 */

public class FrescoImageLoader implements IBaseImageLoader, IImageLoaderHelper {


    public static int fadeDuration = 300;
    public static boolean isFadeAnim = true;
    public boolean isLog = false;

    public FrescoImageLoader(Context context, String cacheDir, boolean isLog) {
        this(context, null, cacheDir, isLog);
    }

    public FrescoImageLoader(Context context, OkHttpClient okHttpClient, String cacheDir, boolean isLog) {
        this.cacheDir = cacheDir;
        setConverter(new FrescoConvertLoadImageUrl());
        FrescoUtil.init(context, okHttpClient, new File(cacheDir), isLog);
        setLog(isLog);
    }

    LoadImageUrlConverter urlConverter;

    @Override
    public void setConverter(LoadImageUrlConverter loadImageUrlConverter) {
        this.urlConverter = loadImageUrlConverter;
    }

    @Override
    public void setLog(boolean isLog) {
        if (isLog) {
            FLog.setMinimumLoggingLevel(FLog.VERBOSE);
        }
        this.isLog = isLog;
    }

    public String cacheDir;

    public SimpleDraweeView checkImageView(ImageView imageView) {
        boolean isType = imageView instanceof SimpleDraweeView;
        if (!isType) {
            throw new IllegalArgumentException("If U want to use Fresco,this view must to be SimpleDraweeView");
        }
        return (SimpleDraweeView) imageView;
    }

    public String convertUrl(@ImageType int imageType, String image) {

        return urlConverter.convert(imageType, image);
    }

    private boolean isNeedLoad(ImageView imageView, String image) {
        String url = (String) imageView.getTag(R.id.load_image_tag_id);
        if (url == null) {
            imageView.setTag(R.id.load_image_tag_id, image);
            return true;
        }
        if (!url.equals(image)) {
            imageView.setTag(R.id.load_image_tag_id, image);
            return true;
        }
        return false;
    }

    private void setFadeDuration(GenericDraweeHierarchy hierarchy, boolean isAnim) {
        if (isFadeAnim && isAnim && fadeDuration > 0) {
            hierarchy.setFadeDuration(fadeDuration);
        } else {
            hierarchy.setFadeDuration(0);
        }
    }

    private ImageRequest setFadeDuration(GenericDraweeHierarchy hierarchy, boolean isAnim, String imageUrl, ReSize reSize, int iterations, int blurRadius) {
        ImageRequest request = FrescoUtil.buildImageRequest(imageUrl, reSize, iterations, blurRadius);
        boolean isInMemory = Fresco.getImagePipeline().isInBitmapMemoryCache(request);
        FLog.d("FrescoImageLoader", " HAVE memoryCache for " + imageUrl + "===" + isInMemory);
        boolean needFade = !isInMemory && isAnim;
        setFadeDuration(hierarchy, needFade);
        return request;
    }


    @Override
    public void loadImage(@NonNull Context context, @ImageType int imageType, ImageView imageView, String image, int defaultImg, int errorImg, ReSize reSize, boolean isAnim, int iterations, int blurRadius) {
        if (!isNeedLoad(imageView, image)) {
            return;
        }
        String resultUrl = convertUrl(imageType, image);
        SimpleDraweeView view = checkImageView(imageView);
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        if (hierarchy == null) {
            hierarchy = FrescoUtil.buildHierarchy(context, defaultImg, errorImg);
        } else {
            hierarchy.setFailureImage(errorImg);
            hierarchy.setPlaceholderImage(defaultImg);
        }
        ImageRequest request = setFadeDuration(hierarchy, isAnim, resultUrl, reSize, iterations, blurRadius);
        DraweeController controller = FrescoUtil.buildController(view, request);
        view.setHierarchy(hierarchy);
        view.setController(controller);
    }

    @Override
    public void loadImage(Fragment fragment, @ImageType int imageType, ImageView imageView, String image, int defaultImg, int errorImg, ReSize reSize, boolean isAnim, int iterations, int blurRadius) {
        if (!isNeedLoad(imageView, image)) {
            return;
        }
        String resultUrl = convertUrl(imageType, image);
        SimpleDraweeView view = checkImageView(imageView);
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        if (hierarchy == null) {
            hierarchy = FrescoUtil.buildHierarchy(fragment.getActivity(), defaultImg, errorImg);
        } else {
            hierarchy.setFailureImage(errorImg);
            hierarchy.setPlaceholderImage(defaultImg);
        }
        ImageRequest request = setFadeDuration(hierarchy, isAnim, resultUrl, reSize, iterations, blurRadius);
        DraweeController controller = FrescoUtil.buildController(view, request);
        view.setHierarchy(hierarchy);
        view.setController(controller);
    }

    @Override
    public void loadImageCircle(@NonNull Context context, @ImageType int imageType, ImageView imageView, String image, int defaultImg, int errorImg, ReSize reSize, boolean isAnim, int iterations, int blurRadius) {
        if (!isNeedLoad(imageView, image)) {
            return;
        }
        String resultUrl = convertUrl(imageType, image);
        SimpleDraweeView view = checkImageView(imageView);
        RoundingParams params = FrescoUtil.buildCircle();
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        if (hierarchy == null) {
            hierarchy = FrescoUtil.buildHierarchy(context, defaultImg, errorImg);
        } else {
            hierarchy.setFailureImage(errorImg);
            hierarchy.setPlaceholderImage(defaultImg);
        }
        ImageRequest request = setFadeDuration(hierarchy, isAnim, resultUrl, reSize, iterations, blurRadius);
        DraweeController controller = FrescoUtil.buildController(view, request);
        hierarchy.setRoundingParams(params);
        view.setHierarchy(hierarchy);
        view.setController(controller);
    }

    @Override
    public void loadImageCircle(Fragment fragment, @ImageType int imageType, ImageView imageView, String image, int defaultImg, int errorImg, ReSize reSize, boolean isAnim, int iterations, int blurRadius) {
        if (!isNeedLoad(imageView, image)) {
            return;
        }
        String resultUrl = convertUrl(imageType, image);
        SimpleDraweeView view = checkImageView(imageView);
        RoundingParams params = FrescoUtil.buildCircle();
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        if (hierarchy == null) {
            hierarchy = FrescoUtil.buildHierarchy(fragment.getActivity(), defaultImg, errorImg);
        } else {
            hierarchy.setFailureImage(errorImg);
            hierarchy.setPlaceholderImage(defaultImg);
        }
        ImageRequest request = setFadeDuration(hierarchy, isAnim, resultUrl, reSize, iterations, blurRadius);
        DraweeController controller = FrescoUtil.buildController(view, request);
        hierarchy.setRoundingParams(params);
        view.setHierarchy(hierarchy);
        view.setController(controller);
    }

    @Override
    public void loadRoundImage(@NonNull Context context, @ImageType int imageType, ImageView imageView, String image, int defaultImg, int errorImg, float tlRadius, float trRadius, float brRadius, float blRadius, ReSize reSize, boolean isAnim, int iterations, int blurRadius) {
        if (!isNeedLoad(imageView, image)) {
            return;
        }
        String resultUrl = convertUrl(imageType, image);
        SimpleDraweeView view = checkImageView(imageView);
        RoundingParams roundingParams = null;
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        if (hierarchy == null) {
            hierarchy = FrescoUtil.buildHierarchy(context, defaultImg, errorImg);
        } else {
            hierarchy.setFailureImage(errorImg);
            hierarchy.setPlaceholderImage(defaultImg);
            roundingParams = hierarchy.getRoundingParams();
        }
        if (roundingParams == null) {
            roundingParams = FrescoUtil.buildRoundParams(tlRadius, trRadius, brRadius, blRadius);
        } else {
            roundingParams.setCornersRadii(tlRadius, trRadius, brRadius, blRadius);
        }
        ImageRequest request = setFadeDuration(hierarchy, isAnim, resultUrl, reSize, iterations, blurRadius);
        DraweeController controller = FrescoUtil.buildController(view, request);
        hierarchy.setRoundingParams(roundingParams);
        view.setHierarchy(hierarchy);
        view.setController(controller);
    }

    @Override
    public void loadRoundImage(Fragment fragment, @ImageType int imageType, ImageView imageView, String image, int defaultImg, int errorImg, float tlRadius, float trRadius, float brRadius, float blRadius, ReSize reSize, boolean isAnim, int iterations, int blurRadius) {
        if (!isNeedLoad(imageView, image)) {
            return;
        }
        String resultUrl = convertUrl(imageType, image);
        SimpleDraweeView view = checkImageView(imageView);
        RoundingParams params = FrescoUtil.buildRoundParams(tlRadius, trRadius, blRadius, brRadius);
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        if (hierarchy == null) {
            hierarchy = FrescoUtil.buildHierarchy(fragment.getActivity(), defaultImg, errorImg);
        } else {
            hierarchy.setFailureImage(errorImg);
            hierarchy.setPlaceholderImage(defaultImg);
        }
        ImageRequest request = setFadeDuration(hierarchy, isAnim, resultUrl, reSize, iterations, blurRadius);
        DraweeController controller = FrescoUtil.buildController(view, request);
        hierarchy.setRoundingParams(params);
        view.setHierarchy(hierarchy);
        view.setController(controller);
    }

    @Override
    public void loadImageCircleWithBorder(@NonNull Context context, @ImageType int imageType, ImageView imageView, String image, int defaultImg, int errorImg, int borderWidth, int borderColor, ReSize reSize, boolean isAnim, int iterations, int blurRadius) {
        if (!isNeedLoad(imageView, image)) {
            return;
        }
        String resultUrl = convertUrl(imageType, image);
        SimpleDraweeView view = checkImageView(imageView);
        RoundingParams params = FrescoUtil.buildCircleWidthBorder(borderColor, borderWidth);
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        if (hierarchy == null) {
            hierarchy = FrescoUtil.buildHierarchy(context, defaultImg, errorImg);
        } else {
            hierarchy.setFailureImage(errorImg);
            hierarchy.setPlaceholderImage(defaultImg);
        }
        ImageRequest request = setFadeDuration(hierarchy, isAnim, resultUrl, reSize, iterations, blurRadius);
        DraweeController controller = FrescoUtil.buildController(view, request);
        hierarchy.setRoundingParams(params);
        view.setHierarchy(hierarchy);
        view.setController(controller);


    }

    @Override
    public void loadImageCircleWithBorder(Fragment fragment, @ImageType int imageType, ImageView imageView, String image, int defaultImg, int errorImg, int borderWidth, int borderColor, ReSize reSize, boolean isAnim, int iterations, int blurRadius) {
        if (!isNeedLoad(imageView, image)) {
            return;
        }
        String resultUrl = convertUrl(imageType, image);
        SimpleDraweeView view = checkImageView(imageView);
        RoundingParams params = FrescoUtil.buildCircleWidthBorder(borderColor, borderWidth);
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        if (hierarchy == null) {
            hierarchy = FrescoUtil.buildHierarchy(fragment.getActivity(), defaultImg, errorImg);
        } else {
            hierarchy.setFailureImage(errorImg);
            hierarchy.setPlaceholderImage(defaultImg);
        }
        ImageRequest request = setFadeDuration(hierarchy, isAnim, resultUrl, reSize, iterations, blurRadius);

        DraweeController controller = FrescoUtil.buildController(view, request);
        hierarchy.setRoundingParams(params);
        view.setHierarchy(hierarchy);
        view.setController(controller);
    }


    @Override
    public void loadGifImage(@NonNull Context context, @ImageType int imageType, ImageView imageView, String image, int defaultImg, int errorImg) {
        if (!isNeedLoad(imageView, image)) {
            return;
        }
        String resultUrl = convertUrl(imageType, image);
        SimpleDraweeView view = checkImageView(imageView);
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        if (hierarchy == null) {
            hierarchy = FrescoUtil.buildHierarchy(context, defaultImg, errorImg);
        } else {
            hierarchy.setFailureImage(errorImg);
            hierarchy.setPlaceholderImage(defaultImg);
        }
        ImageRequest request = FrescoUtil.buildImageRequest(resultUrl);
        DraweeController controller = FrescoUtil.buildController(view, request);
        view.setHierarchy(hierarchy);
        view.setController(controller);
    }

    @Override
    public void loadGifImage(Fragment fragment, @ImageType int imageType, ImageView imageView, String image, int defaultImg, int errorImg) {
        if (!isNeedLoad(imageView, image)) {
            return;
        }
        String resultUrl = convertUrl(imageType, image);
        SimpleDraweeView view = checkImageView(imageView);
        GenericDraweeHierarchy hierarchy = view.getHierarchy();
        if (hierarchy == null) {
            hierarchy = FrescoUtil.buildHierarchy(fragment.getContext(), defaultImg, errorImg);
        } else {
            hierarchy.setFailureImage(errorImg);
            hierarchy.setPlaceholderImage(defaultImg);
        }
        ImageRequest request = FrescoUtil.buildImageRequest(resultUrl);
        DraweeController controller = FrescoUtil.buildController(view, request);
        view.setHierarchy(hierarchy);
        view.setController(controller);
    }

    @Override
    public long getDiskCacheSize(Context context) {
        return getMainImageCacheSize() + getSmallImageCacheSize();
    }

    @Override
    public void clearMemoryCache(Context context) {
        Fresco.getImagePipeline().clearMemoryCaches();
    }

    @Override
    public void clearDiskCache(Context context) {
        Fresco.getImagePipeline().clearDiskCaches();
    }

    @Override
    public void clearCache(Context context) {
        clearMemoryCache(context);
        clearDiskCache(context);
    }

    @Override
    public String getCacheDir(Context context) {


        return cacheDir;
    }

    @Override
    public void pause(Context context) {
        Fresco.getImagePipeline().pause();
        if (isLog) {
            Log.d("fresco load Image---->", "PAUSE");
        }

    }

    @Override
    public void resume(Context context) {
        if (Fresco.getImagePipeline().isPaused()) {
            Fresco.getImagePipeline().resume();
            if (isLog) {
                Log.d("fresco load Image---->", "RESUME");
            }
        }
    }


    public long getMainImageCacheSize() {
        Fresco.getImagePipelineFactory().getMainFileCache().trimToMinimum();
        return Fresco.getImagePipelineFactory().getMainFileCache().getSize();
    }

    public long getSmallImageCacheSize() {
        Fresco.getImagePipelineFactory().getMainFileCache().trimToMinimum();
        return Fresco.getImagePipelineFactory().getSmallImageFileCache().getSize();
    }

    @Override
    public void onTrimMemory(int level) {
        FrescoUtil.trimMemory(level);
    }

    @Override
    public void onLowMemory() {
        FrescoUtil.onLowMemery();
    }

    @Override
    public void downLoad(String url, final DownLoadListener listener) {
        Uri uri = Uri.parse(url);
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setAutoRotateEnabled(true)
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .setProgressiveRenderingEnabled(true)//渐进渲染
                .build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        final DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, this);

        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            protected void onNewResultImpl(Bitmap bitmap) {
                if (listener != null) {
                    listener.onSuccess(bitmap);
                }
                dataSource.close();
            }

            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                if (listener != null) {
                    listener.onFail(dataSource.getFailureCause());
                }

            }
        }, UiThreadImmediateExecutorService.getInstance());
    }

    @Override
    public void downLoad(String url, final DownLoadListener listener, int width, int height) {
        Uri uri = Uri.parse(url);
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setAutoRotateEnabled(true)
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .setProgressiveRenderingEnabled(true)//渐进渲染
                .setResizeOptions(new ResizeOptions(width, height))
                .setRequestListener(new RequestListener() {
                    @Override
                    public void onRequestStart(ImageRequest request, Object callerContext, String requestId, boolean isPrefetch) {
                        if (listener != null) {
                            listener.onStart();
                        }
                    }

                    @Override
                    public void onRequestSuccess(ImageRequest request, String requestId, boolean isPrefetch) {

                    }

                    @Override
                    public void onRequestFailure(ImageRequest request, String requestId, Throwable throwable, boolean isPrefetch) {

                    }

                    @Override
                    public void onRequestCancellation(String requestId) {

                    }

                    @Override
                    public void onProducerStart(String requestId, String producerName) {

                    }

                    @Override
                    public void onProducerEvent(String requestId, String producerName, String eventName) {

                    }

                    @Override
                    public void onProducerFinishWithSuccess(String requestId, String producerName, Map<String, String> extraMap) {

                    }

                    @Override
                    public void onProducerFinishWithFailure(String requestId, String producerName, Throwable t, Map<String, String> extraMap) {

                    }

                    @Override
                    public void onProducerFinishWithCancellation(String requestId, String producerName, Map<String, String> extraMap) {

                    }

                    @Override
                    public void onUltimateProducerReached(String requestId, String producerName, boolean successful) {

                    }

                    @Override
                    public boolean requiresExtraMap(String requestId) {
                        return false;
                    }
                })
                .build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, this);


        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            protected void onNewResultImpl(Bitmap bitmap) {
                if (listener != null) {
                    listener.onSuccess(bitmap);
                }
            }

            @Override
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                if (listener != null) {
                    listener.onFail(dataSource.getFailureCause());
                }

            }
        }, UiThreadImmediateExecutorService.getInstance());
    }

    @Override
    public boolean isMemoryCache(String url, @ImageType int imageType) {
        return false;
    }

    @Override
    public boolean isDiskCache(String url, @ImageType int imageType) {
        return false;
    }
}

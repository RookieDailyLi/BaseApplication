package com.credithc.commonlib.widget.fresco;

import android.content.Context;
import android.net.Uri;

import com.credithc.commonlib.image.util.ReSize;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

import okhttp3.OkHttpClient;


/**
 * Created by liuwenjie on 2016/12/29.
 * lwjfork@gmail.com
 */

class FrescoUtil {


    public static void init(Context context, File cacheDir, boolean isLog) {
        init(context, null, cacheDir, isLog);
    }

    public static void init(Context context, OkHttpClient okHttpClient, File cacheDir, boolean isLog) {
        Fresco.initialize(context, FrescoConfig.getFrescoConfig(cacheDir, okHttpClient, context, isLog));
    }


    public static void init(Context context, OkHttpClient okHttpClient, File cacheDir) {
        init(context, okHttpClient, cacheDir, false);
    }


    public static void init(Context context, File cacheDir) {
        init(context, cacheDir, false);
    }


    public static RoundingParams buildRoundParams(float radius) {
        return buildRoundParams(radius, radius, radius, radius);
    }

    public static RoundingParams buildCircle() {
        return RoundingParams.asCircle();
    }

    public static RoundingParams buildCircleWidthBorder(int borderColor, int borderWidth) {
        RoundingParams params = RoundingParams.asCircle();
        params.setBorder(borderColor, borderWidth);
        return params;
    }

    public static RoundingParams buildRoundParams(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        return RoundingParams.fromCornersRadii(topLeft, topRight, bottomRight, bottomLeft);
    }

    public static ImageRequest buildImageRequest(String url) {
        return buildImageRequest(url, null, 0, 0);
    }


    public static ImageRequest buildImageRequest(String url, ReSize reSize, int iterations, int blurRadius) {
        Uri uri = Uri.parse(url);
        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(uri);
        builder.setAutoRotateEnabled(true);
        if (reSize != null) {
            ResizeOptions resizeOptions = new ResizeOptions(reSize.width, reSize.height);
            builder.setResizeOptions(resizeOptions);
        }
        if (iterations > 0 && blurRadius > 0) {
            builder.setPostprocessor(new IterativeBoxBlurPostProcessor(iterations, blurRadius));
        }

        return builder.build();
    }

    public static ImageRequest buildImageRequest(int resId, ImageRequest.CacheChoice cacheChoice) {
        if (cacheChoice == null) {
            cacheChoice = ImageRequest.CacheChoice.DEFAULT;
        }
        ImageRequest request = ImageRequestBuilder.newBuilderWithResourceId(resId)
                .setAutoRotateEnabled(true)
                .setCacheChoice(cacheChoice)
                .build();
        return request;
    }

    public static ImageRequest buildImageRequest(int resId) {
        return buildImageRequest(resId, null);
    }


    public static GenericDraweeHierarchy buildHierarchy(Context context, int loadingResId, int failResId) {
        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(context.getResources());
        builder.setPlaceholderImage(loadingResId);
        builder.setFailureImage(failResId);
        return builder.build();
    }


    public static DraweeController buildController(SimpleDraweeView imageView, String image) {

        return buildController(imageView, image, null);
    }

    public static DraweeController buildController(SimpleDraweeView imageView, String image, ReSize reSize) {
        ImageRequest request = buildImageRequest(image, reSize, 0, 0);
        return buildController(imageView, request);
    }

    public static DraweeController buildController(SimpleDraweeView imageView, ImageRequest request) {
        ControllerListeners controllerListeners = new ControllerListeners();
        controllerListeners.addControllerListener(new AutoSizeControllerListener(imageView));
        return Fresco.newDraweeControllerBuilder()
                .setOldController(imageView.getController())
                .setImageRequest(request)
                .setAutoPlayAnimations(true)
                .setControllerListener(controllerListeners)
                .build();
    }


    public static void trimMemory(int level) {

    }

    public static void onLowMemery() {

    }

}

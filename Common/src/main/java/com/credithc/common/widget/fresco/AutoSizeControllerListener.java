package com.credithc.common.widget.fresco;

import android.graphics.drawable.Animatable;
import android.view.ViewGroup;

import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

/**
 * Created by liuwenjie on 2017/10/20.
 * lwjfork@gmail.com
 */

public class AutoSizeControllerListener extends BaseControllerListener<ImageInfo> {
    
    SimpleDraweeView imageView;
    
    
    public AutoSizeControllerListener(SimpleDraweeView imageView) {
        this.imageView = imageView;
    }
    
    @Override
    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
        super.onFinalImageSet(id, imageInfo, animatable);
        
        if(imageView == null) {
            return;
        }
        try {
            final ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            
            // 宽高有具体值或者是 match_parent
            if(layoutParams.height != ViewGroup.LayoutParams.WRAP_CONTENT && layoutParams.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
                return;
            }
            float ratio = imageView.getAspectRatio();
            if(ratio > 0f) {
                return;
            }
            if(layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT
                    && (layoutParams.width == ViewGroup.LayoutParams.MATCH_PARENT || layoutParams.width > 0)) {
                // 通过宽度计算高度
                calculateHeightByWidth(imageInfo, layoutParams);
            }
            else if(
                    layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT
                            && (layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT || layoutParams.height > 0)
                    ) {
                // 通过高度计算宽度
                calculateWidthByHeight(imageInfo, layoutParams);
            }
            else { // 全部都为 wrap
                layoutParams.height = 200;
                // 通过高度计算宽度
                calculateWidthByHeight(imageInfo, layoutParams);
                
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void calculateHeightByWidth(ImageInfo imageInfo, ViewGroup.LayoutParams layoutParams) {
        float ratio = imageView.getAspectRatio();
        if (ratio > 0f) {
            return;
        }
        int realHeight = imageInfo.getHeight();
        int realWidth = imageInfo.getWidth();
        int layoutWidth = layoutParams.width;
        if (layoutWidth == ViewGroup.LayoutParams.MATCH_PARENT) {
            layoutWidth = imageView.getMeasuredWidth();
        }
        int layoutHeight = layoutParams.height;
        if (realWidth < layoutWidth) { // 实际尺寸小于设置尺寸
            layoutHeight = layoutWidth * realHeight / realWidth;
        } else if (realWidth == layoutWidth) {// 实际尺寸等于设置尺寸
            layoutParams.height = realHeight;
        } else if (realWidth > layoutWidth) {// 实际尺寸大于设置尺寸
            layoutHeight = layoutWidth * realHeight / realWidth;
        }
        layoutParams.height = layoutHeight;
        imageView.setLayoutParams(layoutParams);
    }

    public void calculateWidthByHeight(ImageInfo imageInfo, ViewGroup.LayoutParams layoutParams) {
        float ratio = imageView.getAspectRatio();
        if (ratio > 0f) {
            return;
        }
        int realHeight = imageInfo.getHeight();
        int realWidth = imageInfo.getWidth();
        int layoutHeight = layoutParams.height;
        if (layoutHeight == ViewGroup.LayoutParams.MATCH_PARENT) {
            layoutHeight = imageView.getMeasuredHeight();
        }
        int layoutWidth = layoutParams.width;
        if (realHeight < layoutHeight) { // // 实际尺寸小于设置尺寸
            layoutWidth = layoutHeight * realWidth / realHeight;
        } else if (realHeight == layoutHeight) { // 实际尺寸等于设置尺寸
            layoutWidth = realWidth;
        } else if (realHeight > layoutHeight) {  // 实际尺寸大于设置尺寸
            layoutWidth = layoutHeight * realWidth / realHeight;
        }
        layoutParams.width = layoutWidth;
        imageView.setLayoutParams(layoutParams);
    }
}

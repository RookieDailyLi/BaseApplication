package com.credithc.commonlib.widget.fresco;

import android.graphics.drawable.Animatable;

import com.facebook.drawee.controller.ControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

import java.util.ArrayList;

/**
 * Created by liuwenjie on 2017/10/20.
 * lwjfork@gmail.com
 */

public class ControllerListeners implements ControllerListener<ImageInfo> {
    
    
    ArrayList<ControllerListener<ImageInfo>> listeners = new ArrayList<>();
    
    public void addControllerListener(ControllerListener<ImageInfo> item) {
        listeners.add(item);
    }
    
    public void removeControllerListener(ControllerListener<ImageInfo> item) {
        listeners.remove(item);
    }
    
    
    @Override
    public void onSubmit(String id, Object callerContext) {
        
        for(ControllerListener<ImageInfo> listener : listeners) {
            listener.onSubmit(id, callerContext);
        }
        
    }
    
    @Override
    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
        for(ControllerListener<ImageInfo> listener : listeners) {
            listener.onFinalImageSet(id, imageInfo, animatable);
        }
    }
    
    @Override
    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
        for(ControllerListener<ImageInfo> listener : listeners) {
            listener.onIntermediateImageSet(id, imageInfo);
        }
    }
    
    @Override
    public void onIntermediateImageFailed(String id, Throwable throwable) {
        for(ControllerListener<ImageInfo> listener : listeners) {
            listener.onIntermediateImageFailed(id, throwable);
        }
    }
    
    @Override
    public void onFailure(String id, Throwable throwable) {
        for(ControllerListener<ImageInfo> listener : listeners) {
            listener.onFailure(id, throwable);
        }
    }
    
    @Override
    public void onRelease(String id) {
        for(ControllerListener<ImageInfo> listener : listeners) {
            listener.onRelease(id);
        }
    }
}

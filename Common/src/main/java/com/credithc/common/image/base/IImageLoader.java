package com.credithc.common.image.base;

import android.content.Context;
import androidx.fragment.app.Fragment;
import android.widget.ImageView;

import com.credithc.common.image.util.ReSize;


/**
 * Created by liuwenjie on 2017/11/14.
 * lwjfork@gmail.com
 */

public interface IImageLoader {
    
    public void load(ImageView imageView);
    
    
    public IImageLoader init(Context context);
    
    public IImageLoader init(Fragment fragment);
    
    public IImageLoader withDefaultRes(int defaultRes);
    
    
    public IImageLoader withErrorRes(int errorRes);
    
    
    public IImageLoader isAnim(boolean isAnim);
    
    
    public IImageLoader withCircle();
    
    public IImageLoader withCircleBorder(int width, int colors);
    
    public IImageLoader withResize(ReSize resize);
    
    
    public IImageLoader withRound(float radius);
    
    public IImageLoader withRound(float tlradius, float trradius, float brRadius, float blRadius);
    
    
    public IImageLoader withUrl(String url, @ImageType int type);
    
    public IImageLoader withUrl(String url);
    
    public IImageLoader isGif(boolean isGif);
    
    
    public IImageLoader withBlur(int iterations, int blurRadius);
    
    
}

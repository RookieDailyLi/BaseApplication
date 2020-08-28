package com.credithc.commonlib.widget.fresco;


import com.credithc.commonlib.image.base.BaseLoadImageUrlConverter;

/**
 * Created by liuwenjie on 2017/9/14.
 * lwjfork@gmail.com
 */

public class FrescoConvertLoadImageUrl extends BaseLoadImageUrlConverter {
    @Override
    public String convertNetUrl(String url) {
        return url;
    }
    
    @Override
    public String convertFile(String filePath) {
        if(filePath.startsWith("/")) {
            return "file://" + filePath;
        }
        else {
            return "file:///" + filePath;
        }
    }
    
    @Override
    public String convertContent(String contentPath) {
        return "content://" + contentPath;
    }
    
    @Override
    public String convertAssets(String assets) {
        return "asset://" + assets;
    }
    
    @Override
    public String convertDrawable(String drawable) {
        return "res:///" + drawable;
    }
}

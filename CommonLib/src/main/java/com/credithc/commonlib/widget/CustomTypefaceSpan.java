package com.credithc.commonlib.widget;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Parcel;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.text.ParcelableSpan;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

import com.credithc.commonlib.util.TypefaceUtil;


/**
 * Created by lwj on 2019/1/2.
 * lwjfork@gmail.com
 * 特殊字体
 */
public class CustomTypefaceSpan extends MetricAffectingSpan implements ParcelableSpan {

    private Typeface typeface;

    public CustomTypefaceSpan(@FontRes int resId) {
        this(TypefaceUtil.getTypeface(resId));
    }

    public CustomTypefaceSpan(Typeface typeface) {
        this.typeface = typeface;
    }


    @Override
    public int getSpanTypeId() {
        return 13;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString("CustomTypefaceSpan");
    }

    @Override
    public void updateMeasureState(@NonNull TextPaint p) {
        apply(p);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        apply(tp);
    }

    private void apply(Paint paint) {
        if (typeface == null) {
            return;
        }
        int oldStyle;

        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }
        Typeface tf = Typeface.create(typeface, oldStyle);
        int fake = oldStyle & ~tf.getStyle();

        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(tf);
    }
}

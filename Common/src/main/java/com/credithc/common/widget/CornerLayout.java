package com.credithc.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.credithc.common.R;


public class CornerLayout extends RelativeLayout {

    private Path mPath;

    private final RectF mCornerRect = new RectF();

    private Paint mPaint;

    private float[] radii = new float[8];

    public CornerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CornerLayout);
        float cornerRadius = array.getDimensionPixelSize(R.styleable.CornerLayout_cornerRadius, 0);
        array.recycle();

        radii[0] = cornerRadius;
        radii[1] = cornerRadius;
        radii[2] = cornerRadius;
        radii[3] = cornerRadius;

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCornerRect.left = getPaddingLeft();
        mCornerRect.top = getPaddingTop();
        mCornerRect.right = w - getPaddingRight();
        mCornerRect.bottom = h - getPaddingBottom();
        mPath.addRoundRect(mCornerRect, radii, Path.Direction.CW);
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        canvas.clipPath(mPath);
        return super.drawChild(canvas, child, drawingTime);
    }
}

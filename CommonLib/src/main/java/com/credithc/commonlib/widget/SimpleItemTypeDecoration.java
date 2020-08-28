package com.credithc.commonlib.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by lwj on 2018/11/17.
 * lwjfork@gmail.com
 */

public class SimpleItemTypeDecoration implements SimpleDecoration.ItemTypeDecoration {

    private int height;
    private Paint dividerPaint;
    private Paint marginPaint;
    private int marginLeft;
    private int marginRight;

    /**
     * @param height 分割线高度
     * @param color  分割线颜色
     */
    public SimpleItemTypeDecoration(int height, int color) {
        this.height = height;
        dividerPaint = new Paint();
        marginPaint = new Paint();
        dividerPaint.setColor(color);
    }

    /**
     * @param height      分割线高度
     * @param marginLeft  分割线左侧偏移
     * @param marginColor 左侧偏移颜色
     * @param color       分割线颜色
     */
    public SimpleItemTypeDecoration(int height, int marginLeft, int marginColor, int color) {
        this.height = height;
        this.marginLeft = marginLeft;
        dividerPaint = new Paint();
        marginPaint = new Paint();
        dividerPaint.setColor(color);
        marginPaint.setColor(marginColor);
    }

    /**
     * @param height      分割线高度
     * @param marginLeft  分割线左侧偏移
     * @param marginColor 左侧偏移颜色
     * @param color       分割线颜色
     */
    public SimpleItemTypeDecoration(int height, int marginLeft, int marginRight, int marginColor, int color) {
        this.height = height;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        dividerPaint = new Paint();
        marginPaint = new Paint();
        dividerPaint.setColor(color);
        marginPaint.setColor(marginColor);
    }

    @Override
    public void setItemOffsets(RecyclerView parent, Rect outRect, int position, int count) {
        if (position == count - 1) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set(0, 0, 0, height);
        }
    }

    @Override
    public void drawItemDivider(View child, RecyclerView parent, Canvas c, int position, int count) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int top = child.getBottom();
        int bottom = top + height;
        if (marginRight > 0 && marginPaint != null) {
            c.drawRect(right - marginRight, top, right, bottom, marginPaint);
        }
        if (marginLeft > 0 && marginPaint != null) {
            c.drawRect(left, top, left + marginLeft, bottom, marginPaint);
        }
        c.drawRect(left + marginLeft, top, right - marginRight, bottom, dividerPaint);
    }


}

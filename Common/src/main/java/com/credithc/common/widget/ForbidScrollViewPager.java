package com.credithc.common.widget;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by lwj on 2018/12/25.
 * lwjfork@gmail.com
 */
public class ForbidScrollViewPager extends ViewPager {

    private static final String TAG = ForbidScrollViewPager.class.getSimpleName();
    private boolean isForbidScroll;
    private boolean isExpenseOnTouch = false;
    private float mLastX;

    public ForbidScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForbidScrollViewPager(Context context) {
        this(context, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isExpenseOnTouch) {
            return false;
        } else {
            return super.onTouchEvent(ev);
        }
    }

    /**
     * 返回false时表示该ViewPager不去获取touch事件
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isForbidScroll()) {
//			L.i(TAG, "onInterceptTouchEvent isForbidScroll");
            return false;
        }
        int action = ev.getAction();
        float x = ev.getX();
        int numberBigEnoughForOnPageScrolledMethodExed = 100;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                boolean isCurLastItem = getCurrentItem() == getAdapter().getCount() - 1;
                boolean isMovingToRight = x - mLastX > numberBigEnoughForOnPageScrolledMethodExed;
                boolean isCurFirstItem = getCurrentItem() == 0;
                boolean isMovingToLeft = mLastX - x > numberBigEnoughForOnPageScrolledMethodExed;
                if (isCurLastItem && isMovingToLeft) {
//				L.i(TAG, "onInterceptTouchEvent isCurLastItem && isMovingToLeft");
                    return false;
                }
                if (isCurFirstItem && isMovingToRight) {
//				L.i(TAG, "onInterceptTouchEvent isCurFirstItem && isMovingToRight");
                    return false;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;

            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    public boolean isForbidScroll() {
        return isForbidScroll;
    }

    /**
     * 通过设置isForbidScroll的boolean值，来切换是父ViewPager还是子ViewPager来截取手势
     *
     * @param isForbidScroll
     */
    public void setForbidScroll(boolean isForbidScroll) {
        this.isForbidScroll = isForbidScroll;
    }

    /**
     * 通过设置isExpenseOnTouch，来调整父ViewPager是否支持左右滑动
     *
     * @param isExpenseOnTouch
     */
    public void setExpenseOnTouch(boolean isExpenseOnTouch) {
        this.isExpenseOnTouch = isExpenseOnTouch;
    }


    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, !isForbidScroll);
    }
}
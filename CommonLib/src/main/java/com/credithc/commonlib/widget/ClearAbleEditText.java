package com.credithc.commonlib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;


import com.credithc.commonlib.R;

import java.util.ArrayList;

/**
 * 可以清空的 EditText
 */
@SuppressLint({"ClickableViewAccessibility", "AppCompatCustomView"})
public class ClearAbleEditText extends EditText implements OnFocusChangeListener, TextWatcher {
    // EditText右侧的删除按钮
    private Drawable mClearDrawable;
    public boolean hasFoucs;
    private boolean isClearAble = true;
    private ArrayList<OnFocusChangeListener> onFocusChangeListeners = new ArrayList<>();

    public ClearAbleEditText(Context context) {
        this(context, null);
    }

    public ClearAbleEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearAbleEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttr(context, attrs, defStyle);
        init();
        if (isInEditMode()) {
            setClearIconVisible(isClearAble);
        }
    }

    private void parseAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ClearAbleEditText, defStyleAttr, 0);
        isClearAble = a.getBoolean(R.styleable.ClearAbleEditText_clearAble, true);
        mClearDrawable = a.getDrawable(R.styleable.ClearAbleEditText_clearIcon);
        a.recycle();
    }


    public Drawable getClearDrawable() {
        return mClearDrawable;
    }

    public void setClearDrawable(Drawable mClearDrawable) {
        this.mClearDrawable = mClearDrawable;
    }


    public boolean isClearAble() {
        return isClearAble;
    }

    public void setClearAble(boolean clearAble) {
        isClearAble = clearAble;
        if (mClearDrawable == null && isClearAble) {
            mClearDrawable = getResources().getDrawable(R.drawable.icon_editdel);
            mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        } else if (isClearAble) {
            mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        }
    }

    private void init() {
        setClearAble(isClearAble);

        // 默认设置隐藏图标
        setClearIconVisible(false);
        // 设置焦点改变的监听
        setOnFocusChangeListener(this);
        // 设置输入框里面内容发生改变的监听
        addTextChangedListener(this);
    }

    /**
     * @说明：isInnerWidth, isInnerHeight为ture，触摸点在删除图标之内，则视为点击了删除图标 event.getX()
     * 获取相对应自身左上角的X坐标 event.getY() 获取相对应自身左上角的Y坐标 getWidth() 获取控件的宽度 getHeight()
     * 获取控件的高度 getTotalPaddingRight() 获取删除图标左边缘到控件右边缘的距离 getPaddingRight()
     * 获取删除图标右边缘到控件右边缘的距离 isInnerWidth: getWidth() - getTotalPaddingRight()
     * 计算删除图标左边缘到控件左边缘的距离 getWidth() - getPaddingRight() 计算删除图标右边缘到控件左边缘的距离
     * isInnerHeight: distance 删除图标顶部边缘到控件顶部边缘的距离 distance + height
     * 删除图标底部边缘到控件顶部边缘的距离
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCompoundDrawables()[2] != null) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                Rect rect = getCompoundDrawables()[2].getBounds();
                int height = rect.height();
                int distance = (getHeight() - height) / 2;
                boolean isInnerWidth = x > (getWidth() - getTotalPaddingRight()) && x < (getWidth() - getPaddingRight());
                boolean isInnerHeight = y > distance && y < (distance + height);
                if (isInnerWidth && isInnerHeight) {
                    this.setText("");
                    return false;
                }
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候， 输入长度为零，隐藏删除图标，否则，显示删除图标
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0 && isClearAble);
        } else {
            setClearIconVisible(false);
        }
        for (OnFocusChangeListener onFocusChangeListener : onFocusChangeListeners) {
            onFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    private void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right, getCompoundDrawables()[3]);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0 && isClearAble);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0 && isClearAble);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    public void addOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        onFocusChangeListeners.add(onFocusChangeListener);
    }

    public void removeOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        onFocusChangeListeners.remove(onFocusChangeListener);
    }

    public String getTrimText() {
        return getText().toString().trim();
    }
}

package com.credithc.commonlib.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.credithc.commonlib.R;


/**
 * Created by lwj on 2019/1/8.
 * lwjfork@gmail.com
 * 可以显示密文和明文的 TextView
 */
@SuppressWarnings("all")
public class CipherTextView extends TextView {

    private String cipherText;
    private CharSequence realText;

    public boolean cipher = false;
    protected Context context;

    public CipherTextView(Context context) {
        this(context, null);
    }

    public CipherTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CipherTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public CipherTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, 0);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    protected void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.context = context;
        parseAttr(attrs, defStyleAttr, defStyleRes);
    }

    private void parseAttr(@Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CipherTextView, defStyleAttr, defStyleRes);
        cipherText = typedArray.getString(R.styleable.CipherTextView_cipherText);
        if (cipherText == null || cipherText.length() == 0) {
            cipherText = "****";
        }
        typedArray.recycle();
    }

    /**
     * 设置加密装态
     *
     * @param isCipther
     */
    public void setCipher(boolean isCipther) {
        this.cipher = isCipther;
        if (isCipther) {
            setText(cipherText);
        } else {
            setText(realText);
        }
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText;
    }

    public void setRealText(CharSequence realText) {
        this.realText = realText;
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (cipher) {
            return;
        }
        realText = text;
    }
}

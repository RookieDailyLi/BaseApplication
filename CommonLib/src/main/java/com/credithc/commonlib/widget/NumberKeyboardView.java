package com.credithc.commonlib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.util.AttributeSet;


import com.credithc.commonlib.R;

import java.util.List;


public class NumberKeyboardView extends KeyboardView {
    private Context context;
    private Keyboard keyboard;

    public NumberKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        keyboard = new Keyboard(context, R.xml.number_input);
        setKeyboard(keyboard);
        setEnabled(true);
        setPreviewEnabled(false);
        setOnKeyboardActionListener(new DefaultKeyboardActionListener() {

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                if (onKeyActionListener == null) {
                    return;
                }
                if (primaryCode == Keyboard.KEYCODE_DELETE) {
                    onKeyActionListener.onDelete();
                } else if (primaryCode == 88) {
                    //do nothing
                } else {//其它按键      ASCII码表转换
                    String string = Character.toString((char) primaryCode);
                    onKeyActionListener.onAddChar(string.charAt(0));
                }
            }

        });
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Keyboard keyboard = getKeyboard();
        if (keyboard == null) {
            return;
        }
        List<Keyboard.Key> keys = keyboard.getKeys();
        for (Keyboard.Key key : keys) {
            if (TextUtils.isEmpty(key.label)) {
                if (key.codes[0] == 88) {
                    resetEmptyBtn(key, canvas);
                } else {
                    resetDelBtn(key, canvas);
                }
            }
        }
    }

    /**
     * 绘制删除键
     *
     * @param key
     * @param canvas
     * @author Song
     */
    private void resetDelBtn(Keyboard.Key key, Canvas canvas) {
        //将OK键重新绘制
        Drawable npd = context.getResources().getDrawable(R.drawable.ic_keyboard_delete);
        npd.setBounds(key.x - 1, key.y - 1, key.x + key.width, key.y + key.height);
        npd.draw(canvas);
    }

    /**
     * 绘制空白键
     *
     * @param key
     * @param canvas
     * @author Song
     */
    private void resetEmptyBtn(Keyboard.Key key, Canvas canvas) {
        //将OK键重新绘制
        Drawable npd = context.getResources().getDrawable(R.drawable.bg_keyboard_empty);
        npd.setBounds(key.x, key.y - 1, key.x + key.width + 1, key.y + key.height);
        npd.draw(canvas);
    }


    OnKeyActionListener onKeyActionListener;

    public void setOnKeyActionListener(OnKeyActionListener onKeyActionListener) {
        this.onKeyActionListener = onKeyActionListener;
    }

    public interface OnKeyActionListener {

        void onDelete();

        void onAddChar(char c);


    }
}

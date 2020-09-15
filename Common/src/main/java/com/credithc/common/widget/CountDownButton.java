package com.credithc.common.widget;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.os.CountDownTimer;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lwj on 2018/12/15.
 * lwjfork@gmail.com
 */
@SuppressWarnings("AppCompatCustomView")
public class CountDownButton extends TextView implements LifecycleObserver {


    OnTimerListener onTimerListener;
    CountDownTimer countDownTimer;
    boolean isFinish = true;

    public CountDownButton(Context context) {
        super(context);
    }

    public CountDownButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CountDownButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void start(long second, long secondInterval) {
        countDownTimer = new CountDownTimer(second * 1000L, secondInterval * 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (onTimerListener != null) {
                    onTimerListener.onTimerTick(millisUntilFinished / 1000L);
                }
            }

            @Override
            public void onFinish() {
                isFinish = true;
                if (onTimerListener != null) {
                    onTimerListener.onTimerFinish();
                }
            }
        };
        isFinish = false;
        countDownTimer.start();
        if (onTimerListener != null) {
            onTimerListener.onTimerStart();
        }
    }

    public void stop() {
        isFinish = true;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (onTimerListener != null) {
            onTimerListener.onTimerCancel();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        stop();
    }

    public void setOnTimerListener(OnTimerListener onTimerListener) {
        this.onTimerListener = onTimerListener;
    }

    public interface OnTimerListener {
        void onTimerStart();

        void onTimerTick(long second);

        void onTimerFinish();

        void onTimerCancel();
    }

    public boolean isFinish() {
        return isFinish;
    }
}

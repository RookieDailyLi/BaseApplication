package com.credithc.commonlib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class TimingTextView extends TextView {
    private static final int TIME_START = 0x001;
    private static final int TIME_FINISH = 0x002;
    private static final int TIME_CHANGE = 0x003;
    private static final int TIME_RESET = 0x004;
    private static final int TIMING = 0x005;
    
    // 中文模式，显示格式为 00小时00分00秒
    public static final int CHINESE = 0x101;
    
    // 数字模式，显示格式为 00:00:00
    public static final int MATH = 0x102;
    
    
    // 显示精确到小时
    public static final int HOUR = 0x201;
    
    // 显示精确到分
    public static final int MINUTE = 0x202;
    
    // 显示精确到秒
    public static final int SECOND = 0x203;
    
    // 当前显示格式
    private int languageMode = MATH;
    
    // 当前精确位数
    private int timeMode = HOUR;
    
    // 倒计时模式下的最大时间
    private int maxTime = 60;
    
    // 当前时间
    private int time;
    
    // 是否倒计时模式
    private boolean isCountDown = true;
    
    // 是否自动刷新显示
    private boolean isAutoRefresh = true;
    
    private TimingThread thread;
    private boolean isTiming;
    private boolean isPausing;
    
    // 计时回调
    private OnTimingListener listener;
    
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            try {
                switch(msg.what) {
                    case TIME_CHANGE:
                        setEnabled(false);
                        setText(setTime(time));
                        break;
                    case TIME_START:
                        if(listener != null)
                            listener.onStart();
                        setEnabled(false);
                        break;
                    case TIME_RESET:
                        //                        setText(calculateTime(time));
                        setText("获取验证码");
                        setEnabled(true);
                        stop();
                        break;
                    case TIME_FINISH:
                        if(listener != null)
                            listener.onFinish();
                        reset();
                        setEnabled(true);
                        break;
                    case TIMING:
                        if(listener != null) {
                            listener.onTiming(((time / (60 * 60) + "").length() == 1 ? "0" : "") + time / (60 * 60),
                                    ((time % (60 * 60) / 60 + "").length() == 1 ? "0" : "") + time % (60 * 60) / 60,
                                    ((time % 60 + "").length() == 1 ? "0" : "") + time % 60, TimingTextView.this);
                            setEnabled(false);
                        }
                        break;
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    };
    
    public TimingTextView(Context context) {
        this(context, null);
    }
    
    public TimingTextView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }
    
    public TimingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setText("获取验证码");
    }
    
    /**
     * 计算显示的时间
     *
     * @param time 当前时间
     * @return 时间字串
     */
    private String setTime(int time) {
        String hour = ((time / (60 * 60) + "").length() == 1 ? "0" : "") + time / (60 * 60);
        String minute = ((time % (60 * 60) / 60 + "").length() == 1 ? "0" : "") + time % (60 * 60) / 60;
        String second = ((time % 60 + "").length() == 1 ? "0" : "") + time % 60;
        
        //        return (timeMode == HOUR ? hour : "") + (timeMode == HOUR ? languageMode == CHINESE ? "小时" : ":" : "")
        //                + (timeMode == HOUR || timeMode == MINUTE ? minute : "") + (timeMode == HOUR || timeMode == MINUTE ? languageMode == CHINESE ? "分" : ":" : "")
        //                + second + (languageMode == CHINESE ? "秒" : "");
        return second + (languageMode == CHINESE ? "秒" : "s");
    }
    
    private void sendMessage(int what, Handler handler) {
        Message message = new Message();
        message.what = what;
        handler.sendMessage(message);
    }
    
    /**
     * 开始计时
     */
    public void start() {
        if(!isTiming) {
            thread = new TimingThread();
            thread.start();
            setEnabled(false);
        }
    }
    
    /**
     * 暂停计时
     */
    public void pause() {
        isPausing = true;
    }
    
    /**
     * 继续计时
     */
    public void resume() {
        isPausing = false;
    }
    
    /**
     * 停止计时
     */
    public void stop() {
        isTiming = false;
        isPausing = false;
        setEnabled(true);
    }
    
    /**
     * 时间重置
     */
    public void reset() {
        time = isCountDown ? maxTime : 0;
        isTiming = true;
        isPausing = false;
        setEnabled(true);
        sendMessage(TIME_RESET, handler);
    }
    
    /**
     * 设置显示格式
     *
     * @param languageMode 模式，可选项：CHINESE、MATH
     */
    public void setLanguageMode(int languageMode) {
        this.languageMode = languageMode;
        setText(setTime(isCountDown ? maxTime : 0));
    }
    
    /**
     * 设置显示精确位数
     *
     * @param timeMode 精确位数，可选项：HOUR、MINUTE、SECOND
     */
    public void setTimeMode(int timeMode) {
        this.timeMode = timeMode;
        setText(setTime(isCountDown ? maxTime : 0));
    }
    
    /**
     * 最大计时时间，只在倒计时模式下有效
     *
     * @param maxTime 最大时间，单位秒
     */
    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
        setText(setTime(isCountDown ? maxTime : 0));
    }
    
    /**
     * 设置是否倒计时
     * 设置为false时从零开始往上计时，无计时上限
     *
     * @param isCountDown
     */
    public void setIsCountDown(boolean isCountDown) {
        this.isCountDown = isCountDown;
        setText(setTime(isCountDown ? maxTime : 0));
    }
    
    /**
     * 是否自动刷新显示
     * 设置为false时需要在回调接口里手动更新需要显示的文字
     *
     * @param isAutoRefresh
     */
    public void setIsAutoRefresh(boolean isAutoRefresh) {
        this.isAutoRefresh = isAutoRefresh;
    }
    
    /**
     * 获取是否正在计时
     *
     * @return
     */
    public boolean isTiming() {
        return isTiming;
    }
    
    /**
     * 获取计时是否暂停
     *
     * @return
     */
    public boolean isPausing() {
        return isPausing;
    }
    
    /**
     * 计时回调接口
     */
    public interface OnTimingListener {
        /**
         * 计时过程中回调
         *
         * @param hour     当前小时数
         * @param minute   当前分钟数
         * @param second   当前秒数
         * @param textView 当前控件实体
         */
        void onTiming(String hour, String minute, String second, TextView textView);
        
        /**
         * 计时开始时回调
         */
        void onStart();
        
        /**
         * 计时结束时回调，倒计时模式下才有此回调
         */
        void onFinish();
    }
    
    /**
     * 设置计时回调接口
     *
     * @param listener
     */
    public void setOnTimingListener(OnTimingListener listener) {
        this.listener = listener;
    }
    
    class TimingThread extends Thread {
        @Override
        public void run() {
            time = isCountDown ? maxTime : 0;
            isTiming = true;
            isPausing = false;
            sendMessage(TIME_START, handler);
            do {
                try {
                    if(isAutoRefresh) {
                        sendMessage(TIME_CHANGE, handler);
                    }
                    else {
                        sendMessage(TIMING, handler);
                    }
                    time = isCountDown ? time - 1 : time + 1;
                    if(time >= 0)
                        sleep(1000);
                    while(isPausing) {
                        sleep(500);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } while(isTiming && (isCountDown ? time >= 0 : true));
            isTiming = false;
            time = 0;
            sendMessage(TIME_FINISH, handler);
        }
    }

    public void destroyView(){
        if(handler!=null){

            handler.removeCallbacksAndMessages(null);
        }
        if(thread!=null){
            isTiming=false;
        }
    }
}
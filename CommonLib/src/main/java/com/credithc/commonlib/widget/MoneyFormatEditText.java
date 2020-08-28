package com.credithc.commonlib.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;

import com.credithc.commonlib.util.ExceptionUtil;
import com.credithc.commonlib.util.FormatUtil;
import com.credithc.commonlib.util.MoneyUtil;
import com.credithc.commonlib.util.RegularUtil;


/**
 * Created by lwj on 2018/11/21.
 * lwjfork@gmail.com
 */
@SuppressWarnings("all")
public class MoneyFormatEditText extends NoPasteOrCopyEditText {
    public MoneyFormatEditText(Context context) {
        super(context);

    }

    public MoneyFormatEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MoneyFormatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @TargetApi(21)
    public MoneyFormatEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private String befroeStr;
    private int beforeSelection;

    @Override
    protected void init() {
        super.init();
        addTextChangedListener(new SimpleTextWatchAdapter() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                befroeStr = s.toString();
                beforeSelection = getSelectionStart();
            }

            @Override
            public void afterTextChanged(Editable s) {
                String srcStr = s.toString();
                int startSelection = getSelectionStart();
                formatStr(srcStr, startSelection);
                String text = getEditableText().toString();
                onEditValue(text.replaceAll(",", ""));
            }
        });
        int inputType = getInputType();
        if (inputType != InputType.TYPE_CLASS_NUMBER && inputType != (InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL)) {
            ExceptionUtil.illegalArgument("InputType must be InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL |InputType.TYPE_NUMBER_FLAG_DECIMAL!");
        }
    }


    protected void onEditValue(String value) {
        if (onEditTextChangeListener != null) {
            onEditTextChangeListener.onText(value);
        }
    }


    private boolean isNumberType() {
        return getInputType() == InputType.TYPE_CLASS_NUMBER;
    }

    private boolean isDecimal() {
        return getInputType() == (InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }


    private void formatStr(String srcStr, int startSelection) {
        if (TextUtils.isEmpty(srcStr)) {
            return;
        }
        if ("0".equals(srcStr)) {
            return;
        }
        if (RegularUtil.isMatch(srcStr, "0+")) {
            // fix 模拟器一次性输入多个0 导致崩溃bug
            setText("0");
            setSelection(1);
            return;
        }
        if (".".equals(srcStr)) { // 单独小数点不可以输入，变成0
            setText("");
//            setSelection(1);
            return;
        }
        if (srcStr.startsWith(".")) {
            setText("0");
            return;
        }
        if (",".equals(srcStr)) {
            setText("");
            setSelection(1);
            return;
        }
        if (srcStr.startsWith(",")) {
            setText(srcStr.substring(1, srcStr.length()));
            setSelection(0);
            return;
        }
        if (srcStr.startsWith("0")) {
            if (isNumberType()) {
                setText(srcStr.substring(1, srcStr.length()));
                setSelection(Math.max(0, startSelection - 1));
                return;
            } else if (isDecimal()) { // 小数格式的，允许以 0 开头，但是只允许一个 0 开头
                if (RegularUtil.isMatch(srcStr, "0[0-9]{1}.*")) {
                    setText(srcStr.substring(1, srcStr.length()));
                    setSelection(Math.max(0, startSelection - 1));
                    return;
                }
            }
        }


        String moneyLong = srcStr.replaceAll(",", "");
        if (isNumberType()) {  // 整数
            if (moneyLong.length() > length) { // 最高11位
                setText(befroeStr);
                int index = Math.max(beforeSelection - 1, 0);
                int realIndex = Math.min(index, getEditableText().length());
                setSelection(realIndex);
                return;
            }
        } else if (isDecimal()) {   // 小数
            if (moneyLong.length() > length) { // 最高11位
                setText(befroeStr);
                int index = Math.max(beforeSelection - 1, 0);
                int realIndex = Math.min(index, getEditableText().length());
                setSelection(realIndex);
                return;
            }
        }

//        if (Long.parseLong(moneyLong) > maxLong) {
//            setText(formatStr(maxLong + "")); // 最大
//            setSelection(getText().length());
//            return;
//        }
        if (MoneyUtil.isFormatMoney(srcStr)) {
            return;
        }
        String resultStr = MoneyUtil.formatMoney(srcStr);
        setText(resultStr);
        if (resultStr.length() > srcStr.length()) {
            setSelection(startSelection + 1);
        } else if (resultStr.length() < srcStr.length()) {
            setSelection(Math.min(Math.max(resultStr.length(), 0), Math.max(startSelection - 1, 0)));
        } else {
            setSelection(startSelection);
        }


        // 找索引
    }


    private int length = 11;
    private double maxValue = Double.MAX_VALUE;

    public void setMaxLength(int length) {
        this.length = length;
    }

    /**
     * 可输入最大值
     * 如果包含小数，则最大长度值为小数部分+小数点+整数部分
     * length = 5  isDecimal = true 即最大值为 99.99
     * 不包含小数，则最大长度即整数部分长度
     * length = 5  isDecimal = false 即最大值为 9999
     *
     * @param length    数字长度
     * @param isDecimal 是否包含小数
     */
    public void setMaxLength(int length, boolean isDecimal) {
        this.length = length;
        if (isDecimal) {
            if (length >= 4) {
                maxValue = Math.pow(10, length + 1 - 4) - 1 + 0.99D;
            } else {
                ExceptionUtil.illegalArgument("length 必须要大于等于4 即最小值必须是 0.00 四位，最大值是 9.99 四位");
            }
        } else {
            maxValue = Math.pow(10, length + 1) - 1;
        }

    }


    public String getRealMoney() {
        String srcStr = getText().toString();
        if (srcStr.endsWith(".")) {
            srcStr = srcStr.substring(0, srcStr.length() - 1);
        } else if (srcStr.endsWith(".0")) {
            srcStr = srcStr.substring(0, srcStr.length() - 2);
        }
        return srcStr.replaceAll(",", "");
    }

    public long getRealLongMoney() {
        return FormatUtil.str2Long(getRealMoney());
    }


    public double getRealDoubleMoney() {
        return FormatUtil.str2Double(getRealMoney());
    }


    public interface OnEditTextChangeListener {

        void onText(String value);

    }

    OnEditTextChangeListener onEditTextChangeListener;

    public void setOnEditTextChangeListener(OnEditTextChangeListener onEditTextChangeListener) {
        this.onEditTextChangeListener = onEditTextChangeListener;
    }
}

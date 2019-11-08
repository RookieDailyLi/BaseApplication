package com.credithc.commonlib.widget.excpetion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.credithc.commonlib.widget.excpetion.base.ILoadCoversView;
import com.credithc.commonlib.widget.excpetion.base.ILoadEmptyView;
import com.credithc.commonlib.widget.excpetion.base.ILoadFailureView;
import com.credithc.commonlib.widget.excpetion.base.INetExceptionView;
import com.credithc.commonlib.widget.excpetion.common.LoadCoversView;
import com.credithc.commonlib.widget.excpetion.common.LoadEmptyView;
import com.credithc.commonlib.widget.excpetion.common.LoadFailureView;
import com.credithc.commonlib.widget.excpetion.common.LoadNetExceptionView;

/**
 * @author liyong
 * @date 2019/11/4
 * @des
 */
public class ExceptionLayout extends BaseExceptionLayout {


    public ExceptionLayout(@NonNull Context context) {
        this(context, null);
    }

    public ExceptionLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public ExceptionLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected ILoadCoversView createCoversView() {
        return new LoadCoversView(context);
    }

    @Override
    protected ILoadEmptyView createEmptyView() {
        return new LoadEmptyView(context);
    }

    @Override
    protected ILoadFailureView createFailureView() {
        return new LoadFailureView(context);
    }

    @Override
    protected INetExceptionView createNetExceptionView() {
        return new LoadNetExceptionView(context);
    }


}

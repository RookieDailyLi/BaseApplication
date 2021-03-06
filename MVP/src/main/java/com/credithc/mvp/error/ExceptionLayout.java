package com.credithc.mvp.error;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

import com.credithc.mvp.error.base.ILoadCoversView;
import com.credithc.mvp.error.base.ILoadEmptyView;
import com.credithc.mvp.error.base.ILoadFailureView;
import com.credithc.mvp.error.base.INetExceptionView;
import com.credithc.mvp.error.common.LoadCoversView;
import com.credithc.mvp.error.common.LoadEmptyView;
import com.credithc.mvp.error.common.LoadFailureView;
import com.credithc.mvp.error.common.LoadNetExceptionView;

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

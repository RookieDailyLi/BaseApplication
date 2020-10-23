package com.credithc.mvp.error;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.credithc.mvp.error.base.ILoadCoversView;
import com.credithc.mvp.error.base.ILoadEmptyView;
import com.credithc.mvp.error.base.ILoadFailureView;
import com.credithc.mvp.error.base.INetExceptionView;
import com.credithc.mvp.error.base.IShow;


/**
 * @author liyong
 * @date 2019/11/4
 * @des 错误页展示布局
 */
public abstract class BaseExceptionLayout extends FrameLayout implements ILoadEmptyView, ILoadFailureView, INetExceptionView, ILoadCoversView, IShow {


    protected Context context;
    protected ILoadEmptyView loadEmpty;
    protected ILoadFailureView loadFailure;
    protected INetExceptionView netException;
    protected ILoadCoversView loadCovers;

    protected OnClickListener emptyClickListener;
    protected OnClickListener failureClickListener;
    protected OnClickListener netExceptionClickListener;

    public BaseExceptionLayout(@NonNull Context context) {
        this(context, null);
    }

    public BaseExceptionLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public BaseExceptionLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    protected abstract ILoadCoversView createCoversView();

    protected abstract ILoadEmptyView createEmptyView();

    protected abstract ILoadFailureView createFailureView();

    protected abstract INetExceptionView createNetExceptionView();

    private void addEmptyView() {
        if (loadEmpty != null) {
            return;
        }
        loadEmpty = createEmptyView();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(loadEmpty.getEmptyView(), params);
        if (emptyClickListener != null) {
            loadEmpty.setLoadEmptyOnClickListener(emptyClickListener);
        }
    }


    private void addLoadFailureView() {
        if (loadFailure != null) {
            return;
        }
        loadFailure = createFailureView();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(loadFailure.getLoadFailureView(), params);
        if (failureClickListener != null) {
            loadFailure.setLoadFailureOnClickListener(failureClickListener);
        }
    }


    private void addNetExceptionView() {
        if (netException != null) {
            return;
        }
        netException = createNetExceptionView();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(netException.getNetExceptionView(), params);
        if (netExceptionClickListener != null) {
            netException.setNetExceptionOnClickListener(netExceptionClickListener);
        }
    }


    @Override
    public final View getEmptyView() {
        return loadEmpty.getEmptyView();
    }


    @Override
    public final View getLoadFailureView() {
        return loadFailure.getLoadFailureView();
    }


    @Override
    public final View getNetExceptionView() {
        return netException.getNetExceptionView();
    }


    @Override
    public void showEmpty(int errorIcon, String tv, String btn) {
        showEmptyView();
        loadEmpty.showEmpty(errorIcon, tv, btn);
    }

    @Override
    public void showLoadFailure(int errorIcon, String tv, String btn) {
        showLoadFailureView();
        loadFailure.showLoadFailure(errorIcon, tv, btn);
    }

    @Override
    public void showNetException(int netIcon, String tv, String btn) {
        showNetExceptionView();
        netException.showNetException(netIcon, tv, btn);
    }

    @Override
    public final void setLoadEmptyOnClickListener(OnClickListener onClickListener) {
        emptyClickListener = onClickListener;
        if (loadEmpty == null) {
            return;
        }
        loadEmpty.setLoadEmptyOnClickListener(onClickListener);
    }

    @Override
    public final void setLoadFailureOnClickListener(OnClickListener onClickListener) {
        failureClickListener = onClickListener;
        if (loadFailure == null) {
            return;
        }
        loadFailure.setLoadFailureOnClickListener(onClickListener);
    }

    @Override
    public final void setNetExceptionOnClickListener(OnClickListener onClickListener) {
        netExceptionClickListener = onClickListener;
        if (netException == null) {
            return;
        }
        netException.setNetExceptionOnClickListener(onClickListener);
    }

    @Override
    public final void hideEmptyView() {
        if (loadEmpty != null) {
            loadEmpty.hideEmptyView();
        }
    }

    @Override
    public final void showEmptyView() {
        addEmptyView();
        hideNetExceptionView();
        hideLoadFailureView();
        hideCovers();
        setVisibility(VISIBLE);
    }

    @Override
    public final void hideLoadFailureView() {
        if (loadFailure != null) {
            loadFailure.hideLoadFailureView();
        }
    }

    @Override
    public final void showLoadFailureView() {
        addLoadFailureView();
        hideEmptyView();
        hideNetExceptionView();
        hideCovers();
        setVisibility(VISIBLE);
    }

    @Override
    public final void hideNetExceptionView() {
        if (netException != null) {
            netException.hideNetExceptionView();
        }
    }

    @Override
    public final void showNetExceptionView() {
        addNetExceptionView();
        hideEmptyView();
        hideLoadFailureView();
        hideCovers();
        setVisibility(VISIBLE);
    }


    @Override
    public final void showLoadCovers(int color) {
        if (loadCovers == null) {
            loadCovers = createCoversView();
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            addView(loadCovers.getCoversView(), params);
        }
        loadCovers.showLoadCovers(color);
        hideLoadFailureView();
        hideNetExceptionView();
        hideEmptyView();
        setVisibility(VISIBLE);
    }

    @Override
    public final View getCoversView() {
        return loadCovers.getCoversView();
    }

    @Override
    public final void hideCovers() {
        if (loadCovers != null) {
            loadCovers.hideCovers();
            // 移除掉背景遮罩，一般只会使用一次，可以减少内存
            removeView(loadCovers.getCoversView());
            loadCovers = null;
        }
    }

    @Override
    public void showAsDropDown(ViewGroup parent, int topMargin) {
        if (parent == null) {
            return;
        }
        setVisibility(View.VISIBLE);
        if (parent == getParent()) { // 已经添加过
            MarginLayoutParams oldParams = (MarginLayoutParams) getLayoutParams();
            int oldTopMargin = oldParams.topMargin;
            if (oldTopMargin == topMargin) {
                return;
            } else {
                oldParams.topMargin = topMargin;
                setLayoutParams(oldParams);
            }
        } else {
            // fix 设置 topMargin 偶尔不生效的bug
//            MarginLayoutParams newParams = new MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                newParams.topMargin = topMargin;
//            this.setLayoutParams(newParams);
            parent.addView(this);
            MarginLayoutParams newParams = (MarginLayoutParams) getLayoutParams();
            newParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            newParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            newParams.topMargin = topMargin;
            setLayoutParams(newParams);
        }
    }

    @Override
    public void show(ViewGroup parent) {
        showAsDropDown(parent, 0);
    }

    public void dismiss() {
        setVisibility(View.GONE);
        ViewParent parent = getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this);
        }
    }
}

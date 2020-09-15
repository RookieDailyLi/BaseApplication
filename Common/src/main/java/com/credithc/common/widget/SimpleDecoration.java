package com.credithc.common.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by lwj on 2018/11/17.
 * lwjfork@gmail.com
 */

public class SimpleDecoration extends RecyclerView.ItemDecoration {


    private SparseArray<ItemTypeDecoration> decorations = new SparseArray<>();

    public static final int DEFAULT_TYPE = 0;

    public SimpleDecoration(SparseArray<ItemTypeDecoration> decorations) {
        super();
        this.decorations = decorations;
    }

    public SimpleDecoration() {
        super();
    }


    public void addItemTypeDecoration(int viewType, ItemTypeDecoration decoration) {
        decorations.put(viewType, decoration);
    }

    public void addItemTypeDecoration(ItemTypeDecoration decoration) {
        decorations.put(DEFAULT_TYPE, decoration);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);
            RecyclerView.Adapter adapter = parent.getAdapter();
            int viewType = adapter.getItemViewType(position);
            int count = adapter.getItemCount();
            drawItemDivider(viewType, child, parent, c, position, count);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();
        int count = adapter.getItemCount();
        int position = parent.getChildAdapterPosition(view);
        int viewType = adapter.getItemViewType(position);
        setItemOffsets(viewType, parent, outRect, position, count);
    }

    private void setItemOffsets(int viewType, RecyclerView parent, Rect outRect, int position, int count) {

        ItemTypeDecoration itemTypeDecoration = decorations.get(viewType);
        if (itemTypeDecoration == null) {
            return;
        }
        itemTypeDecoration.setItemOffsets(parent, outRect, position, count);


    }

    private void drawItemDivider(int viewType, View child, RecyclerView parent, Canvas c, int position, int count) {
        ItemTypeDecoration itemTypeDecoration = decorations.get(viewType);
        if (itemTypeDecoration == null) {
            return;
        }
        itemTypeDecoration.drawItemDivider(child, parent, c, position, count);
    }


    public interface ItemTypeDecoration {
        void setItemOffsets(RecyclerView parent, Rect outRect, int position, int count);

        void drawItemDivider(View child, RecyclerView parent, Canvas c, int position, int count);

    }


}

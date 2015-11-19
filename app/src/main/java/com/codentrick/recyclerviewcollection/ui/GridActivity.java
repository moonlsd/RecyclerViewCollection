package com.codentrick.recyclerviewcollection.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codentrick.recyclerviewcollection.R;
import com.codentrick.recyclerviewcollection.model.DataProvider;

/**
 * Created by Diep Nguyen on 9/10/15.
 * Code and Trick
 * Copyright 2015, AwpSpace
 */
public class GridActivity extends RecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        GridAutofitLayoutManager layoutManager = new GridAutofitLayoutManager(this, 160);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new SimpleAdapter());
    }

    static class SimpleHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public SimpleHolder(View itemView) {
            super(itemView);
            this.mTextView = (TextView) itemView.findViewById(R.id.view_item_text);
        }
    }

    class SimpleAdapter extends RecyclerView.Adapter<SimpleHolder> {

        @Override
        public int getItemCount() {
            return DataProvider.JAVA_BOOKS.length;
        }

        @Override
        public SimpleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.view_item, viewGroup, false);
            return new SimpleHolder(view);
        }

        @Override
        public void onBindViewHolder(SimpleHolder simpleHolder, int i) {
            simpleHolder.mTextView.setText(DataProvider.JAVA_BOOKS[i]);
        }
    }

    public static class GridAutofitLayoutManager extends GridLayoutManager {

        private int mColumnWidth;
        private boolean mColumnWidthChanged = true;

        public GridAutofitLayoutManager(Context context, int columnWidth) {
            super(context, 1);
            setColumnWidth(checkedColumnWidth(context, columnWidth));
        }

        public GridAutofitLayoutManager(Context context, int columnWidth, int orientation, boolean reverseLayout) {
        /* Initially set spanCount to 1, will be changed automatically later. */
            super(context, 1, orientation, reverseLayout);
            setColumnWidth(checkedColumnWidth(context, columnWidth));
        }

        private int checkedColumnWidth(Context context, int columnWidth) {
            if (columnWidth <= 0) {
            /* Set default columnWidth value (48dp here). It is better to move this constant
            to static constant on top, but we need context to convert it to dp, so can't really
            do so. */
                columnWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48,
                        context.getResources().getDisplayMetrics());
            }
            return columnWidth;
        }

        public void setColumnWidth(int newColumnWidth) {
            if (newColumnWidth > 0 && newColumnWidth != mColumnWidth) {
                mColumnWidth = newColumnWidth;
                mColumnWidthChanged = true;
            }
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            if (mColumnWidthChanged && mColumnWidth > 0) {
                int totalSpace;
                if (getOrientation() == VERTICAL) {
                    totalSpace = getWidth() - getPaddingRight() - getPaddingLeft();
                } else {
                    totalSpace = getHeight() - getPaddingTop() - getPaddingBottom();
                }
                int spanCount = Math.max(1, totalSpace / mColumnWidth);
                setSpanCount(spanCount);
                mColumnWidthChanged = false;
            }
            super.onLayoutChildren(recycler, state);
        }
    }
}

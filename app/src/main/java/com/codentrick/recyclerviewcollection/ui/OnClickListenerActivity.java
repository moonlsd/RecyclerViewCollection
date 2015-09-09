package com.codentrick.recyclerviewcollection.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.codentrick.recyclerviewcollection.R;
import com.codentrick.recyclerviewcollection.model.DataProvider;

/**
 * Created by Diep Nguyen on 9/9/15.
 * Code and Trick
 * Copyright 2015, AwpSpace
 */
public class OnClickListenerActivity extends RecyclerViewActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocl);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pos = mRecyclerView.getChildAdapterPosition(v);
                    if (pos >= 0 && pos < getItemCount()) {
                        Toast.makeText(OnClickListenerActivity.this, DataProvider.JAVA_BOOKS[pos], Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return new SimpleHolder(view);
        }

        @Override
        public void onBindViewHolder(SimpleHolder simpleHolder, int i) {
            simpleHolder.mTextView.setText(DataProvider.JAVA_BOOKS[i]);
        }
    }
}

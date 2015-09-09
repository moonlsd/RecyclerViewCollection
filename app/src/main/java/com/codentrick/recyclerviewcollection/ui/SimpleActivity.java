package com.codentrick.recyclerviewcollection.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codentrick.recyclerviewcollection.R;
import com.codentrick.recyclerviewcollection.model.DataProvider;

/**
 * Created by Diep Nguyen on 9/9/15.
 * Code and Trick
 * Copyright 2015, AwpSpace
 */
public class SimpleActivity extends RecyclerViewActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
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
            return new SimpleHolder(view);
        }

        @Override
        public void onBindViewHolder(SimpleHolder simpleHolder, int i) {
            simpleHolder.mTextView.setText(DataProvider.JAVA_BOOKS[i]);
        }
    }
}

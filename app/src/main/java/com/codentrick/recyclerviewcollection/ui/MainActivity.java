package com.codentrick.recyclerviewcollection.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codentrick.recyclerviewcollection.R;

public class MainActivity extends AppCompatActivity {

    private static final String[] SAMPLES = { "Simple RecyclerView", "OnClickListener" };

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(new RecyclerViewCollectionAdapter());
    }

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int pos = mRecyclerView.getChildAdapterPosition(v);
            if (pos == 0) {
                startActivity(new Intent(MainActivity.this, SimpleActivity.class));
            } else if (pos == 1) {
                startActivity(new Intent(MainActivity.this, OnClickListenerActivity.class));
            }
        }
    };

    static class RecyclerViewCollectionHolder extends RecyclerView.ViewHolder {

        TextView mTextView;

        public RecyclerViewCollectionHolder(View itemView) {
            super(itemView);
            this.mTextView = (TextView) itemView.findViewById(R.id.view_item_text);
        }
    }

    class RecyclerViewCollectionAdapter extends RecyclerView.Adapter<RecyclerViewCollectionHolder> {

        @Override
        public int getItemCount() {
            return SAMPLES.length;
        }

        @Override
        public RecyclerViewCollectionHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.view_item, viewGroup, false);
            view.setOnClickListener(mOnClickListener);
            return new RecyclerViewCollectionHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerViewCollectionHolder recyclerViewCollectionHolder, int i) {
            recyclerViewCollectionHolder.mTextView.setText(SAMPLES[i]);
        }
    }
}

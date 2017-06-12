package com.example.android.testapp612;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Android on 6/12/2017.
 */

public class RecycleviewAdapater extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    // data is passed into the constructor
    public RecycleviewAdapater(Context context) {
        this.mInflater = LayoutInflater.from(context);

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView myTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.info_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

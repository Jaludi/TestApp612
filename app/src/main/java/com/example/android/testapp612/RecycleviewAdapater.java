package com.example.android.testapp612;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.testapp612.restPack.Contact;

import java.util.ArrayList;

/**
 * Created by Android on 6/12/2017.
 */

public class RecycleviewAdapater extends RecyclerView.Adapter<RecycleviewAdapater.ViewHolder> {
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ArrayList<Contact> contacts;

    // data is passed into the constructor
    public RecycleviewAdapater(Context context, ArrayList<Contact> list) {
        this.mInflater = LayoutInflater.from(context);
        this.contacts = list;



    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact current = contacts.get(position);
        String animal = current.getName();
        holder.myTextView.setText(animal);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
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
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());

        }
    }
    public String getItem(int id) {
        return contacts.get(id).getName();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
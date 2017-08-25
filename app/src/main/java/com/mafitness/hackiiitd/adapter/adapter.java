package com.mafitness.hackiiitd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.mafitness.hackiiitd.Infracture.data;

import java.util.ArrayList;

/**
 * Created by ravneet on 25/8/17.
 */

public class adapter extends RecyclerView.Adapter<adapter.adapterviewholder> {

    private Context context;
    private ArrayList<data> dataList;
    private OnItemClickListner onItemClickListner;

    public void setOnClickListener(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }

    public adapter(Context context , ArrayList<data> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    public void updatelist(ArrayList<data> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();

    }

    @Override
    public adapterviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(adapterviewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class adapterviewholder extends RecyclerView.ViewHolder{

        public adapterviewholder(View itemView) {
            super(itemView);
        }
    }



}

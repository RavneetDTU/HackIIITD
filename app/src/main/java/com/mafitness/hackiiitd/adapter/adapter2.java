package com.mafitness.hackiiitd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mafitness.hackiiitd.Infracture.data;
import com.mafitness.hackiiitd.R;

import java.util.ArrayList;

/**
 * Created by ravneet on 26/8/17.
 */

public class adapter2 extends RecyclerView.Adapter<adapter2.adapter2ViewHolder> {

    private Context context;
    private ArrayList<data> dataList;
    private OnItemClickListner onItemClickListner;

    public void setOnClickListener(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }

    public adapter2(Context context , ArrayList<data> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    public void updatelist(ArrayList<data> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();

    }

    @Override
    public adapter2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisview = li.inflate(R.layout.listiem,parent,false);

        return new adapter2.adapter2ViewHolder(thisview);
    }

    @Override
    public void onBindViewHolder(adapter2ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class adapter2ViewHolder extends RecyclerView.ViewHolder{

        public adapter2ViewHolder(View itemView) {
            super(itemView);
        }
    }
}

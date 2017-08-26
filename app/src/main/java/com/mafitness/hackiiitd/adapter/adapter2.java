package com.mafitness.hackiiitd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mafitness.hackiiitd.Infracture.data;
import com.mafitness.hackiiitd.Infracture.marketdata;
import com.mafitness.hackiiitd.R;

import java.util.ArrayList;

/**
 * Created by ravneet on 26/8/17.
 */

public class adapter2 extends RecyclerView.Adapter<adapter2.adapter2ViewHolder> {

    private Context context;
    private ArrayList<marketdata> dataList;
    private OnItemClickListner onItemClickListner;

    public void setOnClickListener(OnItemClickListner onItemClickListner){
        this.onItemClickListner = onItemClickListner;
    }

    public adapter2(Context context , ArrayList<marketdata> dataList){
        this.dataList = dataList;
        this.context = context;
    }

    public void updatelist(ArrayList<marketdata> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();

    }

    @Override
    public adapter2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisview = li.inflate(R.layout.marketplace_list_item,parent,false);

        return new adapter2.adapter2ViewHolder(thisview);
    }

    @Override
    public void onBindViewHolder(adapter2ViewHolder holder, int position) {
        final marketdata thisdata = dataList.get(position);

        holder.tvDetails.setText(thisdata.getDetails());
        holder.tvCity.setText(thisdata.getCity());
        holder.tvPhoneNum.setText(thisdata.getPhonenum());
        holder.tvIndName.setText(thisdata.getIndustryname());
        holder.thisView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListner != null){
                    onItemClickListner.OnItemClick(thisdata.getIndustryname(), thisdata.getPhonenum() ,thisdata.getDetails());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class adapter2ViewHolder extends RecyclerView.ViewHolder{

        TextView tvIndName, tvPhoneNum, tvCity, tvDetails;
        View thisView;

        public adapter2ViewHolder(View itemView) {
            super(itemView);
            thisView = itemView;
            tvCity = itemView.findViewById(R.id.tv_MA2_city);
            tvIndName = itemView.findViewById(R.id.tv_MA2_indName);
            tvPhoneNum = itemView.findViewById(R.id.tv_MA2_phonenum);
            tvDetails = itemView.findViewById(R.id.tv_MA2_details);
        }
    }
}

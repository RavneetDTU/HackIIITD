package com.mafitness.hackiiitd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.mafitness.hackiiitd.Infracture.data;
import com.mafitness.hackiiitd.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View thisview = li.inflate(R.layout.listiem,parent,false);

        return new adapterviewholder(thisview);
    }

    @Override
    public void onBindViewHolder(adapterviewholder holder, int position) {

        final data thisdata = dataList.get(position);

        holder.companyname.setText(thisdata.getIndustryname());
        holder.city.setText(thisdata.getLocation());
        Picasso.with(context).load(thisdata.getLogourl()).fit().centerCrop().into(holder.iv_logo);
        holder.thisview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListner != null){
                    onItemClickListner.OnItemClick(thisdata.getIndustryname(),thisdata.getOwner(),thisdata.getDescription());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class adapterviewholder extends RecyclerView.ViewHolder{

        TextView companyname,city;
        ImageView iv_logo;
        View thisview;

        public adapterviewholder(View itemView) {
            super(itemView);
            companyname = (TextView) itemView.findViewById(R.id.tv_industrryname);
            city = (TextView) itemView.findViewById(R.id.tv_city);
            iv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);

            thisview = itemView;
        }
    }



}

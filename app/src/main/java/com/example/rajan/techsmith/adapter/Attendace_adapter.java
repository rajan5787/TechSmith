package com.example.rajan.techsmith.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.rajan.techsmith.AttendaceList;
import com.example.rajan.techsmith.R;
import com.example.rajan.techsmith.database.Attendance;
import com.example.rajan.techsmith.httphelper.VolleyHelper;

import java.util.ArrayList;


/**
 * Created by rajan on 5/6/16.
 */
public class Attendace_adapter extends RecyclerView.Adapter<Attendace_adapter.viewholder> {
    Context context;
    ArrayList<Attendance> mArrayList;
    ImageLoader imageLoader;

    public Attendace_adapter(Context context) {
        this.context = context;
        imageLoader = VolleyHelper.getInstance(context).getImageLoader();

    }

    @Override
    public  Attendace_adapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_row,parent,false);
        return new Attendace_adapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(final Attendace_adapter.viewholder holder, int position) {

        final Attendance item = mArrayList.get(position);
        holder.student_ID.setText(item.student_ID+"");
        holder.student_IMAGE.setImageUrl(item.image,imageLoader);
        if(item.isPresent) {
            holder.student_isPresent.setVisibility(View.VISIBLE);
            holder.student_isCheckout.setVisibility(View.GONE);
            holder.parent.setBackgroundResource(R.color.cartcolor2);
        }
        if(item.isCheckout){
            holder.student_isPresent.setVisibility(View.GONE);
            holder.student_isCheckout.setVisibility(View.GONE);
            holder.parent.setBackgroundResource(R.color.cartcolor);

        }


        holder.student_isPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.student_isPresent.setVisibility(View.GONE);
                holder.student_isCheckout.setVisibility(View.VISIBLE);
                holder.parent.setBackgroundResource(R.color.cartcolor2);
                item.isPresent = true;
                item.save();
            }
        });

        holder.student_isCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.student_isCheckout.setVisibility(View.GONE);
                holder.parent.setBackgroundResource(R.color.cartcolor);
                item.isCheckout = true;
                item.save();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        TextView student_ID,student_total;
        NetworkImageView student_IMAGE;
        Button student_isPresent,student_isCheckout;
        LinearLayout parent;

        public viewholder(View itemView) {
            super(itemView);

            student_ID = (TextView)itemView.findViewById(R.id.student_ID);
            student_isPresent = (Button) itemView.findViewById(R.id.student_isPresent);
            student_isCheckout = (Button) itemView.findViewById(R.id.student_isCheckout);

            student_IMAGE = (NetworkImageView)itemView.findViewById(R.id.student_IMAGE);

            parent = (LinearLayout) itemView.findViewById(R.id.linear_layout);
        }
    }
    public void setdata(ArrayList<Attendance> arraylist) {
        this.mArrayList = arraylist ;
    }
}

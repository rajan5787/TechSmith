package com.example.rajan.techsmith.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.rajan.techsmith.R;
import com.example.rajan.techsmith.database.Attendance;

import java.util.ArrayList;


/**
 * Created by rajan on 5/6/16.
 */
public class Attendace_adapter extends RecyclerView.Adapter<Attendace_adapter.viewholder> {

    String e;
    Context context;
    ArrayList<Attendance> mArrayList;

    public Attendace_adapter(Context context) {
        this.context = context;

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
        holder.student_isPresent.setChecked(item.isPresent);

        holder.student_isPresent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isPresent==true) {
                    item.isPresent = false;
                    holder.student_isPresent.setChecked(item.isPresent);
                    item.save();
                }
                else {
                    item.isPresent = true;
                    holder.student_isPresent.setChecked(item.isPresent);
                    item.save();
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    class viewholder extends RecyclerView.ViewHolder{

        TextView student_ID;
        CheckBox student_isPresent;
        RelativeLayout parent;
        Button locker;
        public viewholder(View itemView) {
            super(itemView);

            student_ID = (TextView)itemView.findViewById(R.id.student_ID);
            student_isPresent = (CheckBox) itemView.findViewById(R.id.student_isPresent);
            parent = (RelativeLayout) itemView.findViewById(R.id.SLR_RelativeLayout);

        }
    }
    public void setdata(ArrayList<Attendance> arraylist) {
        this.mArrayList = arraylist ;
    }
}

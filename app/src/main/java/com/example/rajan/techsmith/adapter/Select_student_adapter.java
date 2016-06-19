package com.example.rajan.techsmith.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.rajan.techsmith.FeedImageView;
import com.example.rajan.techsmith.R;
import com.example.rajan.techsmith.app.AppController;
import com.example.rajan.techsmith.database.Attendance;
import com.example.rajan.techsmith.database.Studentlist;
import com.example.rajan.techsmith.httphelper.VolleyHelper;

import java.util.ArrayList;

import at.markushi.ui.CircleButton;

/**
 * Created by rajan on 16/6/16.
 */
public class Select_student_adapter extends RecyclerView.Adapter<Select_student_adapter.viewholder> {

    ArrayList<Studentlist> marraylist;
    ImageLoader imageLoader;
    Context context;

    public Select_student_adapter(Context context,ArrayList<Studentlist> marraylist) {
        this.marraylist = marraylist;
        this.context = context;
        imageLoader = AppController.getInstance().getImageLoader();

    }
    @Override
    public Select_student_adapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_student_list,parent,false);
        return new Select_student_adapter.viewholder(view);
    }

    @Override
    public void onBindViewHolder(final Select_student_adapter.viewholder holder, int position) {

        final Studentlist item = marraylist.get(position);
        holder.student_NAME.setText(item.student_name);
        holder.student_IMAGE.setImageUrl(item.student_image,imageLoader);
        if(item.student_select) {
            holder.student_isSelect.setVisibility(View.VISIBLE);
        }
        else{
            holder.student_isSelect.setVisibility(View.GONE);

        }

        holder.student_IMAGE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.student_select){
                    item.student_select = false;
                    holder.student_isSelect.setVisibility(View.GONE);
                    item.save();
                }
                else{
                    item.student_select = true;
                    holder.student_isSelect.setVisibility(View.VISIBLE);
                    item.save();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return marraylist.size();
    }

    class viewholder extends RecyclerView.ViewHolder{


        FeedImageView student_IMAGE;
        CircleButton student_isSelect;
        LinearLayout parent;
        TextView student_NAME;

        public viewholder(View itemView) {
            super(itemView);

            student_NAME = (TextView)itemView.findViewById(R.id.student_NAME);
            student_isSelect = (CircleButton) itemView.findViewById(R.id.student_select);
            student_IMAGE = (FeedImageView)itemView.findViewById(R.id.student_IMAGE);
            parent = (LinearLayout) itemView.findViewById(R.id.linear_layout);
        }
    }
    public void setdata(ArrayList<Studentlist> arraylist) {
        this.marraylist = arraylist ;
    }

}

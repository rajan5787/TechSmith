package com.example.rajan.techsmith;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.rajan.techsmith.adapter.Attendace_adapter;
import com.example.rajan.techsmith.adapter.Select_student_adapter;
import com.example.rajan.techsmith.database.Attendance;
import com.example.rajan.techsmith.database.Student_Activity;
import com.example.rajan.techsmith.database.Studentlist;
import com.orm.SugarContext;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.orm.util.NamingHelper;

import java.util.ArrayList;

public class Activities extends AppCompatActivity {
    RecyclerView mRecyclerView;
    Select_student_adapter mAdapter;
    ArrayList<Studentlist> marrylist;
    Button save,take_photo;
    ImageView imageview;
    EditText student_ACTIVITY,student_DISCRIPTION;

    String activity,discription,photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        save = (Button) findViewById(R.id.save);
        take_photo = (Button) findViewById(R.id.take_photo);
        imageview = (ImageView) findViewById(R.id.imageView);
        student_ACTIVITY = (EditText) findViewById(R.id.student_ACTIVITY);
        student_DISCRIPTION = (EditText) findViewById(R.id.student_DISCRIPTION);
        mRecyclerView = (RecyclerView) findViewById(R.id.student_list);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        marrylist = new ArrayList<>();

        mAdapter = new Select_student_adapter(this,marrylist);
        mRecyclerView.setAdapter(mAdapter);


        getData();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity = student_ACTIVITY.getText().toString().trim();
                discription = student_DISCRIPTION.getText().toString().trim();


                //add phoo location
                photo  =  "";

               ArrayList<Studentlist>  mArrayList = studentSelect();
                ArrayList<Long> ids = new ArrayList<Long>();
                for(int i=0;i<mArrayList.size();i++){
                    ids.add(mArrayList.get(i).student_ID);
                }
                Student_Activity mStudent_activity = new Student_Activity(activity,discription,ids);
                mStudent_activity.save();
            }
        });
    }




    private void getData() {
        marrylist= new ArrayList<>(Studentlist.listAll(Studentlist.class));
        System.out.println("rajanPpaliya"+marrylist.size());
        mAdapter.setdata(marrylist);

    }
public ArrayList<Studentlist> studentSelect(){
String s= "";
    ArrayList<Studentlist> marraylist = new ArrayList<>();
    marraylist = (ArrayList<Studentlist>) Select.from(Studentlist.class)
            .where(Condition.prop(NamingHelper.toSQLNameDefault("student_select")).eq(s))
            .list();
    return marraylist;
}

}

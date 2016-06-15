package com.example.rajan.techsmith;

import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rajan.techsmith.adapter.Attendace_adapter;
import com.example.rajan.techsmith.database.Attendance;
import com.orm.SugarContext;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.orm.util.NamingHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttendaceList extends AppCompatActivity  {


    public static int total_student = 0;
    RecyclerView mRecyclerView;
    Attendace_adapter mAdapter;
    ArrayList<Attendance> mArrayList;
   public static TextView student_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendacelist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Attendace");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        student_total = (TextView)findViewById(R.id.student_total);
        mArrayList = new ArrayList<Attendance>();

        SugarContext.init(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_studentlist);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new Attendace_adapter(this);
        mRecyclerView.setAdapter(mAdapter);
        getNewIDs();
        getData();
        student_total.setText(total_student+"/"+mArrayList.size()+"");


    }




    private void getNewIDs() {
        SharedPreferences settings = getSharedPreferences(Constants.USER_PREFERENCES, MODE_PRIVATE);
        if (settings.getBoolean(Constants.FIRST_TIME_PREF, true)) {
            Attendance.deleteAll(Attendance.class);
            for (long i = 201301001; i <= 201301100; i++) {
                Attendance attendance = new Attendance(i, false,"",false);
                attendance.save();

            }
            settings.edit().putBoolean(Constants.FIRST_TIME_PREF, false).commit();


        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_push) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void getData() {
        mArrayList= new ArrayList<>(Attendance.listAll(Attendance.class));
        mAdapter.setdata(mArrayList);

    }


}

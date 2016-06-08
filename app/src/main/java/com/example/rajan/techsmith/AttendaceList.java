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

import com.example.rajan.techsmith.adapter.Attendace_adapter;
import com.example.rajan.techsmith.database.Attendance;
import com.orm.SugarContext;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.orm.util.NamingHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AttendaceList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView mRecyclerView;
    Attendace_adapter mAdapter;
    HashMap<String, Integer> menuMap;
    ArrayList<Attendance> mArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendacelist);

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Attendace");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        SugarContext.init(this);
        getNewIDs();
        mArrayList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_studentlist);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter =new Attendace_adapter(this);
        mRecyclerView.setAdapter(mAdapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        Menu drawerMenu = navigationView.getMenu();
        Log.d("Menu", drawerMenu.size() + "");
        Log.d("Menu", "IDs Size: " + mArrayList.size() + " : Database Size: " + Attendance.count(Attendance.class));
        int range = 60;
        menuMap = new HashMap<>();
        for (int i = 0; i < mArrayList.size() / range; i++) {
            String title = mArrayList.get(i * range).student_ID + " -- " + mArrayList.get((i + 1) * range - 1).student_ID;
            drawerMenu.add(title);
            menuMap.put(title, i * range);
        }
        if (mArrayList.size() % range != 0) {
            int start = drawerMenu.size() * range;
            String title = mArrayList.get(start).student_ID + " -- " + mArrayList.get(mArrayList.size() - 1).student_ID;
            drawerMenu.add(title);
            menuMap.put(title, start);
        }
        Log.d("Menu", drawerMenu.size() + "");
        MenuItem mi = drawerMenu.getItem(drawerMenu.size() - 1);
        mi.setTitle(mi.getTitle());


    }





    private void getNewIDs() {
        SharedPreferences settings = getSharedPreferences(Constants.USER_PREFERENCES, MODE_PRIVATE);
        if (settings.getBoolean(Constants.FIRST_TIME_PREF, true)) {
            Attendance.deleteAll(Attendance.class);
            for (long i = 201301001; i <= 201301100; i++) {
                Attendance attendance = new Attendance(i, false);
                attendance.save();

            }
            settings.edit().putBoolean(Constants.FIRST_TIME_PREF, false).commit();


        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.attendacelist_menu, menu);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        int position = menuMap.get(title);
        mRecyclerView.scrollToPosition(position);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void getData(String e) {
        mArrayList= (ArrayList<Attendance>) Select.from(Attendance.class)
                .where(Condition.prop(NamingHelper.toSQLNameDefault("event")).eq(e))
                .list();
        mAdapter.setdata(mArrayList);

    }


}

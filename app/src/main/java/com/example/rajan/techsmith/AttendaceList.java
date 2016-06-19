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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajan.techsmith.adapter.Attendace_adapter;
import com.example.rajan.techsmith.database.Attendance;
import com.orm.SugarContext;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.orm.util.NamingHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_studentlist);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new Attendace_adapter(this);
        mRecyclerView.setAdapter(mAdapter);
        getData();
        student_total.setText(total_student+"/"+mArrayList.size()+"");


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_push) {
            //   pushdata();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private void getData() {
        mArrayList= new ArrayList<>(Attendance.listAll(Attendance.class));
        mAdapter.setdata(mArrayList);

    }



    public void pushdata(){

        final ArrayList<Attendance> mArrayList= new ArrayList<>(Attendance.listAll(Attendance.class));

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.SENT_ATTENDACE_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                JSONArray jsonArray = new JSONArray();
                final JSONObject finalobject = new JSONObject();
                Map<String, String> map = new HashMap<>();
                for (int i = 0; i < mArrayList.size(); i++) {
                    JSONObject object = new JSONObject();
                    try {
                        object.put(Constants.STUDENT_ID, mArrayList.get(i).student_ID);
                        object.put(Constants.STUDENT_NAME, mArrayList.get(i).student_name);
                        object.put(Constants.STUDENT_ISPRESENT, mArrayList.get(i).isPresent);
                        object.put(Constants.STUDENT_ISCHECKOUT, mArrayList.get(i).isCheckout);
                        jsonArray.put(object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                try {
                    finalobject.put(Constants.STUDENT_ATTENDACELIST_ARRAY, jsonArray);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                //teacher id
                // map.put(Constants.KEY_PASSWORD,id);


                map.put(Constants.STUDENT_ATTENDACELIST_FINALOBJECT, finalobject.toString());
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }

}

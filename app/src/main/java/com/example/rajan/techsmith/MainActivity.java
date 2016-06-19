package com.example.rajan.techsmith;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.rajan.techsmith.database.Attendance;
import com.example.rajan.techsmith.database.Student_Activity;
import com.example.rajan.techsmith.database.Studentlist;
import com.orm.SugarContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
Button b,c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);

        getNewIDs();
        b = (Button)findViewById(R.id.button_attendace);
        c = (Button)findViewById(R.id.button_activities);
        d = (Button) findViewById(R.id.button_share);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(),AttendaceList.class);
                startActivity(i);
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Activities.class);
                startActivity(i);
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  sendDatatoServer();
            }
        });
    }



    private void getNewIDs() {
        SharedPreferences settings = getSharedPreferences(Constants.USER_PREFERENCES, MODE_PRIVATE);
        if (settings.getBoolean(Constants.FIRST_TIME_PREF, true)) {
            Attendance.deleteAll(Attendance.class);
            Studentlist.deleteAll(Studentlist.class);
            Student_Activity.deleteAll(Student_Activity.class);
       //     getData();
       //     getDataStudentList()
            for (long i = 1; i <=50; i++) {
                Attendance attendance = new Attendance(i,"rajan"+i+"", false,"",false);
                attendance.save();

            }
            for (long i = 1; i <= 50; i++) {
                Studentlist list = new Studentlist(i,"rajan"+i+"","",false);
                list.save();

            }

            settings.edit().putBoolean(Constants.FIRST_TIME_PREF, false).commit();


        }
    }









      public void getData(){
          Attendance.deleteAll(Attendance.class);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.STUDENT_LIST,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray array = response.getJSONArray("");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                String name = obj.getString("");
                                String image = obj.getString("");

                                Attendance attendance = new Attendance(i, name, false, image , false);
                                attendance.save();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Please conect internet to get data",Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }



    public void getDataStudentList(){
        Studentlist.deleteAll(Attendance.class);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.STUDENT_LIST,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray array = response.getJSONArray("");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i);
                                int id = obj.getInt("");
                                String name = obj.getString("");
                                String image = obj.getString("");

                                Studentlist studentlist = new Studentlist(id, name, image , false);
                                studentlist.save();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Please conect internet to get data",Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }



   /* public void sendDatatoServer(){

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

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


                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(objectRequest);

    }*/
}

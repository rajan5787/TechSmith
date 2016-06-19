package com.example.rajan.techsmith;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.common.base.MoreObjects;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {


    EditText username,password;
    Button login_button;
    TextView textview_forgotpassword;
    String user = "", pwd = "";
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login_button = (Button) findViewById(R.id.button_login);
        textview_forgotpassword = (TextView) findViewById(R.id.textview_forgotpass);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        toolbar.setTitle("Login");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
             //   login();
            }
        });
    }

    public boolean login(){
        /*
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.LOGIN_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.trim().equals("success")) {
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);

                                    startActivity(i);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Unsuccessfull", Toast.LENGTH_SHORT).show();
                                }
                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "some error", Toast.LENGTH_SHORT).show();

                            }
                        }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String,String>();
                        map.put(Constants.KEY_USERNAME,user);
                        map.put(Constants.KEY_PASSWORD,pwd);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
                */
return true;
    }
}

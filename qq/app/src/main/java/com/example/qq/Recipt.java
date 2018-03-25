package com.example.qq;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Recipt extends AppCompatActivity {
    private  Boolean isConnect = true;
    private RecyclerView rv;
    private myAdapter3 myAdapter = new myAdapter3();
    private BottomNavigationView mNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipt);
        View v = findViewById(R.id.a123);
        v.getBackground().setAlpha(150);
        isConnect = isOnline();
        rv = findViewById(R.id.RecyclerView_r);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        if (isConnect) {
            initDb_net();
        }
        else {
            initDb();
        }
    }
    public void initDb() {
        myDataBaseHelper mDBH = new myDataBaseHelper(this);
        Cursor cursor = mDBH.getAll_r();
        for (int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            this.myAdapter.add(new cellData_3(cursor.getString(0),cursor.getString(1)));
        }
        mNavigationView =  findViewById(R.id.bottomNavigationView);
        mNavigationView.setSelectedItemId(R.id.Recipt);
        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getTitle().toString().equals("message")) {
                            Intent intent = new Intent(Recipt.this,MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.left_to_right,R.anim.left_to_right_end);
                            finish();
                        }
                        else if (item.getTitle().toString().equals("me")) {
                            Intent intent = new Intent(Recipt.this,QR_code.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.left_to_right,R.anim.left_to_right_end);
                            finish();
                        }
                        else if (item.getTitle().toString().equals("call")) {
                            Intent intent = new Intent(Recipt.this,piechart.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.left_to_right,R.anim.left_to_right_end);
                            finish();
                        }
                        else;
                        return true;
                    }
                }
        );
        cursor.close();
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    public void initDb_net() {
        myAdapter.emptyData3();
        RequestQueue mQueue = Volley.newRequestQueue(this);
        String myURL = "http://makerthon.nthuee.org/2018/OnlineAccounting/android_fetch_receipt.php";
        mNavigationView =  findViewById(R.id.bottomNavigationView);
        mNavigationView.setSelectedItemId(R.id.Recipt);
        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getTitle().toString().equals("message")) {
                            Intent intent = new Intent(Recipt.this,MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.left_to_right,R.anim.left_to_right_end);
                            finish();
                        }
                        else if (item.getTitle().toString().equals("me")) {
                            Intent intent = new Intent(Recipt.this,QR_code.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.left_to_right,R.anim.left_to_right_end);
                            finish();
                        }
                        else if (item.getTitle().toString().equals("call")) {
                            Intent intent = new Intent(Recipt.this,piechart.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.left_to_right,R.anim.left_to_right_end);
                            finish();
                        }
                        else;
                        return true;
                    }
                }
        );
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                for (int i =0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        myAdapter.add(new cellData_3(jsonObject.getString("YMD"),jsonObject.getString("ReceiptID")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                myAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log", error);
            }
        });
        mQueue.add(jsonArrayRequest);
        myAdapter.notifyDataSetChanged();
    }
}

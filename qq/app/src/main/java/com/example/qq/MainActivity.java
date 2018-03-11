package com.example.qq;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private CalendarView cdv;
    private Boolean isConnect = true;
    private BottomNavigationView mNavigationView;
    private MyAdapter myAdapter = new MyAdapter();
    public String date = "01/02";
    public String month = "12";
    public String YMD = "D";
    public String day_d = "d";
    static String date_intent="Prashant";
    static String month_intent="Prshant";
    static String year_intent="Prssshant";
    static String day_intent = "Prssshddant";
    private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
    private SimpleDateFormat sdf3 = new SimpleDateFormat("YYYY-MM-DD");
    private SimpleDateFormat sdf4 = new SimpleDateFormat("DD");
    private static final int RequestCode=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Intent intent_alarm =new Intent();
        intent_alarm.setAction("getAlarm");
        isConnect = isOnline();
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent_alarm, 0);
        manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        mNavigationView =  findViewById(R.id.bottomNavigationView);
        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getTitle().toString().equals("call")) {
                            Intent intent = new Intent(MainActivity.this,piechart.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (item.getTitle().toString().equals("me")) {
                            Intent intent = new Intent(MainActivity.this,QR_code.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (item.getTitle().toString().equals("recipt")) {
                            Intent intent = new Intent(MainActivity.this,Recipt.class);
                            startActivity(intent);
                            finish();
                        }
                        else;
                        return true;
                    }
                }
        );
        rv = findViewById(R.id.a);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myAdapter);
        cdv = findViewById(R.id.cdv);
        cdv.bringToFront();
        date = sdf.format(new java.util.Date());
        month = sdf2.format(new java.util.Date());
        YMD = sdf3.format(new java.util.Date());
        day_d = sdf4.format(new java.util.Date());
        cdv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String m,day;

                if (i1+1<10) {
                    m = "0"+Integer.toString(i1+1);
                }
                else m = Integer.toString(i1+1);
                if (i2<10) {
                    day = "0"+Integer.toString(i2);
                }
                else day = Integer.toString(i2);
                date =  m + "-" + day;
                day_d = day;
                month = m;
                YMD = Integer.toString(i) + "-" + m + "-" +day;
                if (isConnect) {
                    changeDate_net(date);
                }
                else{
                    changeDate(date);
                }

            }

        });
        if (isConnect) {
            initDb_net(date);
        }
        else {
            initDb();
        }
        sendAlarm();
    }
    public void sendAlarm() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Intent intent_alarm =new Intent(this,AlarmReceiver2.class);
        intent_alarm.setAction("getAlarm");
        Log.d("sees", "sendAlarm: ");
        PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent_alarm, 0);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 *60, sender);
    }
    public void initDb() {
        myDataBaseHelper mDBH = new myDataBaseHelper(this);
        Cursor cursor = mDBH.getDateRow(date);
        for (int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            this.myAdapter.add(new cellData(cursor.getInt(3),cursor.getString(1),cursor.getString(2)));
        }
        cursor.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_item, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.action_add) {
            Intent intent = new Intent(this,ADD.class);
            intent.putExtra(date_intent,date);
            intent.putExtra(month_intent,month);
            intent.putExtra(year_intent,YMD);
            intent.putExtra(day_intent,day_d);
            this.startActivityForResult(intent,RequestCode);
        }
        else;
        return super.onOptionsItemSelected(item);
    }
    public void changeDate(String date_se) {
        this.myAdapter.emptyData2();
        myDataBaseHelper mDBH = new myDataBaseHelper(this);
        Cursor cursor = mDBH.getDateRow(date_se);
        for (int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            this.myAdapter.add(new cellData(cursor.getInt(3),cursor.getString(1),cursor.getString(2)));
        }
        this.myAdapter.notifyDataSetChanged();
        cursor.close();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == requestCode) {
            if (resultCode == RESULT_OK) {
                if (isConnect) {
                    changeDate_net(date);
                }
                else{
                    changeDate(date);
                }
                this.myAdapter.notifyDataSetChanged();
            }
        }
    }
    public void initDb_net(final String date_2) {
        myAdapter.emptyData2();
        RequestQueue mQueue = Volley.newRequestQueue(this);
        String myURL = "http://makerthon.nthuee.org/2018/OnlineAccounting/android_fetch_db.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                for (int i =0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        if (jsonObject.getString("MD").equals(date_2)) {
                            myAdapter.add(new cellData(Integer.parseInt(jsonObject.getString("Money")),
                                    jsonObject.getString("Type"), jsonObject.getString("Remark")));
                        }
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
    public void changeDate_net(final String date2) {
        myAdapter.emptyData2();
        RequestQueue mQueue = Volley.newRequestQueue(this);
        String myURL = "http://makerthon.nthuee.org/2018/OnlineAccounting/android_fetch_db.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                System.out.println(response);
                for (int i =0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        if (jsonObject.getString("MD").equals(date2)) {
                            myAdapter.add(new cellData(Integer.parseInt(jsonObject.getString("Money")),
                                    jsonObject.getString("Type"), jsonObject.getString("Remark")));
                        }
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
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}

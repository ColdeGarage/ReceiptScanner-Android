package com.example.qq;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
public class piechart extends AppCompatActivity {
    private RecyclerView rv;
    private  Intent intent;
    private MyAdapter2 myAdapter2 = new MyAdapter2();
    private int[] yData =new int[]{0,0,0,0,0,0,0,0};
    private String[] xData = new String[]{"FOOD","TRANSPORT","CLOTH","TEACH","ENTERMAIN","DAILY","DOCTOR","BILL"};
    private cellData_2[]  cd = new cellData_2[]{new cellData_2(1,"飲食")};
    private BottomNavigationView mNavigationView;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        View v = findViewById(R.id.gggg);
        rv = findViewById(R.id.rv_pc);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myAdapter2);
        intent = getIntent();
        mNavigationView =  findViewById(R.id.bottomNavigationView);
        mNavigationView.setSelectedItemId(R.id.pie);
        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getTitle().toString().equals("message")) {
                            Intent intent = new Intent(piechart.this,MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.left_to_right,R.anim.left_to_right_end);
                            finish();
                        }
                        else if (item.getTitle().toString().equals("me")) {
                            Intent intent = new Intent(piechart.this,QR_code.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.right_to_left,R.anim.right_to_left_end);
                            finish();

                        }
                        else if (item.getTitle().toString().equals("recipt")) {
                            Intent intent = new Intent(piechart.this,Recipt.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.right_to_left,R.anim.right_to_left_end);
                            finish();
                        }
                        else;
                        return true;
                    }
                }
        );
        pieChart = (PieChart) findViewById(R.id.PieChart);
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Super Cool Chart");
        pieChart.setCenterTextSize(10);
        //initDb();
        initDb_net();
        for (int i=0; i<yData.length; i++) {
            System.out.println(yData[i]);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                addDataSet();
            }
        }, 1000);
        //addDataSet();
    }
    public void initDb() {

        myDataBaseHelper mDBH = new myDataBaseHelper(this);
        Cursor cursor = mDBH.getMonth("01");
        Integer total[] = new Integer[]{0,0};
        for (int i=0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            if (cursor.getString(1).equals("FOOD")) {
                total[0]+=cursor.getInt(3);
            }
            else {
                total[1]+=cursor.getInt(3);
            }
        }
        cursor.close();
        for (int i=0; i<total.length; i++) {
            yDataAdd(total[i]);
            System.out.println(yData[i]);
            add_cd(new cellData_2(total[i],xData[i]));
        }
        Arrays.sort(cd);
        for (int i=total.length-1; i>-1; i--) {
            this.myAdapter2.add(cd[i]);
        }
    }
    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yData.length; i++){
            yEntrys.add(new PieEntry(yData[i] , i));
        }

        for(int i = 1; i < xData.length; i++){
            xEntrys.add(xData[i]);
        }
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Employee Sales");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#F44336"));
        colors.add(Color.parseColor("#00BCD4"));
        colors.add(Color.parseColor("#03A9F4"));
        colors.add(Color.parseColor("#009688"));
        colors.add(Color.parseColor("#4CAF50"));
        colors.add(Color.parseColor("#FF9800"));
        colors.add(Color.parseColor("#FFC107"));
        colors.add(Color.parseColor("#9C27B0"));
        pieDataSet.setColors(colors);

        //add legend to chart
        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
    private  int idxY=-1;
    public void yDataAdd(int a) {
            idxY++;
            if (idxY==0) {
                yData[0] = a;
            }
            else if (idxY>=yData.length) {
                int[] newData = new int[yData.length+1];
                System.arraycopy(yData,0,newData,0,yData.length);
                yData = newData;
            }
            yData[idxY] = a;
    }

    int idx_cd=-1;
    public void add_cd(cellData_2 obj) {
        idx_cd++;
        if (idx_cd==0) {
            cd[0].setItem(obj.getItem());
            cd[0].setTotal(obj.getTotal());
        }
        else if (idx_cd>=cd.length) {
            cellData_2[] newData=new cellData_2[cd.length+1];
            System.arraycopy(cd,0,newData,0,cd.length);
            cd = newData;
        }
        cd[idx_cd] = obj;
    }
    public void initDb_net() {
        myAdapter2.emptyData2();
        RequestQueue mQueue = Volley.newRequestQueue(this);
        String myURL = "http://makerthon.nthuee.org/2018/OnlineAccounting/android_fetch_db.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(myURL, new Response.Listener<JSONArray>() {
            Integer total[] = new Integer[]{0,0,0,0,0,0,0,0};
            @Override
            public void onResponse(JSONArray response) {
                //System.out.println(response);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        if (jsonObject.getString("Month").equals("01") && jsonObject.getString("Type").equals("FOOD")) {
                            total[0] += Integer.parseInt(jsonObject.getString("Money"));
                        }
                        else if (jsonObject.getString("Month").equals("01") && jsonObject.getString("Type").equals("TRANSPORT")) {
                            total[1] += Integer.parseInt(jsonObject.getString("Money"));
                        }
                        else if (jsonObject.getString("Month").equals("01") && jsonObject.getString("Type").equals("CLOTH")) {
                            total[2] += Integer.parseInt(jsonObject.getString("Money"));
                        }
                        else if (jsonObject.getString("Month").equals("01") && jsonObject.getString("Type").equals("TEACH")) {
                            total[3] += Integer.parseInt(jsonObject.getString("Money"));
                        }
                        else if (jsonObject.getString("Month").equals("01") && jsonObject.getString("Type").equals("ENTERTAIN")) {
                            total[4] += Integer.parseInt(jsonObject.getString("Money"));
                        }
                        else if (jsonObject.getString("Month").equals("01") && jsonObject.getString("Type").equals("DAILY")) {
                            total[5] += Integer.parseInt(jsonObject.getString("Money"));
                        }
                        else if (jsonObject.getString("Month").equals("01") && jsonObject.getString("Type").equals("DOCTOR")) {
                            total[6] += Integer.parseInt(jsonObject.getString("Money"));
                        }
                        else if (jsonObject.getString("Month").equals("01") && jsonObject.getString("Type").equals("BILL")) {
                            total[7] += Integer.parseInt(jsonObject.getString("Money"));
                        }
                        else;
                    } catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < total.length; j++) {
                    yDataAdd(total[j]);
                    add_cd(new cellData_2(total[j], xData[j]));
                }
                Arrays.sort(cd);
                for (int j = total.length - 1; j > -1; j--) {
                    myAdapter2.add(cd[j]);
                }
                myAdapter2.notifyDataSetChanged();
            }
        },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("Volley Log", error);
                }
            });
        mQueue.add(jsonArrayRequest);
    }
}


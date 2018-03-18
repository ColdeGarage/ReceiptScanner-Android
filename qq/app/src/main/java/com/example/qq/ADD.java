package com.example.qq;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ADD extends AppCompatActivity {
    private Button save;
    private Button cancel;
    private TextView date;
    private EditText name;
    private EditText clas;
    private EditText money;
    private Intent intent;
    private String date_s;
    private String month;
    private String YMD;
    private String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        save = findViewById(R.id.save_add_bt);
        cancel = findViewById(R.id.cancel_add_bt);
        date = findViewById(R.id.date_add_tv);
        name = findViewById(R.id.name_add_et);
        clas = findViewById(R.id.class_add_et);
        money = findViewById(R.id.money_add_et);
        clas.setClickable(true);
        clas.setFocusable(false);
        View v = findViewById(R.id.addd);
        v.getBackground().setAlpha(210);
        intent = getIntent();
        date_s = intent.getStringExtra(MainActivity.date_intent);
        month = intent.getStringExtra(MainActivity.month_intent);
        YMD = intent.getStringExtra(MainActivity.year_intent);
        day = intent.getStringExtra(MainActivity.day_intent);
        date.setText(date_s);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDataBaseHelper myDBH = new myDataBaseHelper(ADD.this);
                SQLiteDatabase db=myDBH.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("item",clas.getText().toString());
                values.put("name",name.getText().toString());
                values.put("amount",Integer.parseInt(money.getText().toString()));
                values.put("date",date_s);
                values.put("month",month);
                db.insert("pay", null,values);
                new  uploadData(YMD,month,date_s,day,money.getText().toString(),clas.getText().toString(),name.getText().toString(),YMD).execute();
                //Toast.makeText(ADD.this, "這是一個Toast......", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK);
                ADD.this.finish();
            }
        });
        clas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ADD.this,class_select.class);
                startActivityForResult(intent,3);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ADD.this.finish();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                if ((data.getIntExtra("11",0)==1)) {
                    clas.setText("FOOD");
                }
               else  if ((data.getIntExtra("11",0)==2)) {
                    clas.setText("TRANSPORT");
                }
                else if ((data.getIntExtra("11",0)==3)) {
                    System.out.println("inininin");
                    clas.setText("CLOTH",TextView.BufferType.EDITABLE);
                }
                else if ((data.getIntExtra("11",0)==4)) {
                    clas.setText("TEACH");
                }
                else if ((data.getIntExtra("11",0)==5)) {
                    clas.setText("ENTERTAIN");
                }
                else if ((data.getIntExtra("11",0)==6)) {
                    clas.setText("DAILY");
                }
                else if ((data.getIntExtra("11",0)==7)) {
                    clas.setText("DOCTOR");
                }
                else if ((data.getIntExtra("11",0)==8)) {
                    clas.setText("BILL");
                }
                else;
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                Bundle MBuddle = data.getExtras();
                String Mmessage = MBuddle .getString("ssd");
                name.setText(Mmessage);
                }
            }
        }

}

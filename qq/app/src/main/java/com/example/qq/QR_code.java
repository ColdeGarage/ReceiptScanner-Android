package com.example.qq;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.ActivityCompat;
import static android.Manifest.permission.*;
import static java.lang.Long.valueOf;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import static android.Manifest.permission.CAMERA;

public class QR_code extends AppCompatActivity {
    private static final int REQUIRE_CAMERA = 0;
    private TextView scan_content;
    private Button scan_btn;
    private TextView scan_format;
    private Activity mainactivity;
    private TextView scan_date;
    private int i = 0;
    private int total=0;
    private int num=0;
    private Button ok;
    private EditText item_detail;
    private EditText item;
    private String[] mergeitem={"0"};
    private String mergeitemfull;
    private Intent intent;
    private String YMD;
    private String month;
    private String Y;
    private String code;
    private String day;
    private BottomNavigationView mNavigationView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        View v = findViewById(R.id.QRcode);
        v.getBackground().setAlpha(200);
        intent = getIntent();
        mNavigationView =  findViewById(R.id.bottomNavigationView);
        mNavigationView.setSelectedItemId(R.id.qr3);
        mNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (item.getTitle().toString().equals("message")) {
                            Intent intent = new Intent(QR_code.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (item.getTitle().toString().equals("call")) {
                            Intent intent = new Intent(QR_code.this,piechart.class);
                            startActivity(intent);
                            finish();
                        }
                        else if (item.getTitle().toString().equals("recipt")) {
                            Intent intent = new Intent(QR_code.this,Recipt.class);
                            startActivity(intent);
                            finish();
                        }
                        else;
                        return true;
                    }
                }
        );
        init_view();

        int permission = ActivityCompat.checkSelfPermission(mainactivity, Manifest.permission.CAMERA);
        if (permission != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(mainactivity, new String[]{CAMERA}, REQUIRE_CAMERA);
        }
        scan_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                i = 0;
                shot();

            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDataBaseHelper myDBH = new myDataBaseHelper(QR_code.this);
                SQLiteDatabase db=myDBH.getWritableDatabase();
                ContentValues values = new ContentValues();
                ContentValues values_r = new ContentValues();
                String MD;
                MD = scan_date.getText().toString().substring(3,5) + "-" + scan_date.getText().toString().substring(5,7);
                day = scan_date.getText().toString().substring(5,7);
                System.out.println(MD);
                values.put("item",item.getText().toString());
                values.put("name",item_detail.getText().toString());
                values.put("amount",Integer.parseInt(scan_content.getText().toString()));
                values.put("date",MD);
                values.put("month",scan_date.getText().toString().substring(3,5));
                db.insert("pay", null,values);
                YMD = scan_date.getText().toString().substring(0,3) + "-" + MD;
                values_r.put("YMD",scan_date.getText().toString().substring(0,3) + "-" + MD);
                values_r.put("month",scan_date.getText().toString().substring(3,5));
                month = scan_date.getText().toString().substring(3,5);
                code = scan_format.getText().toString();
                Y = scan_date.getText().toString().substring(0,3);
                values_r.put("code",scan_format.getText().toString());
                values_r.put("amount",Integer.parseInt(scan_content.getText().toString()));
                long result = db.insert("scan",null,values_r);
                new uploadData(YMD,month,MD,day,scan_content.getText().toString(),item.getText().toString(),item_detail.getText().toString(),YMD).execute();
                new uploadData_r(YMD,month,Y,code).execute();
                myDataBaseHelper mDBH = new myDataBaseHelper(QR_code.this);
                if (result != -1){
                    scan_format.setText("");
                    scan_date.setText("");
                    item_detail.setText("");
                    item.setText("");
                    scan_content.setText("");
                }
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                if (data.getIntExtra("11",0)==1) {
                    item.setText("FOOD");
                }
                else  if (data.getIntExtra("11",0)==2) {
                    item.setText("TRANSPORT");
                }
                else if (data.getIntExtra("11",0)==3) {
                    item.setText("CLOTH");
                }
                else if (data.getIntExtra("11",0)==4) {
                    item.setText("TEACH");
                }
                else if (data.getIntExtra("11",0)==5) {
                    item.setText("ENTERTAIN");
                }
                else if (data.getIntExtra("11",0)==6) {
                    item.setText("DAILY");
                }
                else if (data.getIntExtra("11",0)==7) {
                    item.setText("DOCTOR");
                }
                else if (data.getIntExtra("11",0)==8) {
                    item.setText("BILL");
                }
                else;
            }
        }
        if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                Bundle MBuddle = intent.getExtras();
                String Mmessage = MBuddle .getString("ssd");
                item_detail.setText(Mmessage);
            }
        }
        if (scanningResult != null) {
            System.out.println(requestCode+"   dddd   ");
            String[] itemname = {""};
            String[] itemmoney = {""};
            int u = 0;
            if (i == 0) {
                String scanContent = scanningResult.getContents();
                String number = scanContent.substring(0, 10);
                String date = scanContent.substring(10, 17);
                String price = scanContent.substring(30, 37);
                int finallyprice = Integer.parseInt(price, 16);
                String finallyprices = String.valueOf(finallyprice);
                scan_content.setText(finallyprices);
                String[] item = scanContent.split(":");
                total = Integer.parseInt(item[2]);
                num=total;
                scan_date.setText(date);
                scan_format.setText(number);
                int pricelength = item.length;
                String type = item[4];
                int typehey = Integer.valueOf(type);
                int price1 = 0;
                int amount = 0;
/**
                if (typehey == 0) {
                    for (int j = 0; j + 7 < pricelength; j = j + 3) {

                        amount = Integer.valueOf(item[j + 6]);
                        price1 = Integer.valueOf(item[j + 7]);
                        itemname[u] = item[j + 5];
                        itemname[u] = itemname[u].concat(" ");
                        itemmoney[u] = String.valueOf(amount * price1);
                        mergeitem[u]=(itemname[u]+itemmoney[u]).concat("\n");
                        mergeitemfull=mergeitem[u]+mergeitemfull;
                        u++;
                        total=total-1;

                    }
                }
                if (typehey == 1) {

                    for (int k = 0; k + 7 < pricelength; k = k + 3) {
                        int amount1 = Integer.valueOf(item[k + 6]);
                        if (k + 7 == pricelength - 1) {
                            String[] finallyget = item[7].split(" ");
                            price1 = Integer.valueOf(finallyget[0]);
                        } else {
                            price1 = Integer.valueOf(item[k + 7]);
                        }
                        itemname[u] = item[k + 5];
                        itemname[u] = itemname[u].concat(" ");
                        itemmoney[u] = String.valueOf(amount1 * price1);
                        mergeitem[u]=itemname[u]+itemmoney[u].concat("\n");
                        mergeitemfull=mergeitemfull+mergeitem[u];
                        u++;
                        total=total-1;
                    }
                }**/
            } else if (i == 1) {
/**                String scanContent = scanningResult.getContents();
                String[] item = scanContent.split(":");
                int price2length = item.length;
                if (price2length < 3) {
                    ;
                } else {
                    for (int h = 0; h < total; h ++) {

                        int amount2 = Integer.valueOf(item[1 + h]);

                        int price2 = Integer.valueOf(item[2 + h]);
                        itemname[u] = itemname[h];
                        itemname[u] = itemname[u].concat(" ");
                        itemmoney[u] = String.valueOf(amount2 * price2).concat("\n");
                        mergeitem[u]=itemname[u]+itemmoney[u];
                        mergeitemfull=mergeitemfull+mergeitem[u];
                        u++;
                        total=total-1;
                    }
                }//item_detail.setText(mergeitemfull);**/
            }
        }
        else
        {
            //Toast.makeText(getApplicationContext(), "nothing", Toast.LENGTH_SHORT).show();
        }
    }

    private void init_view()
    {
        this.item = findViewById(R.id.item);
        this.scan_content =  findViewById(R.id.scan_price);
        this.scan_format =  findViewById(R.id.scan_number);
        this.mainactivity = this;
        this.scan_date =  findViewById(R.id.scan_date);
        this.scan_btn = (Button) findViewById(R.id.scan_btn);
        this.ok = (Button) findViewById(R.id.ok);
        this.item_detail = (EditText) findViewById(R.id.item_detail);
        item.setClickable(true);
        item.setFocusable(false);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QR_code.this,class_select.class);
                startActivityForResult(intent,4);
            }
        });
    }

    public void shot()
    {
        IntentIntegrator scanIntegrator = new IntentIntegrator(mainactivity);
        scanIntegrator.setCameraId(0);
        scanIntegrator.initiateScan(IntentIntegrator.QR_CODE_TYPES);
    }

}

package com.example.qq;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class uploadData_r extends AsyncTask {
    private String YMD,Month,Y,code;
    public uploadData_r(String YMD,String Month,String Y,String code) {
        this.Y = Y;
        this.Month = Month;
        this.code = code;
        this.YMD = YMD;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try{
            System.out.println(Y+"       "+Month+"       "+code+"     "+YMD+"          ");
            String link="http://makerthon.nthuee.org/2018/OnlineAccounting/android_insert_receipt.php";
            String data  = URLEncoder.encode("YMD", "UTF-8") + "=" +
                    URLEncoder.encode(YMD, "UTF-8");
            data += "&" + URLEncoder.encode("Year", "UTF-8") + "=" +
                    URLEncoder.encode(Y, "UTF-8");
            data += "&" + URLEncoder.encode("Month", "UTF-8") + "=" +
                    URLEncoder.encode(Month, "UTF-8");
            data += "&" + URLEncoder.encode("ReceiptID", "UTF-8") + "=" +
                    URLEncoder.encode(code, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            System.out.println(data);
            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }

            return sb.toString();
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }
}

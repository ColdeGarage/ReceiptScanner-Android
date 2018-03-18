package com.example.qq;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

import android.os.AsyncTask;
public class uploadData  extends AsyncTask {
    private String YMD,Month,MD,Day,Money,Type,Remark,Date;
    public uploadData(String YMD,String Month,String MD,String Day,String Money,String Type,String Remark,String Date) {
        this.Date = Date;
        this.Day = Day;
        this.MD = MD;
        this.Month = Month;
        this.Remark = Remark;
        this.Type = Type;
        this.YMD = YMD;
        this.Money = Money;
    }
    @Override
    protected String doInBackground(Object[] objects) {
        try{
            String link="http://makerthon.nthuee.org/2018/OnlineAccounting/android_insert_db.php";
            String data  = URLEncoder.encode("YMD", "UTF-8") + "=" +
                    URLEncoder.encode(YMD, "UTF-8");
            data += "&" + URLEncoder.encode("Month", "UTF-8") + "=" +
                    URLEncoder.encode(Month, "UTF-8");
            data += "&" + URLEncoder.encode("MD", "UTF-8") + "=" +
                    URLEncoder.encode(MD, "UTF-8");
            data += "&" + URLEncoder.encode("Day", "UTF-8") + "=" +
                    URLEncoder.encode(Day, "UTF-8");
            data += "&" + URLEncoder.encode("Date", "UTF-8") + "=" +
                    URLEncoder.encode(Date, "UTF-8");
            data += "&" + URLEncoder.encode("Money", "UTF-8") + "=" +
                    URLEncoder.encode(Money, "UTF-8");
            data += "&" + URLEncoder.encode("Type", "UTF-8") + "=" +
                    URLEncoder.encode(Type, "UTF-8");
            data += "&" + URLEncoder.encode("Remark", "UTF-8") + "=" +
                    URLEncoder.encode(Remark, "UTF-8");

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
package com.example.qq;


import android.os.AsyncTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class updateData extends AsyncTask {
    @Override
    protected String doInBackground(Object... data) {
        System.out.println("sssssssdsd");
        String YMD = String.valueOf(data[0]);
        String M = String.valueOf(data[1]);
        String MD = String.valueOf(data[2]);
        String D = String.valueOf(data[3]);
        String money = String.valueOf(data[4]);
        String type = String.valueOf(data[5]);
        String remark = String.valueOf(data[6]);
        String link = "http://makerthon.nthuee.org/2018/OnlineAccounting/android_insert_db.php?YMD=dd&M=ss&MD=dd&D=Zz&money=aa&type=dd&remark=Ff";
        //URL url = new URL(link);
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(link);
        try {
            HttpResponse response = client.execute(request);
            HttpEntity resEntity = response.getEntity();
            System.out.println("dddddddddd");
            //Log.d("Response of GET request", EntityUtils.toString(resEntity));
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "Dd";
    }
}

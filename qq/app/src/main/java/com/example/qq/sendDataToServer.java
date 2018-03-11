package com.example.qq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
public class sendDataToServer extends AppCompatActivity {
    private EditText _title, _date, _news;
    private Button _submit;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_to_server);

        _title = (EditText) findViewById(R.id.news_title);
        _date = (EditText) findViewById(R.id.news_title);
        _news = (EditText) findViewById(R.id.new_details);

        _submit = (Button) findViewById(R.id.submit_news);


        _submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://mrubel.com/tuntuninews/api/newpostfromapp.php";


                StringRequest sq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    protected Map<String, String > getParams(){
                        Map<String, String> parr = new HashMap<String, String>();

                        parr.put("mytitle", _title.getText().toString());
                        parr.put("mydate", _date.getText().toString());
                        parr.put("mynews", _news.getText().toString());

                        return parr;

                    }

                };
                mQueue = Volley.newRequestQueue(sendDataToServer.this);
                mQueue.add(sq);
                Toast.makeText(getApplicationContext(), "Vua news published Successfully!", Toast.LENGTH_LONG).show();

            }

        });
    }
}

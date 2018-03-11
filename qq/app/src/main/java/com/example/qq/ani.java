package com.example.qq;

import com.bumptech.glide.Glide;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ani extends AppCompatActivity {
    private ImageView gif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ani);
        gif = (ImageView) findViewById(R.id.imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(ani.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000);
        Glide.with(this).load(R.drawable.ani_gif).into(gif);

    }

}

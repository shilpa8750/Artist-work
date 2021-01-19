package com.shilpa.artist_work;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.shilpa.artist_work.login_activity.AuthActivity;


public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN=5000;

    private ImageView imageView;
    private TextView textView;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text1);
        textView1 =findViewById(R.id.text2);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, AuthActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);

    }

}
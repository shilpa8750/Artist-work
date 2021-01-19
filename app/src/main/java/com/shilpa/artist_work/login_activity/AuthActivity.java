package com.shilpa.artist_work.login_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.FacebookActivity;
import com.shilpa.artist_work.R;

public class AuthActivity extends AppCompatActivity {

    private Button email_button;
    private Button fb_button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        email_button = findViewById(R.id.email_login);
        fb_button = findViewById(R.id.fb_login);
        textView = findViewById(R.id.already_login);
    }


    public void email_login(View view) {
        Intent intent = new Intent(AuthActivity.this,EmailActivity.class);
        startActivity(intent);
    }

    public void fb_login(View view) {
        Intent intent = new Intent(AuthActivity.this, FbActivity.class);
        startActivity(intent);
    }


}
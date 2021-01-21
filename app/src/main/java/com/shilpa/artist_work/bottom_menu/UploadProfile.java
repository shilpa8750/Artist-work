package com.shilpa.artist_work.bottom_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.shilpa.artist_work.R;

import java.net.URI;

public class UploadProfile extends AppCompatActivity {

    ImageView img;
    Button upload,browse;
    URI filepath;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile);


    }


}
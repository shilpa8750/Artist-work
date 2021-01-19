package com.shilpa.hindishayri.categories;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//import com.google.android.gms.ads.MobileAds;
//import com.google.android.gms.ads.initialization.InitializationStatus;
//import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shilpa.hindishayri.Model;
import com.shilpa.hindishayri.R;

import java.util.ArrayList;
import java.util.List;

public class GoodNight extends AppCompatActivity  implements View.OnClickListener {
    TextView count_txt , shayri_text;
    CardView back_btn, copy_btn, share_btn, next_btn;

    List<String> quote_list;
    DatabaseReference databaseReference;
    Model myShayari;
    int position = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Good Night");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        count_txt = findViewById(R.id.countTEXT);
        shayri_text = findViewById(R.id.shayriTEXT);
        back_btn = findViewById(R.id.backBtn);
        copy_btn = findViewById(R.id.copyBtn);
        share_btn = findViewById(R.id.shareBtn);
        next_btn = findViewById(R.id.nextBtn);

        back_btn.setOnClickListener(this);
        copy_btn.setOnClickListener(this);
        share_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("goodnight");
        myShayari = new Model();
        quote_list = new ArrayList<>();

        //event value method
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    myShayari = dataSnapshot1.getValue(Model.class);
                    if (myShayari != null){
                        quote_list.add(myShayari.getTitle());
                    }

                }
                shayri_text.setText(quote_list.get(position));
                count_txt.setText(position + "/" + quote_list.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "error Occured", Toast.LENGTH_SHORT).show();

            }
        });

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        AdView mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backBtn:
                back();
                break;
            case R.id.copyBtn:
                copy();
                break;
            case R.id.shareBtn:
                share();
                break;
            case R.id.nextBtn:
                next();
                break;

        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() ==  android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("SetTextI18n")
    private void back(){
        if (position>0){
            position = (position-1)%quote_list.size();
            shayri_text.setText(quote_list.get(position));
            count_txt.setText(position + "/" + quote_list.size());
        }
    }
    @SuppressLint("SetTextI18n")
    private void next(){
        position = (position+1)%quote_list.size();
        shayri_text.setText(quote_list.get(position));
        count_txt.setText(position + "/" + quote_list.size());

    }
    private void copy()
    {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("text", shayri_text.getText());
        if (clipboardManager != null){
            clipboardManager.setPrimaryClip(clipData);
        }
        Toast.makeText(getApplicationContext(), "copied", Toast.LENGTH_SHORT).show();
    }
    private void share()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plan");
        intent.putExtra(Intent.EXTRA_TEXT,shayri_text.getText());
        startActivity(Intent.createChooser(intent,"Share To"));
    }
}

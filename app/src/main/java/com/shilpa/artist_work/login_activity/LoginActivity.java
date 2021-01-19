package com.shilpa.artist_work.login_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shilpa.artist_work.HomeActivity;
import com.shilpa.artist_work.R;

public class LoginActivity extends AppCompatActivity {

    private EditText log_email, log_pass;
    private Button login;
    private TextView register;

    private String email, pass;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        log_email= findViewById(R.id.logEmail);
        log_pass = findViewById(R.id.logPass);
        register = findViewById(R.id.openRegister);
        login= findViewById(R.id.userLogin);

        register.setOnClickListener((view)->{
            startActivity(new Intent(LoginActivity.this,EmailActivity.class));
            finish();
        });

            login.setOnClickListener((view)->{ validateUser();});

    }
    @Override
    protected void  onStart(){
        super.onStart();
        if (auth.getCurrentUser()!= null){
            startActivity(new Intent(this,HomeActivity.class));
        }
    }

    private void validateUser() {
            email = log_email.getText().toString();
            pass = log_pass.getText().toString();
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please enter the all details", Toast.LENGTH_SHORT).show();
            } else {
                loginUser();

        }
    }

    private void loginUser() {

        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            finish();
                        }else
                        {
                            Toast.makeText(LoginActivity.this, "error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}


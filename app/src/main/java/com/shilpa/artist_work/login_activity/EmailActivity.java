 package com.shilpa.artist_work.login_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.shilpa.artist_work.HomeActivity;
import com.shilpa.artist_work.R;

public class EmailActivity extends AppCompatActivity {

    private EditText fname,lname,email,password;
    private Button register;
    private TextView login;

    private String first_name, last_name,regemail,regpassword;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        fname = findViewById(R.id.first_name);
        lname = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.openLogin);

        register.setOnClickListener((view)->{validateUser();});

        login.setOnClickListener((view)->{
            startActivity(new Intent(EmailActivity.this,LoginActivity.class));
            finish();
        });

         firebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void validateUser() {
        first_name = fname.getText().toString();
        last_name = lname.getText().toString();
        regemail = email.getText().toString();
        regpassword = password.getText().toString();

        if(first_name.isEmpty()||last_name.isEmpty()||regemail.isEmpty()||
                regpassword.isEmpty())  {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else
        {
            registerUser();
        }
    }

    private void registerUser() {
        firebaseAuth.createUserWithEmailAndPassword(regemail,regpassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(EmailActivity.this, "Successful login", Toast.LENGTH_SHORT).show();
                    updateUser();
                }else{
                    Toast.makeText(EmailActivity.this, "Error : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUser() {
        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(first_name)
                .build();

        firebaseAuth.getCurrentUser().updateProfile(changeRequest);
        firebaseAuth.signOut();
        openHome();
    }

    private void openHome() {

        startActivity(new Intent(EmailActivity.this,HomeActivity.class));
        finish();
    }
}
package com.shilpa.artist_work;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private FirebaseAuth firebaseAuth;
    private TextView textView;
    private ImageView imageView;
    private LoginButton loginButton;
    private static final String TAG="FacebookAuthentication";

    //facebook login
    private CallbackManager mCallbackManager;
    private  FirebaseAuth.AuthStateListener authStateListener;
    private AccessTokenTracker accessTokenTracker;
    private TextView textViewUser;
    private ImageView imageView3;





    //faceBook Code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth= FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());

        //textViewUser = findViewById(R.id.loginText);
        imageView3= findViewById(R.id.userImage);
        loginButton= findViewById(R.id.fb_login);
        mCallbackManager=CallbackManager.Factory.create();
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"onSuccess" + loginResult);
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG,"onCancelled");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"onError" + error);

            }
        });

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user =firebaseAuth.getCurrentUser();
                if (user!= null){
                    updateUI(user);
                }
                else
                {
                    updateUI(null);
                }
            }
        };

        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken==null){
                    firebaseAuth.signOut();
                }
            }
        };


        // Initialize Firebase Auth


    }
    private  void handleFacebookToken(AccessToken token){
        Log.d(TAG,"handleFacebookToken" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Log.d(TAG,"sign is with credential: successful");
                    FirebaseUser user=firebaseAuth.getCurrentUser();
                    updateUI(user);
                }
                else {

                    Log.d(TAG,"sign is with credential: failed", task.getException());
                    Toast.makeText(LoginActivity.this,"Authentication failed", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }


        });
    }
    @Override
    protected void  onActivityResult(int requestcode, int resultcode, @NonNull Intent data) {

        mCallbackManager.onActivityResult(requestcode, resultcode,data);
        super.onActivityResult(requestcode, resultcode, data);

    }
    private void updateUI(FirebaseUser user){
        if(user!= null){
            textViewUser.setText(user.getDisplayName());
            if(user.getPhotoUrl() != null){
                String photoUrl = user.getPhotoUrl().toString();
                photoUrl= photoUrl + "?type=large";
                Picasso.get().load(photoUrl).into(imageView3);
            }else
            {
                textViewUser.setText("");
                imageView3.setImageResource(R.drawable.splashbg);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }

    }
}
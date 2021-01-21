package com.shilpa.artist_work;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shilpa.artist_work.bottom_menu.HomeFragment;
import com.shilpa.artist_work.bottom_menu.NotificatonFragment;
import com.shilpa.artist_work.bottom_menu.ProfileFragment;
import com.shilpa.artist_work.bottom_menu.SaveFragment;
import com.shilpa.artist_work.bottom_menu.UploadFragment;
import com.shilpa.artist_work.login_activity.EmailActivity;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
        //side menu
    DrawerLayout drawerLayout;
    NavigationView nav;
    Toolbar toolbar;



     //bottom navigation
    BottomNavigationView bnv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer);
        nav= findViewById(R.id.nav_menu);
        toolbar= findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar);

        nav.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment temp;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                     switch (item.getItemId()){
                    case R.id.blog :
                        temp = new BlogFragment();
                        Toast.makeText(HomeActivity.this, "Blog panel is open", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.upload :

                        Toast.makeText(HomeActivity.this, "Upload panel is open", Toast.LENGTH_SHORT).show();
                        break;
                        case R.id.feedback :
                            temp = new FeedbackFragment();
                        Toast.makeText(HomeActivity.this, "Feedback panel is open", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.setting :
                        temp = new SettingFragment();
                        Toast.makeText(HomeActivity.this, "Setting panel is open", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.help :
                        temp = new HelpFragment();
                        Toast.makeText(HomeActivity.this, "Help panel is open", Toast.LENGTH_SHORT).show();
                        break;
                        case R.id.about :
                            temp = new AboutFragment();
                        Toast.makeText(HomeActivity.this, "About panel is open", Toast.LENGTH_SHORT).show();
                        break;
                        case R.id.term :
                            temp = new TermFragment();
                        Toast.makeText(HomeActivity.this, "Term & Condition panel is open", Toast.LENGTH_SHORT).show();

                        break;
                        case R.id.privacy :
                            temp = new PrivacyFragment();
                        Toast.makeText(HomeActivity.this, "Privacy panel is open", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.sign_out :
                        Toast.makeText(HomeActivity.this, "SignOut", Toast.LENGTH_SHORT).show();

                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container,temp).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        //recycleview and cardview



        //bottom navigation
        bnv=(BottomNavigationView)findViewById(R.id.bottom_nav);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment temp1=null;
                switch (item.getItemId()){
                    case  R.id.bottom_home:temp1 = new HomeFragment();
                    break;
                    case  R.id.bottom_upload:temp1 = new UploadFragment();
                    break;
                    case  R.id.bottom_noti:temp1 = new NotificatonFragment();
                    break;
                    case  R.id.bottom_save:temp1 = new SaveFragment();
                    break;
                    case  R.id.bottom_profile:temp1 = new ProfileFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container2,temp1).commit();
                return true;
            }
        });
    }
//


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) { return true; }


    }

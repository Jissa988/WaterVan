package com.example.watervan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.bumptech.glide.Glide;
import com.example.watervan.Home.HomepageActivity;
import com.example.watervan.Login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;
public class SplashActivity extends AppCompatActivity {

    private TokenSharedPreference tokenSharedPreference;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
       // setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        getSupportActionBar().hide();
          tokenSharedPreference = new TokenSharedPreference(this);
        ImageView imageView1;


        imageView1=findViewById(R.id.gif);

        Glide.with(SplashActivity.this).load(R.raw.gif1).into(imageView1);

        //   Glide.with(activity).load(url).asGif().into(view);
        startActivityMainDelay();

       // changeStatusBarColor();


    }


    private void startActivityMainDelay() {
        // Show splash screen for 2 seconds
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if (tokenSharedPreference.isFirstLaunch()) {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    //startActivity(new Intent(ActivitySplash.this, Login.class));
//                    set_id_user.setFirstLaunch(false);
                }
                else
                {
                    Intent i = new Intent(SplashActivity.this, HomepageActivity.class);
                    startActivity(i);
                    finish(); // kill current activity
                }
            }
        };
        new Timer().schedule(task, 3000);
    }


}
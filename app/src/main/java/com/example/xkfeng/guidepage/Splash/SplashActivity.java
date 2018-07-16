package com.example.xkfeng.guidepage.Splash;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xkfeng.guidepage.MainActivity;
import com.example.xkfeng.guidepage.R;

/**
 * Created by initializing on 2018/7/16.
 */

public class SplashActivity extends AppCompatActivity {

    private final static int DELAY_TIME = 5000 ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this ,
                        MainActivity.class));
            }
        },DELAY_TIME) ;
    }
}

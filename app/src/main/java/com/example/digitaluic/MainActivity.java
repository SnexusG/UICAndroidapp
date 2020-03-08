package com.example.digitaluic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pr1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pr1 = findViewById(R.id.progressBar);
        pr1.setProgress(0);
        pr1.getProgressDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        final boolean firstStart = prefs.getBoolean("firstStart", true);

//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    Thread.sleep(2000);
//                    Intent intent = new Intent(getApplicationContext(),SplashScreen.class);
//                    startActivity(intent);
//                    finish();
//                }catch(InterruptedException e){
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        t1.start();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(pr1.getProgress() != 100) {
                    pr1.incrementProgressBy(20);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(firstStart) {
                    WelcomeMessage welcomeMessage = new WelcomeMessage();
                    welcomeMessage.show(getSupportFragmentManager(), "welcome message");
//                    Intent intent = new Intent(getApplicationContext(), LoginPage.class);
//                    startActivity(intent);
//                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(), drawer_activity.class); // changes from mainscreen.class
                    startActivity(intent);
                    finish();
                }
            }
        });

        t1.start();

    }

}

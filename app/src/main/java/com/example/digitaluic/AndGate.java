package com.example.digitaluic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AndGate extends AppCompatActivity {
    private Switch s1;
    private ImageView im2;
    private ImageView im6;
    private ToggleButton tb1;
    private  ToggleButton tb2;
    private TextView tx1;
    int a = 0;
    int b = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_gate);

        s1 = (Switch) findViewById(R.id.switch1);
        im2 = (ImageView) findViewById(R.id.imageView2);
        im6 = (ImageView) findViewById(R.id.imageView6);
        tb1 = (ToggleButton) findViewById(R.id.toggleButton);
        tb2 = (ToggleButton) findViewById(R.id.toggleButton2);
        tx1 = (TextView) findViewById(R.id.textView8);


        SharedPreferences loginPrefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        Boolean check = loginPrefs.getBoolean("loginBool", false);

        if (check){
            s1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s1.isChecked()) {
//                        im2.setEnabled(false);
//                        im2.setVisibility(View.GONE);
                        im6.setVisibility(View.VISIBLE);
                        tb1.setVisibility(View.VISIBLE);
                        tb2.setVisibility(View.VISIBLE);
                        tx1.setVisibility(View.VISIBLE);
                    } else {
//                        im2.setEnabled(true);
//                        im2.setVisibility(View.VISIBLE);
                        im6.setVisibility(View.GONE);
                        tb1.setVisibility(View.GONE);
                        tb2.setVisibility(View.GONE);
                        tx1.setVisibility(View.GONE);
                    }
                }
            });
    }else{
            s1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s1.isChecked()) {
                        Toast.makeText(AndGate.this, "Login To use Interactive mode", Toast.LENGTH_SHORT).show();
//                        im2.setEnabled(true);
//                        im2.setVisibility(View.VISIBLE);
                        im6.setVisibility(View.GONE);
                        tb1.setVisibility(View.GONE);
                        tb2.setVisibility(View.GONE);
                        tx1.setVisibility(View.GONE);
                    } else {
//                        im2.setEnabled(true);
//                        im2.setVisibility(View.VISIBLE);
                        im6.setVisibility(View.GONE);
                        tb1.setVisibility(View.GONE);
                        tb2.setVisibility(View.GONE);
                        tx1.setVisibility(View.GONE);
                    }
                }
            });
        }


        tb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tb1.isChecked()){
                    a = 1;
                }else{
                    a = 0;
                }
                if(a == 1 && b == 1){
                    tx1.setText("1");
                } else{
                    tx1.setText("0");
                }
            }
        });

        tb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tb2.isChecked()){
                    b = 1;
                }else{
                    b = 0;
                }
                if(a == 1 && b == 1){
                    tx1.setText("1");
                } else{
                    tx1.setText("0");
                }
            }
        });

    }
}

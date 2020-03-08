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

public class NorGate extends AppCompatActivity {

    private Switch s1;
    private ImageView im2;
    private ImageView im6;
    private ToggleButton tb1;
    private  ToggleButton tb2;
    private TextView tx1, tx2, tx3, tx4;
    int a = 0;
    int b = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nor_gate);

        s1  =  findViewById(R.id.norswitch1);
        im2 =  findViewById(R.id.norimageView2);
        im6 =  findViewById(R.id.norimageView6);
        tb1 =  findViewById(R.id.nortoggleButton);
        tb2 =  findViewById(R.id.nortoggleButton2);
        tx1 =  findViewById(R.id.nortextView8);
        tx2 = findViewById(R.id.inpA);
        tx3 = findViewById(R.id.inpB);
        tx4 = findViewById(R.id.opY);

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
                        tx2.setVisibility(View.VISIBLE);
                        tx3.setVisibility(View.VISIBLE);
                        tx4.setVisibility(View.VISIBLE);
                    } else {
//                        im2.setEnabled(true);
//                        im2.setVisibility(View.VISIBLE);
                        im6.setVisibility(View.GONE);
                        tb1.setVisibility(View.GONE);
                        tb2.setVisibility(View.GONE);
                        tx1.setVisibility(View.GONE);
                        tx2.setVisibility(View.GONE);
                        tx3.setVisibility(View.GONE);
                        tx4.setVisibility(View.GONE);
                    }
                }
            });
        }else{
            s1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s1.isChecked()) {
                        Toast.makeText(NorGate.this, "Login To use Interactive mode", Toast.LENGTH_SHORT).show();
//                        im2.setEnabled(true);
//                        im2.setVisibility(View.VISIBLE);
                        im6.setVisibility(View.GONE);
                        tb1.setVisibility(View.GONE);
                        tb2.setVisibility(View.GONE);
                        tx1.setVisibility(View.GONE);
                        tx2.setVisibility(View.GONE);
                        tx3.setVisibility(View.GONE);
                        tx4.setVisibility(View.GONE);
                    } else {
//                        im2.setEnabled(true);
//                        im2.setVisibility(View.VISIBLE);
                        im6.setVisibility(View.GONE);
                        tb1.setVisibility(View.GONE);
                        tb2.setVisibility(View.GONE);
                        tx1.setVisibility(View.GONE);
                        tx2.setVisibility(View.GONE);
                        tx3.setVisibility(View.GONE);
                        tx4.setVisibility(View.GONE);
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
                if(a == 0 && b == 0){
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
                if(a == 0 && b == 0){
                    tx1.setText("1");
                } else{
                    tx1.setText("0");
                }
            }
        });

    }
}

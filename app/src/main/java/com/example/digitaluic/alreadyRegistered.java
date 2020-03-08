package com.example.digitaluic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class alreadyRegistered extends AppCompatActivity implements View.OnClickListener {

    private EditText ed1;
    private EditText pd1;
    private Button bt1;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_registered);

        firebaseAuth = FirebaseAuth.getInstance();
        ed1 = (EditText) findViewById(R.id.editTexta);
        pd1 = (EditText) findViewById(R.id.editTextb);
        bt1 = (Button) findViewById(R.id.buttona);
        progressDialog = new ProgressDialog(this);
        bt1.setOnClickListener(this);
    }

    public  void  loginUser(){
        String email = ed1.getText().toString().trim();
        String password = pd1.getText().toString().trim();

        if(TextUtils.isEmpty((email))){
            Toast.makeText(this, "Please enter email Id!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty((password))){
            Toast.makeText(this, "Please enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging in User....");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();

                            SharedPreferences loginPrefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor loginEditor = loginPrefs.edit();
                            loginEditor.putBoolean("loginBool", true);
                            loginEditor.apply();

                            Intent intent = new Intent(getApplicationContext(),drawer_activity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(alreadyRegistered.this, "Incorrect email/password", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });

    }

    public void onClick(View view) {
            //login activity
         loginUser();
    }
}

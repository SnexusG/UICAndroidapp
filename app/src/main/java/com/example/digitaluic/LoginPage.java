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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity implements View.OnClickListener{

    private EditText ed1;
    private EditText ed2;
    private EditText ed3;
    private EditText ed4;

    private Button bt1;
    private TextView tx1;
    private ProgressDialog progressDialog;

   private FirebaseAuth firebaseAuth;
   DatabaseReference databaseUsers;

    private  TextView tx2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");

        ed1 = findViewById(R.id.editText);
        ed2 =  findViewById(R.id.editText2);
        ed3 = findViewById(R.id.editText3);
        ed4 = findViewById(R.id.editText4);

        bt1 =  findViewById(R.id.button19);
        tx1 =  findViewById(R.id.textView11);
        tx2 =  findViewById(R.id.textView12);

        tx2.setOnClickListener(this);
        bt1.setOnClickListener(this);
        tx1.setOnClickListener(this);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();

    }


    public  void  registerUser(){

        final String email = ed1.getText().toString().trim();
        final String password = ed2.getText().toString().trim();
        final String userName = ed3.getText().toString();
        final String collegeName = ed4.getText().toString();


        if(TextUtils.isEmpty((email))){
            Toast.makeText(this, "Please enter email Id!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty((password))){
            Toast.makeText(this, "Please enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.length() < 8){
            Toast.makeText(this, "Password should be atleast 8 characters long!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty((userName))){
            Toast.makeText(this, "Please enter Username!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginPage.this, "Registration Complete!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();

                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                           // String id = databaseUsers.push().getKey();

                            User u1 = new User(user.getUid(), userName, collegeName, password, email);
                            databaseUsers.child(user.getUid()).setValue(u1);

                            SharedPreferences loginPrefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor loginEditor = loginPrefs.edit();
                            loginEditor.putBoolean("loginBool", true);
                            loginEditor.apply();


                            Intent intent = new Intent(getApplicationContext(),drawer_activity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginPage.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == bt1){
            //register user on firebase
            registerUser();
        }
        if(view == tx1){
            //login activity
            Intent intent = new Intent(getApplicationContext(),alreadyRegistered.class);
            startActivity(intent);
        }
        if(view == tx2){

            SharedPreferences loginPrefs = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor loginEditor = loginPrefs.edit();
            loginEditor.putBoolean("loginBool", false);
            loginEditor.apply();

            Intent intent = new Intent(getApplicationContext(),drawer_activity.class);  //changed from mainscreen.class
            startActivity(intent);
            finish();
        }

    }

}

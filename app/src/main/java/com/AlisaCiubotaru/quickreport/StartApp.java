package com.AlisaCiubotaru.quickreport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class StartApp extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private Button buttonregister;
    private Button buttonlearnmore;
    private EditText username;
    private EditText password;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startapp);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonregister = findViewById(R.id.register);
        buttonlearnmore = findViewById(R.id.learnmore);
        buttonLogin = findViewById(R.id.buttonLogin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        buttonLogin.setOnClickListener(this);
        buttonregister.setOnClickListener(this);
        buttonlearnmore.setOnClickListener(this);
    }



    public void openActivity2(){
        Intent intent = new Intent(this, FloorPlanLoaded.class);
        startActivity(intent);
    }

    private void learnMore(){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    private void loginUser(){
//        openActivity2();
        Intent intent = new Intent(this, ReportOrCheck.class);
        startActivity(intent);
    }

    private void openActivityRegister(){
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if( v == buttonLogin){
            loginUser();
        }

        if( v == buttonregister){
            openActivityRegister();
        }

        if( v == buttonlearnmore){
            learnMore();
        }

    }


}
package com.AlisaCiubotaru.quickreport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StartApp extends AppCompatActivity implements View.OnClickListener {

    private Button buttonLogin;
    private Button buttonregister;
    private Button buttonlearnmore;
    private Button buttonguest;
    private EditText email;
    private EditText password;
//    private boolean guest = false;
//    private boolean reporter = false;
//    private boolean solver = false;

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startapp);

        progressDialog = new ProgressDialog(this);


        mAuth = FirebaseAuth.getInstance();
//        if( mAuth.getCurrentUser() == null){
//            finish();
//            startActivity(new Intent(this, StartApp.class));
//        }

        buttonregister = findViewById(R.id.register);
        buttonlearnmore = findViewById(R.id.learnmore);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonguest = findViewById(R.id.buttonGuest);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        buttonLogin.setOnClickListener(this);
        buttonregister.setOnClickListener(this);
        buttonlearnmore.setOnClickListener(this);
        buttonguest.setOnClickListener(this);
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    public void openActivity2(){
        Intent intent = new Intent(this, FloorPlanLoaded.class);
        startActivity(intent);
    }

    private void learnMore(){
        Intent intent = new Intent(this, About.class);
        startActivity(intent);
    }

    private void loginUser(){
        final String mail = email.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(StartApp.this,"Successful authentication!",Toast.LENGTH_SHORT).show();
                            openReportOrCheck();
                        } else {
                            Toast.makeText(StartApp.this,"Invalid data or non-existing user. TRY AGAIN!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void openReportOrCheck(){
        Intent intent = new Intent(this, ReportOrCheck.class);
        startActivity(intent);
    }

    private void openActivityRegister(){
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }
//
//    private void setGuestRole(){
//        guest = true;
//        reporter = false;
//        solver = false;
//    }
//
//    private void setReporterRole(){
//        guest = false;
//        reporter = true;
//        solver = false;
//    }

    @Override
    public void onClick(View v) {
        if( v == buttonLogin){
//            setReporterRole();
            loginUser();
        }

        if( v == buttonregister){
            openActivityRegister();
        }

        if( v == buttonlearnmore){
            learnMore();
        }

        if( v == buttonguest){
//            setGuestRole();
            Toast.makeText(StartApp.this,"Authenticating as GUEST!",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ReportOrCheck.class));
        }

    }


}
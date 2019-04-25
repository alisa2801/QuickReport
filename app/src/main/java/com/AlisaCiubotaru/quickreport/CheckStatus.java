package com.AlisaCiubotaru.quickreport;

//import android.content.Intent;
//
//import com.google.firebase.auth.FirebaseAuth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CheckStatus extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkstatus);
    }

    @Override
    public void onClick(View v) {

    }

//    private FirebaseAuth mAuth;
//
//    mAuth = FirebaseAuth.getInstance();
//        if( mAuth.getCurrentUser() == null){
//        finish();
//        startActivity(new Intent(this, StartApp.class));
//    }
}

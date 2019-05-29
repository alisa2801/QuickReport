package com.AlisaCiubotaru.quickreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ReportOrCheck extends AppCompatActivity implements View.OnClickListener {

    private Button buttonreport;
    private Button buttoncheckstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportorcheck);

        buttonreport = findViewById(R.id.buttonreport);
        buttonreport.setOnClickListener(this);

        buttoncheckstatus = findViewById(R.id.buttoncheckstatus);
        buttoncheckstatus.setOnClickListener(this);
    }
    public void openActivityReport(){
        Intent intent = new Intent(this, FloorPlanLoaded.class);
        startActivity(intent);
    }

    public void openActivityCheck(){
//        Intent intent = new Intent(this, CheckStatus.class);
        Intent intent = new Intent(this, ViewDatabase.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonreport)
        {
            openActivityReport();
        }
        else if (v == buttoncheckstatus)
        {
            openActivityCheck();
        }
    }
}
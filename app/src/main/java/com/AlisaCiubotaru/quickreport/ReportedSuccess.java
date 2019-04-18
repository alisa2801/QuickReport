package com.AlisaCiubotaru.quickreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ReportedSuccess extends AppCompatActivity implements View.OnClickListener {

    private Button buttonBackReportOrCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportedsuccess);

        buttonBackReportOrCheck = (Button) findViewById(R.id.buttonbackreporcheck);
        buttonBackReportOrCheck.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonBackReportOrCheck){
            Intent intent = new Intent(this, ReportOrCheck.class);
            startActivity(intent);
        }
    }
}

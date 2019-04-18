package com.AlisaCiubotaru.quickreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ProblemReporter extends AppCompatActivity implements View.OnClickListener {

    private Button butonSubmitProblem;
    private Button butonVerif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_reporter);

        butonSubmitProblem = findViewById(R.id.buttonsubmitproblem);
        butonSubmitProblem.setOnClickListener(this);

        butonVerif = (Button) findViewById(R.id.button2);
        butonVerif.setOnClickListener(this);


    }

    public void openActivityReportedSuccess(){
        Intent intent = new Intent(this, ReportedSuccess.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == butonSubmitProblem){
            butonSubmitProblem.setText("BLAAAAAAAA1");
            openActivityReportedSuccess();
        }

        else if (v == butonVerif){
            butonVerif.setText("BLAAAAAAAA2");
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        }
    }
}

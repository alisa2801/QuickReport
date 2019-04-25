package com.AlisaCiubotaru.quickreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Report extends AppCompatActivity implements View.OnClickListener {
    private Button send;
    private Spinner dropdown;
    private String to, subject, message;
    private TextView room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        send = findViewById(R.id.buttonSend);
        send.setOnClickListener(this);

        room = findViewById(R.id.reportRoom);
        room.setText(FloorPlanLoaded.getRoom());
        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinnerProblemTarget);
        //create a list of items for the spinner.
        String[] items = new String[]{"Plumber", "Electrician", "three"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        // Plumber
                        to = "alisa.ciubotaru@gmail.com";
                        subject = "QuickReport: New plumber issue reported in "+ room.getText().toString();
                        message = "Hello Mr/Ms Plumber\n. A new plumber issue was reported in " + room.getText().toString();
                        break;
                    case 1:
                        // Electrician
                        to = "alisa.ciubotaru@ligaac.ro";
                        subject = "QuickReport: New electrician issue reported in "+ room.getText().toString();
                        message = "Hello Mr/Ms Electrician\n. A new plumber issue was reported in " + room.getText().toString();
                        break;
                    case 2:
                        // Three
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == send) {
            new Email().sendEmail(to, subject, message);
            Toast.makeText(Report.this, "Problem reported sucessful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ReportedSuccess.class);
            startActivity(intent);
        }
    }
}
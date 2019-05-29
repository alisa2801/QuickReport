package com.AlisaCiubotaru.quickreport;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckStatus extends AppCompatActivity implements View.OnClickListener {

    private Button uselessButton;
    private TextView textViewUseless;
    private ArrayList<Problema> problemelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkstatus);

        uselessButton = findViewById(R.id.btnchk);
        uselessButton.setOnClickListener(this);

        textViewUseless = findViewById(R.id.textViewtv);
    }

    @Override
    public void onClick(View view) {
        if ( view == uselessButton)
        {
            textViewUseless.setText("Macaar nu crapa...");
            getUserForTest();
//            textViewUseless.setText(problemelist.get(0).getEmailReporter());
        }
    }

    public void getUserForTest(){
        DatabaseReference ref1= FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref2;
        ref2 = ref1.child("Probleme");


        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                problemelist = new ArrayList<Problema>();
                for (DataSnapshot dsp : dataSnapshot.getChildren())	 		{
//                    problemelist.add(dsp.getValue(Problema.class));

                    Problema p = dsp.getValue(Problema.class);
                    Toast.makeText(getApplicationContext(), "ALISA: p1User= "+p.getEmailReporter(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

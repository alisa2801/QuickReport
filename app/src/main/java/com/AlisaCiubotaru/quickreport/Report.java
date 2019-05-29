
package com.AlisaCiubotaru.quickreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Report extends AppCompatActivity implements View.OnClickListener {
    private Button send;
    private ImageButton openCamera;
    private EditText moreDetails;
    private Spinner dropdown;
    private String to, subject, mesajMail;
    private TextView room;
    private TextView pathImgText;
    private Problema problema;
    private String details;
    private String userUuid;
    final String[] username = new String[1];
    final String[] fullNameReporter = new String[1];
    final String[] email = new String[1];
    private String pathImg;//pathImgText.getText().toString();
    private String roomtext;
    Boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        send = findViewById(R.id.reportbtnraporteazapb);
        send.setOnClickListener(this);

        openCamera = findViewById(R.id.reportbtnuploadImg);
        openCamera.setOnClickListener(this);

        moreDetails = findViewById(R.id.reportmessage);

        room = findViewById(R.id.reportRoom);
        room.setText(FloorPlanLoaded.getRoom());

        pathImgText = findViewById(R.id.reportpathImg);
// aici preluam textul din camera capture
//        pathImgText.setText(getIntent().getStringExtra("mesaj"));




        dropdown = findViewById(R.id.reportspinnerProblemTarget);
        String[] items = new String[]{"Instalator", "Electrician", "Aparat cafea", "Alta problema"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        // Instalator
                        to = "alisa.ciubotaru@gmail.com";
                        subject = "QuickReport: Problema instalatie raportata in "+ room.getText().toString();
                        mesajMail = "Buna ziua domnule Instalator,\n O noua problema a fost raportata in: " + room.getText().toString()+
                                ".\n"+"Detalii: ";
                        break;
                    case 1:
                        // Electrician
                        to = "alisa.ciubotaru@ligaac.ro";
                        subject = "QuickReport: Problema instalatie electrica raportata in "+ room.getText().toString();
                        mesajMail = "Buna ziua domnule Electrician,\n O noua problema a fost raportata in: " + room.getText().toString()+
                                ".\n"+"Detalii: ";
                        break;
                    case 2:
                        // Aparat cafea
                        to = "alisa.ciubotaru@ligaac.ro";
                        subject = "QuickReport: Problema aparat cafea raportata in "+ room.getText().toString();
                        mesajMail = "Buna ziua domnule,\n O noua problema a fost raportata in: " + room.getText().toString()+
                                ".\n"+"Detalii: ";
                        break;
                    case 3:
                        // Alta problema
                        to = "alisa.ciubotaru@ligaac.ro";
                        subject = "QuickReport: Problema raportata in "+ room.getText().toString();
                        mesajMail = "Buna ziua domnule,\n O noua problema a fost raportata in: " + room.getText().toString()+
                                ".\n"+"Detalii: ";
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

    public void raporteazaProblema()
    {
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final String userid=user.getUid();
        userUuid =userid;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Toast.makeText(getApplicationContext(), "ALISA: reference= "+reference.toString(), Toast.LENGTH_LONG).show();

//room.getText().toString();
//        String detailspb = details;


        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                email[0] = dataSnapshot.child("email").getValue(String.class);
                Toast.makeText(getApplicationContext(), "ALISA email: "+ email[0], Toast.LENGTH_LONG).show();
                fullNameReporter[0] = dataSnapshot.child("fullName").getValue(String.class);
                Toast.makeText(getApplicationContext(), "ALISA fullNameReporter: "+ fullNameReporter[0], Toast.LENGTH_LONG).show();
                username[0] = dataSnapshot.child("username").getValue(String.class);
                Toast.makeText(getApplicationContext(), "ALISA room: "+ roomtext, Toast.LENGTH_LONG).show();

//                changed = true;

//                String fullName = dataSnapshot.child("fullName").getValue(String.class);
                Toast.makeText(getApplicationContext(), "ALISA: pathImage "+ pathImg, Toast.LENGTH_LONG).show();
//                tv.setText("Email: "+email+" fullNameUser: "+fullName);
//

                Toast.makeText(getApplicationContext(), "ALISA: problemaEmail11111111= " + email[0], Toast.LENGTH_LONG).show();
                Problema problema1 = new Problema(username[0], email[0], pathImg, details, fullNameReporter[0], roomtext,userid);
                Toast.makeText(getApplicationContext(), "ALISA: problemaPATHIMG2222222= " + problema1.getPathImg(), Toast.LENGTH_LONG).show();

                success = problema1.raporteazaProblema(problema1);

                Intent intent1 = new Intent(getBaseContext(), ReportOrCheck.class);
                startActivity(intent1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        } );
    }

    @Override
    public void onClick(View v) {
        if (v == send) {
            details = moreDetails.getText().toString();
            roomtext = room.getText().toString();
            raporteazaProblema();
            Toast.makeText(Report.this, "DETAILS!= "+details, Toast.LENGTH_SHORT).show();




//            if(changed) {


//                FirebaseDatabase.getInstance().getReference("Probleme")
//                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                        .setValue(problema1).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(Report.this, "Problema raportata cu success!", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(getApplicationContext(), StartApp.class));
//                    }
//                });
//                changed = false;
//            }



            new Email().sendEmail(to, subject, mesajMail+details);
            Toast.makeText(Report.this, "Mail trimis!", Toast.LENGTH_SHORT).show();
        }

        if (v == openCamera){
            Intent i = new Intent(this, CameraCapture.class);
            startActivityForResult(i,1);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                pathImg = data.getStringExtra("cameraPathTextValue");
                String strImageName = data.getStringExtra("imageName");
                pathImgText.setText(strImageName);
            }
        }

        if (success)
        {
            Toast.makeText(Report.this, "SUCCEEEEEESSSS", Toast.LENGTH_SHORT).show();

        }
    }
}
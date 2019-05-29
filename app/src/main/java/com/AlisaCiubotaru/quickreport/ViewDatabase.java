package com.AlisaCiubotaru.quickreport;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewDatabase extends AppCompatActivity implements View.OnClickListener {
    private Button btnRezolva;

    private List<Problema> problemaAll;
    private RecyclerView rv;
    private MyAdapter adapter;

    private TextView textView2;
    private TextView textView21;
    private Button rezolvat;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;

    private ListView mListView;

    ArrayList<Problema> listProbleme;
    Problema problema;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_viewdatabase);
        super.onCreate(savedInstanceState);


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Probleme");

        textView2 = (TextView) findViewById(R.id.tvUserInfo);
        textView21 = (TextView) findViewById(R.id.tvUserInfo2);

        btnRezolva = findViewById(R.id.btnmarksolved);
//        btnRezolva.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        listProbleme = new ArrayList<>();
        problema = new Problema();

        rv = (RecyclerView)findViewById(R.id.recyclerview);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        problemaAll = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){

            problema = ds.getValue(Problema.class);
            listProbleme.add(problema);
        }
        adapter = new MyAdapter(listProbleme);
        rv.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
    }
}

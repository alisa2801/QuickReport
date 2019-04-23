package com.AlisaCiubotaru.quickreport;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterUser  extends AppCompatActivity implements View.OnClickListener {

    private Button button_register;
    private EditText fullname;
    private EditText email;
    private EditText username;
    private EditText password;

    private ProgressDialog progressDialog;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeruser);

        progressDialog = new ProgressDialog(this);

        button_register = findViewById(R.id.buttonregister2);
        fullname = findViewById(R.id.fullnameregister);
        email = findViewById(R.id.emailregister);
        username = findViewById(R.id.usernameregister);
        password = findViewById(R.id.passwordregister);

        button_register.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("ReporterUser");
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void registerUser(){
        final String fname = fullname.getText().toString().trim();
        final String mail = email.getText().toString().trim();
        final String user = username.getText().toString().trim();
        final String pass = password.getText().toString().trim();

        if(TextUtils.isEmpty(fname)){
            //empty username
            Toast.makeText(RegisterUser.this,"Please enter Full Name",Toast.LENGTH_SHORT).show();
            //stop the function to execute
//            return;
        }

        if(TextUtils.isEmpty(mail)){
            //empty username
            Toast.makeText(RegisterUser.this,"Please enter Email",Toast.LENGTH_SHORT).show();
            //stop the function to execute
//            return;
        }

        if(TextUtils.isEmpty(user)){
            //empty username
            Toast.makeText(RegisterUser.this,"Please enter Username",Toast.LENGTH_SHORT).show();
            //stop the function to execute
//            return;
        }

        if(TextUtils.isEmpty(pass)){
            //empty password
            Toast.makeText(RegisterUser.this, "Please enter Password containing 1 UPPER letter",Toast.LENGTH_SHORT).show();
//            return;
        }
        //if validations are ok
//        progressDialog.setMessage("Registering user...");
//        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(RegisterUser.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            ReporterUser userReporter = new ReporterUser(
                                    fname, mail, user
                            );

                            FirebaseDatabase.getInstance().getReference("ReporterUser")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(userReporter).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(RegisterUser.this, "Registation Complete! Authenticate with your user!",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(),StartApp.class));
                                }
                            });


                        } else {

                        }

                        // ...
                    }
                });


    }


    @Override
    public void onClick(View v) {
        if (v == button_register){
            registerUser();
        }
    }
}

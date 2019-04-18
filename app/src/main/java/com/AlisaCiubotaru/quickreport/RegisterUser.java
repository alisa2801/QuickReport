package com.AlisaCiubotaru.quickreport;

import android.app.ProgressDialog;
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

public class RegisterUser  extends AppCompatActivity implements View.OnClickListener {

    private Button button_register;
    private EditText username;
    private EditText password;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startapp);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        button_register = (Button) findViewById(R.id.buttonregister2);
        username = (EditText) findViewById(R.id.usernameregister);
        password = (EditText) findViewById(R.id.passwordregister);

        button_register.setOnClickListener(this);
    }

    private void registerUser(){
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(TextUtils.isEmpty(user)){
            //empty username
            Toast.makeText(this,"Please enter username",Toast.LENGTH_SHORT).show();
            //stop the function to execute
            return;
        }

        if(TextUtils.isEmpty(pass)){
            //empty password
            Toast.makeText(this, "Please enter password containing 1 UPPER letter",Toast.LENGTH_SHORT).show();
            return;
        }
        //if validations are ok
        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(user,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            progressDialog.setMessage("Inregistrare reusita");
                            progressDialog.show();
                        }
                        else
                        {
                            progressDialog.setMessage("EROARE inregistrare");
                            progressDialog.show();
                        }
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

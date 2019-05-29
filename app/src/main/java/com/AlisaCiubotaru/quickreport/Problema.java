package com.AlisaCiubotaru.quickreport;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Problema {
//    private String usernameReporter;
//    private String fullNameReporter;
//    private String emailReporter;
//    private String pathImg;
//    private String moreDetails;
//    private String roomReporter;
//    private  String userUuid;
public String usernameReporter;
    public String fullNameReporter;
    public String emailReporter;
    public String pathImg;
    public String moreDetails;
    public String roomReporter;
    public  String userUuid;

//    private ArrayList<String> checkFormatProblema;

    public Problema(String usernameReporter,String emailReporter, String pathImg, String moreDetails,
                    String fullNameReporter, String roomReporter, String userUuid)
    {
        this.usernameReporter = usernameReporter;
        this.fullNameReporter = fullNameReporter;
        this.emailReporter = emailReporter;
        this.pathImg = pathImg;
        this.moreDetails = moreDetails;
        this.roomReporter = roomReporter;
        this.userUuid = userUuid;

//        checkFormatProblema.add(usernameReporter);
//        checkFormatProblema.add(emailReporter);
//        checkFormatProblema.add(pathImg);
//        checkFormatProblema.add(moreDetails);
//        checkFormatProblema.add(fullNameReporter);
//        checkFormatProblema.add(roomReporter);
    }

    public Problema(){}
//    public ArrayList<String> getCheckFormat()
//    {
//        return checkFormatProblema;
//    }

//    public void raporteazaProblema()
//    {
//        Problema problema = new Problema(
//                fname, mail, user
//        );
//        FirebaseDatabase.getInstance().getReference("Probleme")
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                .setValue(userReporter).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Toast.makeText(RegisterUser.this, "Registation Complete! Authenticate with your user!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), StartApp.class));
//
//            }
//        }
//    }



    public boolean raporteazaProblema(Problema problema){
        final boolean[] success = {false};
        UUID uuid = UUID.randomUUID();
        String userChild = uuid.toString();
//        String imgPath = problema.getPathImg();
//        Toast.makeText(this, "IMAGE PATH= "+imgPath, Toast.LENGTH_SHORT).show();
//        System.out.println("IMAge PATHe= "+imgPath);
        FirebaseDatabase.getInstance().getReference("Probleme")

                        //.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child(userChild)
                        .setValue(problema).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        success[0] = true;
                    }
                });
        return success[0];
    }

    public String getUsernameReporter(){
        return usernameReporter;
    }

    public String getEmailReporter() {
        return emailReporter;
    }

    public String getFullNameReporter() {
        return fullNameReporter;
    }

    public String getPathImg(){
        return pathImg;
    }

    public String getMoreDetails(){
        return moreDetails;
    }

    public String getRoomReporter(){
        return roomReporter;
    }

    public String getUserUuid(){return  userUuid;}
}
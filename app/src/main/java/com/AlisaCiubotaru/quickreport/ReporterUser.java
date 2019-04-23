package com.AlisaCiubotaru.quickreport;

public class ReporterUser {
    private String fullName,email,username;

//    public  ReporterUser(){
//
//    }
//
    public ReporterUser(String fullName, String email, String username){
        this.fullName = fullName;
        this.email = email;
        this.username = username;
    }

    public String getFullName(){
        return fullName;
    }

    public String getEmail(){
        return email;
    }

    public String getUsername(){
        return username;
    }
}

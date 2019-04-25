package com.AlisaCiubotaru.quickreport;

public class User {
    private String fullName,email,username;
    private boolean userRole, solverRole;

//    public  User(){
//
//    }
//
    public User(String fullName, String email, String username){
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.userRole = true;
        this.solverRole = false;
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

    public boolean getUserRole() { return userRole; }

    public boolean getSolverRole() { return solverRole; }
}

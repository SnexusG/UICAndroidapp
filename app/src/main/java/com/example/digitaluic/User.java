package com.example.digitaluic;

public class User {
    String userId;
    String userName;
    String userCollege;
    String password;
    String emailID;

    public User(){

    }

    public User(String userId, String userName, String userCollege, String password, String emailID){
        this.userId = userId;
        this.userName = userName;
        this.userCollege = userCollege;
        this.password = password;
        this.emailID = emailID;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserCollege() {
        return userCollege;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailID() {
        return emailID;
    }
}

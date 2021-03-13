package com.mishrasoumitra.parkinglot.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    private String lastName;
    private String firstName;
    private String password;
    private String userType;
    private String sessionId;

//    private int userId;
    private String userName;

    public User() {
    }

    public User(String userName, String lastName, String firstName, String password, String userType) {
//        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.userType = userType;
        this.userName = userName;
        this.sessionId = null;
    }



//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public int getUserId() {
//        return userId;
//    }

//    public void setUserId(int id) {
//        this.userId = id;
//    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String type) {
        this.userType = type;
    }

    @Id
    public String getUserName() {
        return userName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

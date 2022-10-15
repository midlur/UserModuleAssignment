package com.user.request;

import java.io.Serializable;

public class UserDetailsRequest implements Serializable {
    private String userId;
    private String createdOn;
    private String email;
    private String firstName;
    private String secondName;

    public String getUserId() {
        return userId;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public UserDetailsRequest(String userId, String createdOn, String email, String firstName, String secondName) {
        this.userId = userId;
        this.createdOn = createdOn;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public UserDetailsRequest() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}

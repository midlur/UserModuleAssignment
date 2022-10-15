package com.user.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.user.model.Location;

import java.util.Date;
import java.util.UUID;
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserDetailsWithLatestLocationResponse extends BaseResponse{
    private String userId;
    private Date createdOn;
    private String email;
    private String firstName;
    private String secondName;
    private LocationResponse location;
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setCreatedOn(Date createdOn) {
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

    public void setLocation(LocationResponse location) {
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }

    public Date getCreatedOn() {
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

    public LocationResponse getLocation() {
        return location;
    }

}

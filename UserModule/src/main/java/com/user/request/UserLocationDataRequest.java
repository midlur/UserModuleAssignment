package com.user.request;

import com.user.model.Location;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class UserLocationDataRequest implements Serializable {
    private String userId;
    private String createdOn;
    private Location location;

    public String getUserId() {
        return userId;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public Location getLocation() {
        return location;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UserLocationDataRequest(String userId, String createdOn, Location location) {
        this.userId = userId;
        this.createdOn = createdOn;
        this.location = location;
    }

    public UserLocationDataRequest() {
    }
}

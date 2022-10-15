package com.user.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class UserLocationsByTimeRangeResponse extends BaseResponse{

    public String userId;
    public List<Locations> locationsList;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLocationsList(List<Locations> locationsList) {
        this.locationsList = locationsList;
    }

    public static class Locations implements Serializable {
        public Date createdOn;
        public LocationResponse location;
        public void setCreatedOn(Date createdOn) {
            this.createdOn = createdOn;
        }

        public void setLocation(LocationResponse location) {
            this.location = location;
        }
    }

    public String getUserId() {
        return userId;
    }

    public List<Locations> getLocationsList() {
        return locationsList;
    }
}

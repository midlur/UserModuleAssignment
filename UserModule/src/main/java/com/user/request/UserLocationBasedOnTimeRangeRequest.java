package com.user.request;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class UserLocationBasedOnTimeRangeRequest implements Serializable {

    private String userId;
    private String fromDate;
    private String toDate;

    public String getUserId() {
        return userId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public UserLocationBasedOnTimeRangeRequest(String userId, String fromDate, String toDate) {
        this.userId = userId;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public UserLocationBasedOnTimeRangeRequest() {
    }
}

package com.user.service;

import com.user.request.UserDetailsRequest;
import com.user.request.UserLocationBasedOnTimeRangeRequest;
import com.user.request.UserLocationDataRequest;
import com.user.response.BaseResponse;
import com.user.response.UserDetailsWithLatestLocationResponse;
import com.user.response.UserLocationsByTimeRangeResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface IUserService {
    public BaseResponse saveUserLocationData(UserLocationDataRequest request);

    public BaseResponse saveOrUpdateUserDetails(UserDetailsRequest request);

    public UserDetailsWithLatestLocationResponse getLatestLocationWithUserDetails(String id);

    public UserLocationsByTimeRangeResponse getUserLocationsByTimeRange(UserLocationBasedOnTimeRangeRequest request);
}

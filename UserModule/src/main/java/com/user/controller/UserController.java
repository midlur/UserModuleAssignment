package com.user.controller;

import com.user.request.UserDetailsRequest;
import com.user.request.UserLocationBasedOnTimeRangeRequest;
import com.user.request.UserLocationDataRequest;
import com.user.response.BaseResponse;
import com.user.response.UserDetailsWithLatestLocationResponse;
import com.user.response.UserLocationsByTimeRangeResponse;
import com.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("ping")
    public String ping() {
        return "Welcome to Sale Order Controller";
    }

    /**
     * This API will be called in order to create a user if not created yet and add location to the list.
     * @param request
     * @return
     */
    @PostMapping("/locationData")
    public BaseResponse saveUserLocationData(@Valid @RequestBody UserLocationDataRequest request) {
        return userService.saveUserLocationData(request);
    }

    /**
     * This API will be invoked to save/update user details.
     * @param request
     * @return
     */
    @PostMapping("/saveUser")
    public BaseResponse saveOrUpdateUserDetails(@Valid @RequestBody UserDetailsRequest request) {
        return userService.saveOrUpdateUserDetails(request);
    }

    /**
     * This API Fetches the Latest location of the user if present
     *      As the user entity stores all the location data of the user
     *      This api fetches the user on the basis of userID and retrieves its location with the greatest CreatedOn value.
     * @param id
     * @return
     */
    @GetMapping("/latestLocation/{id}")
    public UserDetailsWithLatestLocationResponse getLatestLocationWithUserDetails(@PathVariable("id") String id) {
        return userService.getLatestLocationWithUserDetails(id);
    }

    /**
     * This API fetches locations on the basis of userId and TimeRange provided.
     * @param request
     * @return
     */
    @GetMapping("/userLocationsByTimeRange")
    public UserLocationsByTimeRangeResponse getUserLocationsByTimeRange(@Valid @RequestBody UserLocationBasedOnTimeRangeRequest request) {
        return userService.getUserLocationsByTimeRange(request);
    }
}

package com.user.service.impl;

import com.user.model.Location;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.request.UserDetailsRequest;
import com.user.request.UserLocationBasedOnTimeRangeRequest;
import com.user.request.UserLocationDataRequest;
import com.user.response.BaseResponse;
import com.user.response.LocationResponse;
import com.user.response.UserDetailsWithLatestLocationResponse;
import com.user.response.UserLocationsByTimeRangeResponse;
import com.user.service.IUserService;
import com.user.helper.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.user.model.User.getLocationComparator;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Fetch the user if already existing and modify the Location List
     *      In this case just the location's created on will be saved
     *      User's createdOn will not be modified.
     * If User not already created, then New User will be created
     *      In this case createdOn for location and user will be the same.
     * @param request
     * @return
     */
    public BaseResponse saveUserLocationData(UserLocationDataRequest request){
        Optional<User> existingUser = userRepository.findById(request.getUserId());
        if(existingUser.isPresent()){
            List<Location> locationList = existingUser.get().getLocation();
            Location location = request.getLocation();
            Location locationToBeAdded = new Location();
            locationToBeAdded.setLatitude(location.getLatitude());
            locationToBeAdded.setLongitude(location.getLongitude());
            locationToBeAdded.setCreatedOn(UtilityClass.parseDate(request.getCreatedOn()));
            locationToBeAdded.setUser(existingUser.get());
            locationList.add(locationToBeAdded);
            userRepository.save(existingUser.get());
        } else{
            User user = new User();
            user.setUserId(request.getUserId());
            user.setCreatedOn(UtilityClass.parseDate(request.getCreatedOn()));
            List<Location> locationList = new ArrayList<>();
            Location userLocation = new Location();
            userLocation.setCreatedOn(UtilityClass.parseDate(request.getCreatedOn()));
            userLocation.setLongitude(request.getLocation().getLongitude());
            userLocation.setLatitude(request.getLocation().getLatitude());
            userLocation.setUser(user);
            locationList.add(userLocation);
            user.setLocation(locationList);
            userRepository.save(user);
        }
        return new BaseResponse("User Location saved successfully", 200);
    }

    /**
     * If User Already exists
     *      Update the other details
     * else
     *      Create a new user with all the User details
     * @param request
     * @return
     */
    @Override
    public BaseResponse saveOrUpdateUserDetails(UserDetailsRequest request) {
        Optional<User> existingUser = userRepository.findById(request.getUserId());
        if(existingUser.isPresent()){
            existingUser.get().setEmail(request.getEmail());
            existingUser.get().setFirstName(request.getFirstName());
            existingUser.get().setSecondName(request.getSecondName());
            userRepository.save(existingUser.get());
        }else {
            User user = new User();
            user.setUserId(request.getUserId());
            user.setCreatedOn(UtilityClass.parseDate(request.getCreatedOn()));
            user.setEmail(request.getEmail());
            user.setFirstName(request.getFirstName());
            user.setSecondName(request.getSecondName());
            userRepository.save(user);
        }
        return new BaseResponse("User Details saved successfully", 200);
    }

    /**
     * Given the userId
     * If the User Exists and user has at least one location stored
     *      Fetch all the user Locations and sort on the basis of createdOn in desc order and get the latest
     * If User does not exists
     *      404 response is sent
     * @param id
     * @return
     */
    @Override
    public UserDetailsWithLatestLocationResponse getLatestLocationWithUserDetails(String id) {
        Optional<User> existingUser = userRepository.findById(id);
        UserDetailsWithLatestLocationResponse response = new UserDetailsWithLatestLocationResponse();
        if(existingUser.isPresent() && existingUser.get().getLocation().stream().findFirst().isPresent()){
            response.setEmail(existingUser.get().getEmail());
            response.setSecondName(existingUser.get().getSecondName());
            response.setFirstName(existingUser.get().getFirstName());
            response.setUserId(existingUser.get().getUserId());
            LocationResponse locationResponse = new LocationResponse();
            Location location = existingUser.get().getLocation().stream().sorted(getLocationComparator()).findFirst().get();
            locationResponse.setLatitude(location.getLatitude());
            locationResponse.setLongitude(location.getLongitude());
            response.setLocation(locationResponse);
            response.setCreatedOn(location.getCreatedOn());
        } else {
            response.setMessage("User Or User Location does not exist");
            response.setCode(404);
        }
        return response;
    }

    /**
     * If the given userId exists
     *      fetch all the locations of tha user and response is prepared
     * If user does not exists
     *      404 response is sent
     * @param request
     * @return
     */
    @Override
    public UserLocationsByTimeRangeResponse getUserLocationsByTimeRange(UserLocationBasedOnTimeRangeRequest request) {
        Optional<User> user = userRepository.findByUserIdAndCreatedOnBetween(request.getUserId(),UtilityClass.parseDate(request.getFromDate()),UtilityClass.parseDate(request.getToDate()));
        UserLocationsByTimeRangeResponse response = new UserLocationsByTimeRangeResponse();
        if(user.isPresent()){
            response.setUserId(user.get().getUserId());
            List<UserLocationsByTimeRangeResponse.Locations> locationsList = new ArrayList<>();
            for(Location userLocation : user.get().getLocation()){
                UserLocationsByTimeRangeResponse.Locations locations = new UserLocationsByTimeRangeResponse.Locations();
                LocationResponse location = new LocationResponse();
                location.setLatitude(userLocation.getLatitude());
                location.setLongitude(userLocation.getLongitude());
                locations.setLocation(location);
                locations.setCreatedOn(userLocation.getCreatedOn());
                locationsList.add(locations);
            }
            response.setLocationsList(locationsList);
        } else {
            response.setMessage("User Or User Location does not exist within the given time range");
            response.setCode(404);
        }
        return response;
    }
}

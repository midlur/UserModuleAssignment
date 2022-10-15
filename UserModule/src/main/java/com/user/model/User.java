package com.user.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.*;

/**
 * A User Entity which stores all the relevant information of a user i.e UserId, FirstName, SecondName, Email, CreatedOn (Time when user was created)
 * and list of locations
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name="user_id")
    private String userId;
    @Column(name="created_on")
    private Date createdOn;
    @Email
    private String email;
    @Column(name="first_name")
    private String firstName;
    @Column(name="second_name")
    private String secondName;
    @OneToMany(targetEntity = Location.class,fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Location> location = new ArrayList<>();

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

    public static Comparator<Location> getLocationComparator() {
        return Comparator.comparing(Location::getCreatedOn).reversed();
    }

    public User(String userId, Date createdOn, String email, String firstName, String secondName, List<Location> location) {
        this.userId = userId;
        this.createdOn = createdOn;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.location = location;
    }

    public User(String userId,Date createdOn, String email, String firstName, String secondName) {
        this.userId = userId;
        this.email = email;
        this.createdOn = createdOn;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}

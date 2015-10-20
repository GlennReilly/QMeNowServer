package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.UserStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 1/09/15.
 */
public class User {
    private UserCredentials userCredentials = new UserCredentials();
    private static UserStore userStore = new UserStore();
    private int id;
    private int businessId;
    private String firstName = "";
    private String lastName = "";
    private String name = "";
    private String phoneNumber = "";
    private String emailAddress = "";
    private String physicalAddress = "";

    public static User get(UserCredentials userCredentials) {
        return userStore.get(userCredentials);
    }

    public static boolean validateCredentials(UserCredentials userCredentials) {
        if(userStore.validateCredentials(userCredentials)) {
            return true;
        }
        else{
            return false;
        }
    }

    public List<User> getAllUsers(boolean active){
        List<User> userList = new ArrayList<>();
        userList = userStore.getAll(active);
         return  userList;
    }

    public void save() {
        userStore.saveNew(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getName() {
        return getFirstName() + " " + getLastName();
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.userCredentials.getUsername();
    }

    public void setUsername(String username) {
        this.userCredentials.setUsername(username);
    }

    public String getPassword() {
        return this.userCredentials.getPassword();
    }

    public void setPassword(String password) {
        this.userCredentials.setPassword(password);
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }


}

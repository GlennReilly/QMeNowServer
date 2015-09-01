package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.UserStore;

/**
 * Created by glenn on 1/09/15.
 */
public class User {
    private UserCredentials userCredentials = new UserCredentials();
    private int customerId;
    private String firstName = "";
    private String lastName = "";
    private String phoneNumber = "";
    private String emailAddress = "";
    private String physicalAddress = "";


    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void save() {
        UserStore userStore = new UserStore();
        userStore.saveNew(this);
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

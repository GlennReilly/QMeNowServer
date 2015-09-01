package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.CustomerStore;

/**
 * Created by glenn on 31/08/15.
 */
public class Customer {
    private String businessName = "";
    private String contactName = "";
    private String phoneNumber = "";
    private String emailAddress = "";
    private String username = "";
    private String password = "";
    private String physicalAddress = "";


    public void save() {
        CustomerStore customerStore = new CustomerStore();
        customerStore.saveNew(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}

package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.CustomerStore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by glenn on 1/09/15.
 */
public class Customer {
    private static CustomerStore customerStore = new CustomerStore();
    private int id;
    private String firstName = "";
    private String lastName = "";
    private String name = "";
    private String phoneNumber = "";
    private String emailAddress = "";
    private String physicalAddress = "";
    private Gender gender;
    private Date DOB;
    private int businessId;

    public Customer() {}


    public Customer(int id) {
        this.id = id;
    }


    public static Customer get(int customerId) {
        return customerStore.get(customerId);
    }


/*
    public List<Customer> getAllCustomers(boolean active){
        List<Customer> customerList = new ArrayList<>();
        customerList = customerStore.getAll(active);
        return  customerList;
    }*/

    public int saveNew() {
        int newCustomerId = customerStore.saveNew(this);
        return newCustomerId;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
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


    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Date getDOB() {
        return DOB;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public enum Gender {NOT_SPECIFIED, MALE, FEMALE,}

}

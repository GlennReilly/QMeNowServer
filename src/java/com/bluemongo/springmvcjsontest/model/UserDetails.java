package com.bluemongo.springmvcjsontest.model;

/**
 * Created by glenn on 8/10/15.
 */
public class UserDetails {
    String firstName;
    String lastName;
    String customerId;

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

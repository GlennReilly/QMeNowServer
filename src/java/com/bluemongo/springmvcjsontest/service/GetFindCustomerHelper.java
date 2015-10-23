package com.bluemongo.springmvcjsontest.service;

/**
 * Created by glenn on 22/10/15.
 */
public class GetFindCustomerHelper {
    String customerIdStr;
    String firstName;
    String lastName;


    public String getCustomerIdStr() {
        return customerIdStr;
    }

    public void setCustomerIdStr(String customerId) {
        this.customerIdStr = customerId;
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
}

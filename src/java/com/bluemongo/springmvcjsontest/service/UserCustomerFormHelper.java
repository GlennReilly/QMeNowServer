package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.Customer;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.model.UserCredentials;
import com.bluemongo.springmvcjsontest.persistence.CustomerStore;
import com.bluemongo.springmvcjsontest.persistence.UserStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 31/08/15.
 */
public class UserCustomerFormHelper {
    private User user;
    private List<Customer> activeCustomers = new ArrayList<>();
    private List<User> activeUsers = new ArrayList<>();
    private int selectedCustomerId;

    public void setSelectedCustomerId(int selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }

    public int getSelectedCustomerId() {
        return selectedCustomerId;
    }

    public List<Customer> getActiveCustomers() {
        activeCustomers = getCustomers();
        return activeCustomers;
    }

    public void setActiveCustomers() {
        this.activeCustomers = getCustomers();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public void save() {
        user.setCustomerId(selectedCustomerId);
        user.save();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    private List<Customer> getCustomers(boolean isActive){
        CustomerStore customerStore = new CustomerStore();
        return customerStore.getAll(isActive);
    }

    private List<Customer> getCustomers(){
        return getCustomers(true);
    }
}

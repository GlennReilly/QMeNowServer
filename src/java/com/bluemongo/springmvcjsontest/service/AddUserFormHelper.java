package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.BusinessStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 31/08/15.
 */
public class AddUserFormHelper {
    private User user;
    private List<Business> activeBusinesses = new ArrayList<>();
    private int selectedCustomerId;

    public void setSelectedCustomerId(int selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }

    public int getSelectedCustomerId() {
        return selectedCustomerId;
    }

    public List<Business> getActiveBusinesses() {
        activeBusinesses = getCustomers();
        return activeBusinesses;
    }

    public void setActiveCustomers() {
        this.activeBusinesses = getCustomers();
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

    private List<Business> getCustomers(boolean isActive){
        BusinessStore businessStore = new BusinessStore();
        return businessStore.getAll(isActive);
    }

    private List<Business> getCustomers(){
        return getCustomers(true);
    }
}

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
    private int selectedBusinessId;

/*    public AddUserFormHelper(int selectedBusinessId) {
        this.selectedBusinessId = selectedBusinessId;
    }*/

    public int getSelectedBusinessId() {
        return selectedBusinessId;
    }

    public void setSelectedBusinessId(int selectedBusinessId) {
        this.selectedBusinessId = selectedBusinessId;
    }

    public List<Business> getActiveBusinesses() {
        activeBusinesses = getBusinesses();
        return activeBusinesses;
    }

    public void setActiveBusinesses() {
        this.activeBusinesses = getBusinesses();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void save() {
        user.setBusinessId(selectedBusinessId);
        user.save();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    private List<Business> getBusinesses(boolean isActive){
        BusinessStore businessStore = new BusinessStore();
        return businessStore.getAll(isActive);
    }

    private List<Business> getBusinesses(){
        return getBusinesses(true);
    }
}

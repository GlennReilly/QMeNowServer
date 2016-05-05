package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.BusinessStore;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by glenn on 31/08/15.
 */
@Component
public class AddUserFormHelper implements Validator {

    private User newUser = new User();
    private List<Business> activeBusinesses = new ArrayList<>();
    private int selectedBusinessId;



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


    public User getNewUser() {
        return newUser;
    }

    public void setNewUser(User newUser) {
        this.newUser = newUser;
    }


    public void save() {
        newUser.setBusinessId(selectedBusinessId);
        newUser.save();
    }

    @NotNull(message="please enter a newUser first name")
    public String getFirstName() {
        return newUser.getFirstName();
    }

    public String getLastName() {
        return newUser.getLastName();
    }

    private List<Business> getBusinesses(boolean isActive){
        BusinessStore businessStore = new BusinessStore();
        return businessStore.getAll(isActive);
    }

    private List<Business> getBusinesses(){
        return getBusinesses(true);
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return AddUserFormHelper.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newUser.firstName", "", "field required");
    }
}

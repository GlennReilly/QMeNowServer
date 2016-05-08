package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.LocationStore;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by glenn on 13/10/15.
 */
public class Location implements Validator {
    int id;
    String name;
    String backgroundColourHexCode;
    int businessId;
    private boolean isDefault;

    private LocationStore locationStore = new LocationStore();

    public void save(){
        locationStore.saveNew(this);
    }

    public String getName() {
        if(name == null){
            name = "";
        }
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getBusinessId(){
        return businessId;
    }

    public String getBackgroundColourHexCode() {
        return backgroundColourHexCode;
    }

    public void setBackgroundColourHexCode(String backgroundColourHexCode) {
        this.backgroundColourHexCode = backgroundColourHexCode;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Location.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "field required");
    }
}

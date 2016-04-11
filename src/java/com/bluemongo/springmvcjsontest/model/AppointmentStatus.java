package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.AppointmentStatusStore;

/**
 * Created by glenn on 16/10/15.
 */
public class AppointmentStatus  {
    private int id;
    private String name;
    private int businessId;
    AppointmentStatusStore store = new AppointmentStatusStore();
    private String backgroundColourHexCode;
    private int sequenceNumber;
    private boolean customerInitiated;
    private boolean isDefault;

    public int saveNew() {
        int newId = store.saveNew(this);
        return newId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBackgroundColourHexCode(String backgroundColourHexCode) {
        this.backgroundColourHexCode = backgroundColourHexCode;
    }

    public String getBackgroundColourHexCode() {
        return backgroundColourHexCode;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public boolean isCustomerInitiated() {
        return customerInitiated;
    }

    public void setCustomerInitiated(boolean customerInitiated) {
        this.customerInitiated = customerInitiated;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}

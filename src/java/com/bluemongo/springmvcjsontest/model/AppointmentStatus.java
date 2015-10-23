package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.AppointmentStatusStore;

/**
 * Created by glenn on 16/10/15.
 */
public class AppointmentStatus  {
    private int id;
    private String statusName;
    private int businessId;
    AppointmentStatusStore store = new AppointmentStatusStore();

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

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getBusinessId() {
        return businessId;
    }
}

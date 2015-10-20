package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.AppointmentStatusStore;

/**
 * Created by glenn on 16/10/15.
 */
public class AppointmentStatus  {
    int id;
    int customerId;
    String statusName;

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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}

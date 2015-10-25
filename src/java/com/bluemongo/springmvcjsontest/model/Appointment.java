package com.bluemongo.springmvcjsontest.model;


import com.bluemongo.springmvcjsontest.persistence.AppointmentStore;
import utils.InputHelper;

import java.util.Date;

/**
 * Created by glenn on 5/10/15.
 */
public class Appointment {
    private int id;
    private String messageToCustomer;
    private Date appointmentDate;
    private int locationId;
    private int customerId;
    private int appointmentTypeId;
    private int status;
    private boolean isComplete;
    private AppointmentStore appointmentStore = new AppointmentStore();


    public Appointment(){}
    public Appointment(int id){
        this.id = id;
    }

    public int saveNew() {
        int newId = appointmentStore.saveNew(this);
        return newId;
    }


    public String getAppointmentDateString8601() {
        return InputHelper.getISO8601StringFromDate(appointmentDate);
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getMessageToCustomer() {
        return messageToCustomer;
    }

    public void setMessageToCustomer(String messageToCustomer) {
        this.messageToCustomer = messageToCustomer;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public int getAppointmentTypeId() {
        return appointmentTypeId;
    }

    public void setAppointmentTypeId(int appointmentTypeId) {
        this.appointmentTypeId = appointmentTypeId;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
}
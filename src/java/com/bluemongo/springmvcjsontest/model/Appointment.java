package com.bluemongo.springmvcjsontest.model;


import com.bluemongo.springmvcjsontest.persistence.AppointmentStore;
import utils.InputHelper;

import java.util.Date;

/**
 * Created by glenn on 5/10/15.
 */
public class Appointment {
    private String messageToUser;
    private Date appointmentDate;
    private int locationId;
    private int userId;
    String appointmentType;

    private AppointmentStore appointmentStore = new AppointmentStore();

    public int save() {
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

    public String getMessageToUser() {
        return messageToUser;
    }

    public void setMessageToUser(String messageToUser) {
        this.messageToUser = messageToUser;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }
}
package com.bluemongo.springmvcjsontest.model;

import utils.ISO8601DateParser;

import java.util.Date;

/**
 * Created by glenn on 5/10/15.
 */
public class Appointment {
    String messageToUser;
    Date appointmentDate;
    String location;

    public String getAppointmentDate8601() {
        return ISO8601DateParser.toString(appointmentDate);
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessageToUser() {
        return messageToUser;
    }

    public void setMessageToUser(String messageToUser) {
        this.messageToUser = messageToUser;
    }
}
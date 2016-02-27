package com.bluemongo.springmvcjsontest.model;

import java.util.Date;

import utils.InputHelper;

/**
 * Created by glenn on 14/02/16.
 */
public class AppointmentCheckInDTO {
    private transient Date checkInDateTime;
    private String checkInDateTimeString;
    private int appointmentId;
    private int customerId;

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(Date checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
        this.checkInDateTimeString = InputHelper.getISO8601StringFromDate(checkInDateTime);
    }

    public String getCheckInDateTimeString() {
        return checkInDateTimeString;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

package com.bluemongo.springmvcjsontest.model;

import java.util.List;

/**
 * Created by glenn on 13/12/15.
 */
public class AppointmentsResponse {
    private List<Appointment> appointmentList;
    private String appointmentCreationURL;
    private List<AppointmentStatus> appointmentStatusList;

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentCreationURL(String appointmentCreationURL) {
        this.appointmentCreationURL = appointmentCreationURL;
    }

    public String getAppointmentCreationURL() {
        return appointmentCreationURL;
    }

    public List<AppointmentStatus> getAppointmentStatusList() {
        return appointmentStatusList;
    }

    public void setAppointmentStatusList(List<AppointmentStatus> appointmentStatusList) {
        this.appointmentStatusList = appointmentStatusList;
    }
}

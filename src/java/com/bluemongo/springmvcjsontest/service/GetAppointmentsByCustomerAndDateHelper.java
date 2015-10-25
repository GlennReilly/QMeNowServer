package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.Appointment;

import java.util.List;

/**
 * Created by glenn on 21/10/15.
 */
public class GetAppointmentsByCustomerAndDateHelper {
    String strFromDate;
    String strToDate;
    int businessId;
    List<Appointment> appointmentList;


    public String getStrFromDate() {
        return strFromDate;
    }

    public void setStrFromDate(String strFromDate) {
        this.strFromDate = strFromDate;
    }

    public String getStrToDate() {
        return strToDate;
    }

    public void setStrToDate(String strToDate) {
        this.strToDate = strToDate;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}

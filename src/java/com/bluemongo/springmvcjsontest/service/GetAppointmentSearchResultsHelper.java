package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.persistence.AppointmentStore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by glenn on 24/10/15.
 */
public class GetAppointmentSearchResultsHelper {
    List<AppointmentAndCustomer> appointmentResultList = new ArrayList<>();
    AppointmentStore appointmentStore = new AppointmentStore();
    String strFromDate;
    String strToDate;
    Date fromDate;
    Date toDate;
    int customerId;
    int businessId;
    Boolean isComplete;


    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public void setStrFromDate(String strFromDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            fromDate = sdf.parse(strFromDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.strFromDate = strFromDate;
    }

    public String getStrFromDate() {
        return strFromDate;
    }

    public String getStrToDate() {
        return strToDate;
    }

    public void setStrToDate(String strToDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            toDate = sdf.parse(strToDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.strToDate = strToDate;
    }

    public void generateSearchResults(){
        appointmentResultList = appointmentStore.getAppointmentsAndCustomer(businessId, customerId, fromDate, toDate, isComplete);
    }

    public List<AppointmentAndCustomer> getAppointmentResultList() {
        return appointmentResultList;
    }

    public void setAppointmentResultList(List<AppointmentAndCustomer> appointmentResultList) {
        this.appointmentResultList = appointmentResultList;
    }

    public AppointmentStore getAppointmentStore() {
        return appointmentStore;
    }

    public void setAppointmentStore(AppointmentStore appointmentStore) {
        this.appointmentStore = appointmentStore;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

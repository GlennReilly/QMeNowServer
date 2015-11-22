package com.bluemongo.springmvcjsontest.model;


import com.bluemongo.springmvcjsontest.persistence.*;
import org.apache.commons.lang3.*;
import utils.InputHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by glenn on 5/10/15.
 */
public class Appointment {
    private int id;
    private String messageToCustomer;
    private String strAppointmentDate;
    private String strAppointmentTime;
    private Date appointmentDate;
    private int locationId;
    private int customerId;
    private int appointmentTypeId;
    private int status;
    private boolean isComplete;
    private AppointmentStore appointmentStore = new AppointmentStore();
    private String locationName;
    private String appointmentTypeName;


    public Appointment(){}
    public Appointment(int id){
        this.id = id;
    }

    public int saveNew() {
        int newId = appointmentStore.saveNew(this);
        return newId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppointmentDateString8601() {
        return InputHelper.getISO8601StringFromDate(appointmentDate);
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
        setStrAppointmentDate(appointmentDate);
    }

    public String getStrAppointmentDate() {
        return strAppointmentDate;
    }

    public void setStrAppointmentDate(Date appointmentDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.strAppointmentDate = sdf.format(appointmentDate);
    }

    public void setStrAppointmentDate(String strAppointmentDate) {
        this.strAppointmentDate = strAppointmentDate;
        checkAndSetAppointmentDateAndTime(strAppointmentDate);

    }

    private void checkAndSetAppointmentDateAndTime(String strAppointmentDate) {
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");

        if (!StringUtils.isBlank(this.strAppointmentDate) && !StringUtils.isBlank(this.strAppointmentTime)) {
            try {
                this.setAppointmentDate(sdf.parse(this.strAppointmentDate + " " + this.strAppointmentTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStrAppointmentTime() {
        return strAppointmentTime;
    }

    public void setStrAppointmentTime(String strAppointmentTime) {
        this.strAppointmentTime = strAppointmentTime;
        checkAndSetAppointmentDateAndTime(strAppointmentDate);
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
        Location location = new LocationStore().get(this.locationId);
        locationName = "";
        if(location != null) {
            locationName = location.getName();
        }
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
        Customer customer = new CustomerStore().get(this.customerId);
        AppointmentType appointmentType = null;
        if(customer != null) {
            appointmentType = new AppointmentTypeStore().get(customer.getBusinessId(), this.appointmentTypeId);
        }
        this.appointmentTypeName = appointmentType != null? appointmentType.getName(): "";
    }

    public String getAppointmentTypeName() {

        return this.appointmentTypeName;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public int saveUpdate() {
        int updatedId = appointmentStore.saveUpdate(this);
        return updatedId;
    }

    public String getLocationName() {
        return locationName;
    }




}
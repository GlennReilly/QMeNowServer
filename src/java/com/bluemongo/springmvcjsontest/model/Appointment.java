package com.bluemongo.springmvcjsontest.model;


import com.bluemongo.springmvcjsontest.persistence.*;
import org.apache.commons.lang3.*;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import utils.InputHelper;

import javax.validation.constraints.Min;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by glenn on 5/10/15.
 */
public class Appointment implements Validator{
    private int id;
    private String messageToCustomer;
    private String strAppointmentDate;
    private String strAppointmentTime;
    private transient Date appointmentDate;

    @Min(value = 1, message = "Please select a location")
    private int locationId;
    private String locationName;

    private int customerId;

    private int appointmentTypeId;

    private int status;
    private String statusName;

    private boolean isComplete;
    private transient AppointmentStore appointmentStore = new AppointmentStore();

    private String appointmentTypeName;
    private String appointmentTypePrefix;

    private String statusHexCode;
    private String appTypeHexCode;
    private String locationHexCode;
    private transient Date checkInDate;
    private String strCheckInDateTime;


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
        setStrAppointmentDateAndTime(appointmentDate);
    }

    public void updateAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setStrAppointmentDateAndTime(Date appointmentDate) {
        setStrAppointmentDate(appointmentDate);
        setStrAppointmentTime(appointmentDate);
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
        updateAppointmentDateAndTimeFromStrings();
    }

    public String getStrAppointmentTime() {
        return strAppointmentTime;
    }

    private void setStrAppointmentTime(Date appointmentDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String timeString  = sdf.format(appointmentDate);
        setStrAppointmentTime(timeString);
    }

    public void setStrAppointmentTime(String strAppointmentTime) {
        this.strAppointmentTime = strAppointmentTime;
        updateAppointmentDateAndTimeFromStrings();
    }

    private Date updateAppointmentDateAndTimeFromStrings() {

        if (!StringUtils.isBlank(this.strAppointmentDate) && StringUtils.isBlank(this.strAppointmentTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            try {
                this.updateAppointmentDate(sdf.parse(this.strAppointmentDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (!StringUtils.isBlank(this.strAppointmentDate) && !StringUtils.isBlank(this.strAppointmentTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
            try {
                this.updateAppointmentDate(sdf.parse(this.strAppointmentDate + " " + this.strAppointmentTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return this.getAppointmentDate();
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
        AppointmentStatus appointmentStatus = new AppointmentStatusStore().get(status);
        this.statusName = appointmentStatus.getName();
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
        this.appointmentTypePrefix = appointmentType != null? appointmentType.getPrefix(): "";
    }

    public String getAppointmentTypeName() {

        return this.appointmentTypeName;
    }

    public String getAppointmentTypePrefix() {
        return appointmentTypePrefix;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public boolean getIsComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public int saveUpdate() {
        if (getIsComplete()) {
            int  updatedId = appointmentStore.setCompleted(this);
            return updatedId;
        } else {
            int  updatedId = appointmentStore.saveUpdate(this);
            return updatedId;
        }
    }

    public String getLocationName() {
        return locationName;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getLocationHexCode() {
        return locationHexCode;
    }

    public void setLocationHexCode(String locationHexCode) {
        this.locationHexCode = locationHexCode;
    }

    public String getAppTypeHexCode() {
        return appTypeHexCode;
    }

    public void setAppTypeHexCode(String appTypeHexCode) {
        this.appTypeHexCode = appTypeHexCode;
    }

    public String getStatusHexCode() {
        return statusHexCode;
    }

    public void setStatusHexCode(String statusHexCode) {
        this.statusHexCode = statusHexCode;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
        if(checkInDate != null) {
            this.setStrCheckInDateTime(InputHelper.getISO8601StringFromDate(checkInDate));
            this.saveUpdate();
        }
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public String getStrCheckInDateTime() {
        String returnVal = "";
        if (checkInDate != null) {
            returnVal = InputHelper.getISO8601StringFromDate(checkInDate);
        }
        return returnVal;
    }


    public void setStrCheckInDateTime(String strCheckInDateTime) {
        this.strCheckInDateTime = strCheckInDateTime;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Appointment.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "appointment.strAppointmentDate", "", "field required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "appointment.strAppointmentTime", "", "field required");
        Appointment appointment = (Appointment) o;
        if (appointment.locationId < 1){
            errors.rejectValue("appointment.locationId",null, "field required");
        }

        if (appointment.status < 1){
            errors.rejectValue("appointment.status",null, "field required");
        }

        if (appointment.appointmentTypeId < 1){
            errors.rejectValue("appointment.appointmentTypeId",null, "field required");
        }

    }
}
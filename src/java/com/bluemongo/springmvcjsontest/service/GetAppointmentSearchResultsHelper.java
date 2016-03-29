package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.persistence.AppointmentStore;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

            if(StringUtils.isEmpty(strFromDate)){
                Date date = new Date();                      // timestamp now
                Calendar cal = Calendar.getInstance();       // get calendar instance
                cal.setTime(date);                           // set cal to date
                cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
                cal.set(Calendar.MINUTE, 0);                 // set minute in hour
                cal.set(Calendar.SECOND, 0);                 // set second in minute
                cal.set(Calendar.MILLISECOND, 0);            // set millis in second
                fromDate = cal.getTime();             // actually computes the new Date
            }else {
                fromDate = sdf.parse(strFromDate);
            }
            this.strFromDate = sdf.format(fromDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
            if(StringUtils.isEmpty(strToDate)){
                Calendar nextDayCal = Calendar.getInstance();
                nextDayCal.setTime(getFromDate());
                nextDayCal.add(Calendar.DATE, 1);
                nextDayCal.add(Calendar.HOUR_OF_DAY, 23);
                nextDayCal.add(Calendar.MINUTE, 59);
                nextDayCal.add(Calendar.SECOND, 59);

                toDate = toDate == null ? nextDayCal.getTime() : toDate;
            }
            else{
                toDate = sdf.parse(strToDate);
                Calendar toDateCal = Calendar.getInstance();
                toDateCal.setTime(toDate);
                //toDateCal.add(Calendar.DATE, 1);
                toDateCal.add(Calendar.HOUR_OF_DAY, 23);
                toDateCal.add(Calendar.MINUTE, 59);
                toDateCal.add(Calendar.SECOND, 59);
                toDate = toDateCal.getTime();
            }
            this.strToDate = sdf.format(toDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
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
        fromDate = fromDate ==  null ? Calendar.getInstance().getTime(): fromDate;
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        Calendar nextDayCal = Calendar.getInstance();
        nextDayCal.setTime(getFromDate());
        nextDayCal.add(Calendar.DATE, 1);
        toDate = toDate == null ? nextDayCal.getTime() : toDate;
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

package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.Appointment;
import com.bluemongo.springmvcjsontest.model.Customer;
import com.bluemongo.springmvcjsontest.model.Location;
import com.bluemongo.springmvcjsontest.persistence.CustomerStore;
import com.bluemongo.springmvcjsontest.persistence.LocationStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 12/10/15.

 */
public class AddAppointmentFormHelper {
    private int userId = -1;
    private int businessId = -1;
    Appointment appointment;
    private int selectedUserId;
    //private List<Customer> activeCustomers = new ArrayList<>();
    private List<Location> activeLocations = new ArrayList<>();
    private List<Customer> customersList;

    public AddAppointmentFormHelper(){}
    public AddAppointmentFormHelper(int userId, int businessId){
        this.userId = userId;
        this.businessId = businessId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int saveNew() {
        appointment.setUserId(selectedUserId);
        int newAppointmentId = appointment.saveNew();
        return newAppointmentId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

/*    public List<Customer> getActiveCustomers() {
        CustomerStore customerStore = new CustomerStore();
        return customerStore.getAll(businessId, true);
    }*/

    public List<Location> getActiveLocations() {
        LocationStore locationStore = new LocationStore();
        activeLocations = locationStore.getAll(businessId, true);
        return activeLocations;
    }

    public int getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(int selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public void setCustomersList(List<Customer> customersList) {
        this.customersList = customersList;
    }

    public List<Customer> getCustomersList() {
        return customersList;
    }

}

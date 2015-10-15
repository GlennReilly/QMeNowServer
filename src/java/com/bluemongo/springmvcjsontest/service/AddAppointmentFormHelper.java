package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.Appointment;
import com.bluemongo.springmvcjsontest.model.Customer;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.CustomerStore;
import com.bluemongo.springmvcjsontest.persistence.UserStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 12/10/15.

 */
public class AddAppointmentFormHelper {
    Appointment appointment;
    private int selectedUserId;
    private List<Customer> activeUsers = new ArrayList<>();

    public void save() {
        appointment.setUserId(selectedUserId);
        appointment.save();
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public List<Customer> getActiveUsers() {
        CustomerStore customerStore = new CustomerStore();
        return customerStore.getAll(true);
    }


    public int getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(int selectedUserId) {
        this.selectedUserId = selectedUserId;
    }
}

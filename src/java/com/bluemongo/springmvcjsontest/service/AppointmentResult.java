package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.Appointment;
import com.bluemongo.springmvcjsontest.model.Customer;

/**
 * Created by glenn on 25/10/15.
 */
public class AppointmentResult {
    Appointment appointment;
    Customer customer;

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

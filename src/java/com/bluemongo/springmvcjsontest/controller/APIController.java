package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.api.AppointmentServiceAPI;
import com.bluemongo.springmvcjsontest.model.*;
import com.bluemongo.springmvcjsontest.persistence.AppointmentStore;
import com.bluemongo.springmvcjsontest.persistence.BusinessStore;
import com.bluemongo.springmvcjsontest.persistence.CustomerStore;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by glenn on 6/12/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/api/v1")
public class APIController implements AppointmentServiceAPI {


    //http://10.1.1.7:8080/FlexibleUIConfig/api/v1/AppointmentsToday/7
    @Override
    @RequestMapping(value = "/AppointmentsToday/{customerId}", method = RequestMethod.GET)
    public AppointmentsResponse getAppointmentsToday(@PathVariable Integer customerId) {
        //public List<Appointment> getAppointmentsToday(@PathVariable Integer customerId) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date fromDate = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date toDate = cal.getTime();
        List<Appointment> appointments;
        appointments = new AppointmentStore().get(customerId, fromDate, toDate, false);
        AppointmentsResponse appointmentsResponse = new AppointmentsResponse();
        appointmentsResponse.setAppointmentList(appointments);
        return appointmentsResponse;

    }

    //http://10.1.1.7:8080/FlexibleUIConfig/api/v1/AppointmentsTodayTest/7
    @Override
    @RequestMapping(value = "/AppointmentsTodayTest/{customerId}", method = RequestMethod.GET)
    public List<Appointment> getAppointmentsTest(@PathVariable Integer customerId) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date fromDate = cal.getTime();
        cal.add(Calendar.DATE, 1);
        Date toDate = cal.getTime();
        List<Appointment> appointments;
        appointments = new AppointmentStore().get(customerId, fromDate, toDate, false);
        return appointments;
    }

    //http://10.1.1.7:8080/FlexibleUIConfig/api/v1/AppointmentsTodayTest/7

    @RequestMapping(value = "/AppointmentsTodayTest2/{customerId}", method = RequestMethod.GET)
    public List<AppointmentTest> getAppointmentsTest2(@PathVariable Integer customerId) {
        List<AppointmentTest> appointmentTests = new ArrayList<>();
        AppointmentTest appointmentTest = new AppointmentTest();
        appointmentTest.setId(123);
        appointmentTests.add(appointmentTest);
        return appointmentTests;
    }

    @Override
    @RequestMapping(value = "/Appointments?customerId={customerId}&firstName={firstName}&lastName={lastName}}", method = RequestMethod.GET)
    public AppointmentsResponse getAppointmentsByUserIDAndName(Integer customerId, String firstName, String lastName) {
        //public List<Appointment> getAppointmentsByUserIDAndName(Integer customerId, String firstName, String lastName) {
        List<Appointment> appointments = new ArrayList<>();
        Date fromDate = Calendar.getInstance().getTime();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date toDate = cal.getTime();

        appointments = new AppointmentStore().get(customerId, fromDate, toDate, false);
        AppointmentsResponse appointmentsResponse = new AppointmentsResponse();
        appointmentsResponse.setAppointmentList(appointments);
        Customer customer = new CustomerStore().get(customerId);
        Business business = new BusinessStore().get(customer.getBusinessId());
        if(business.getDefaultLocationId() > 0){
            String appointmentCreationURL = "/FlexibleUIConfig/api/v1/Appointments/";
            appointmentsResponse.setAppointmentCreationURL(appointmentCreationURL);
        }

        return appointmentsResponse;
    }

    @Override
    @RequestMapping(value = "/test/{userId}", method = RequestMethod.GET)
    public List<String> doTest(@PathVariable Integer userId) {
        List<String> stringList = new ArrayList<>();
        stringList.add("bob"+userId);
        stringList.add("tracey"+userId);
        stringList.add("nigel"+userId);

        return stringList;
    }
}

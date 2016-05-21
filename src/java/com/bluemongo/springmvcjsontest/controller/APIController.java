package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.api.AppointmentServiceAPI;
import com.bluemongo.springmvcjsontest.model.*;
import com.bluemongo.springmvcjsontest.persistence.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by glenn on 6/12/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/api/v1")
public class APIController implements AppointmentServiceAPI, ServletContextAware {
enum Errors{CUSTOMER_NOT_IN_THIS_BUSINESS}

    //AppointmentsResponse getAppointmentsToday(Integer businessId, Integer customerId, String firstName, String lastName);
    @Override
    @RequestMapping(value = "/AppointmentsToday/{businessId}", method = RequestMethod.GET)
    public AppointmentsResponse getAppointmentsToday(@PathVariable Integer businessId, @RequestParam Integer customerId,
                                                        @RequestParam String firstName, @RequestParam String lastName) {
        AppointmentsResponse appointmentsResponse = new AppointmentsResponse();

        if (customerId == 0) {
            //create new empty customer
            Customer newCustomer = new Customer();
            newCustomer.setBusinessId(businessId);
            customerId = new CustomerStore().saveNew(newCustomer);
            newCustomer.setFirstName("newCustomer");
            newCustomer.setLastName("LastName"+customerId);
            new CustomerStore().saveUpdate(newCustomer);
            //create new appointment for this customer
            Appointment newAppointment = new Appointment();
            newAppointment.setCustomerId(customerId);
            newAppointment.setStatus(new AppointmentStatusStore().getDefault());
            newAppointment.setAppointmentTypeId(new AppointmentTypeStore().getDefault());
            newAppointment.setLocationId(new LocationStore().getDefault());
            newAppointment.saveNew();

        }
        Customer customer = new CustomerStore().get(customerId);
        if (customer==null || customer.getBusinessId() != businessId){
            appointmentsResponse.addErrorMessage(Errors.CUSTOMER_NOT_IN_THIS_BUSINESS.toString());
        }
        else {
            //does supplied name match customerId?
            if (customer.getFirstName().equalsIgnoreCase(firstName) && customer.getLastName().equalsIgnoreCase(lastName)) {
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

                appointmentsResponse.setAppointmentList(appointments);
                //Customer customer = new CustomerStore().get(customerId);
                List<AppointmentStatus> appointmentStatusList = new AppointmentStatusStore().getAll(customer.getBusinessId(), true);
                Business business = new BusinessStore().get(customer.getBusinessId());
                appointmentsResponse.setBusinessDTOFromBusiness(business);
                appointmentsResponse.setAppointmentStatusList(appointmentStatusList);
            }
            else{
                appointmentsResponse.addErrorMessage(Errors.CUSTOMER_NOT_IN_THIS_BUSINESS.toString());
            }
        }

        return appointmentsResponse;
    }

    // eg: http://10.1.1.7:8080/FlexibleUIConfig/api/v1/AppointmentsToday/1/7
/*    @Override
    @RequestMapping(value = "/AppointmentsToday/{businessId}/{customerId}", method = RequestMethod.GET)
    public AppointmentsResponse getAppointmentsToday(@PathVariable Integer businessId, @PathVariable Integer customerId) {
        //public List<Appointment> getAppointmentsToday(@PathVariable Integer customerId) {
        AppointmentsResponse appointmentsResponse = new AppointmentsResponse();

        if (customerId == 0) {
            //create new empty customer
            Customer newCustomer = new Customer();
            newCustomer.setBusinessId(businessId);
            customerId = new CustomerStore().saveNew(newCustomer);
            newCustomer.setFirstName("newCustomer");
            newCustomer.setLastName("LastName"+customerId);
            new CustomerStore().saveUpdate(newCustomer);
            //create new appointment for this customer
            Appointment newAppointment = new Appointment();
            newAppointment.setCustomerId(customerId);
            newAppointment.setStatus(new AppointmentStatusStore().getDefault());
            newAppointment.setAppointmentTypeId(new AppointmentTypeStore().getDefault());
            newAppointment.setLocationId(new LocationStore().getDefault());
            newAppointment.saveNew();

        }
        Customer customer = new CustomerStore().get(customerId);
        if (customer.getBusinessId() != businessId){
            appointmentsResponse.addErrorMessage(Errors.CUSTOMER_NOT_IN_THIS_BUSINESS.toString());
        }
        else {
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

            appointmentsResponse.setAppointmentList(appointments);
            //Customer customer = new CustomerStore().get(customerId);
            List<AppointmentStatus> appointmentStatusList = new AppointmentStatusStore().getAll(customer.getBusinessId(), true);
            Business business = new BusinessStore().get(customer.getBusinessId());
            appointmentsResponse.setBusinessDTOFromBusiness(business);
            appointmentsResponse.setAppointmentStatusList(appointmentStatusList);
        }

        return appointmentsResponse;
    }*/

    // http://10.1.1.7:8080/FlexibleUIConfig/api/v1/Appointment/CheckIn/{id}
    @Override
    @RequestMapping(value = "/Appointment/{id}/CheckIn")
    public AppointmentsResponse checkInAppointment(@PathVariable("id") int appointmentId, @RequestBody AppointmentCheckInDTO appointmentCheckIn){
        AppointmentsResponse appointmentsResponse = new AppointmentsResponse();
        appointmentsResponse.setAppointmentCreationURL("successful " + appointmentCheckIn.getCheckInDateTimeString());
        //save checkin date, prevent button from showing again until that date is removed.
        Appointment appointment = new AppointmentStore().getAppointment(appointmentId);
        appointment.setCheckInDate(Calendar.getInstance().getTime());
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
        List<AppointmentStatus> appointmentStatusList = new AppointmentStatusStore().getAll(customer.getBusinessId(), true);
        appointmentsResponse.setAppointmentStatusList(appointmentStatusList);
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

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


}

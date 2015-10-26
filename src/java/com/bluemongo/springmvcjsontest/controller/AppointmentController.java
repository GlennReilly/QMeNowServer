package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.Appointment;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.AppointmentStore;
import com.bluemongo.springmvcjsontest.service.AddAppointmentFormHelper;
import com.bluemongo.springmvcjsontest.service.GetAppointmentSearchResultsHelper;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by glenn on 11/10/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/appointment")
public class AppointmentController {

    @RequestMapping(value = "/add/{customerId}", method = RequestMethod.GET)
    public ModelAndView getAddAppointmentForm( HttpSession httpSession, @PathVariable int customerId){
        ModelAndView modelAndView;
        User user;
        if (httpSession.getAttribute("User") != null){
            user = (User)httpSession.getAttribute("User");
            modelAndView = ModelViewHelper.GetModelViewForAddAppointment(customerId, user.getBusinessId(), null, httpSession);
        }
        else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView AddAppointment(@ModelAttribute AddAppointmentFormHelper addAppointmentFormHelper, HttpSession httpSession){
        if (httpSession.getAttribute("businessId") != null && httpSession.getAttribute("customerId") != null){
            addAppointmentFormHelper.setBusinessId((int) httpSession.getAttribute("businessId"));
            addAppointmentFormHelper.setCustomerId((int)httpSession.getAttribute("customerId"));
        }
        int newAppointmentId = addAppointmentFormHelper.saveNew();
        String message = "Appointment " + newAppointmentId + " saved successfully: ";
        User user = null;
        if (httpSession.getAttribute("User") != null){
            user = (User)httpSession.getAttribute("User");
        }
        ModelAndView modelAndView = ModelViewHelper.GetModelViewForUserHome(user, message);

        return modelAndView;
    }


        @RequestMapping(value = "/get", method = RequestMethod.GET)
        public ModelAndView GetAllForDateRange(HttpSession httpSession, @ModelAttribute GetAppointmentSearchResultsHelper getAppointmentSearchResultsHelper){
            ModelAndView modelAndView;

                User user;
                if (httpSession.getAttribute("User") != null){
                    user = (User)httpSession.getAttribute("User");
                    getAppointmentSearchResultsHelper.setBusinessId(user.getBusinessId());
                    getAppointmentSearchResultsHelper.generateSearchResults();
                    modelAndView = ModelViewHelper.GetAppointmentSearchResults(getAppointmentSearchResultsHelper, null);
                }
                else{
                    modelAndView = ModelViewHelper.GetLoginForm("Please log in");
                }

            return modelAndView;
        }

    @RequestMapping(value = "/get/{appointmentId}")
    public ModelAndView ShowAppointmentDetails(HttpSession httpSession, @PathVariable int appointmentId)
    {
        ModelAndView modelAndView;
        User user;
        if (httpSession.getAttribute("User") != null){
            user = (User)httpSession.getAttribute("User");
            Appointment appointment = new AppointmentStore().get(appointmentId);
            modelAndView = ModelViewHelper.GetModelViewForEditAppointment(appointment.getCustomerId(), user.getBusinessId(), null, httpSession, appointment);
        }
        else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/getAllForDateRange", method = RequestMethod.GET)
    public ModelAndView GetAllForDateRange(HttpSession httpSession){
        ModelAndView modelAndView;

        User user;
        if (httpSession.getAttribute("User") != null){
            user = (User)httpSession.getAttribute("User");
            modelAndView = ModelViewHelper.GetModelViewForGetCustomerAppointments(user.getBusinessId(), null);
        }
        else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }
}

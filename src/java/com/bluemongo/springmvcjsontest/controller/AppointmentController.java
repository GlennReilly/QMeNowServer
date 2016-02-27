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

/**
 * Created by glenn on 11/10/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/appointment")
public class AppointmentController{

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

    @RequestMapping(value="/addOrUpdate", method = RequestMethod.POST)
    public ModelAndView AddAppointment(@ModelAttribute AddAppointmentFormHelper addAppointmentFormHelper, HttpSession httpSession){
        ModelAndView modelAndView;
        String message;
        if (httpSession.getAttribute("User") != null) {
            if (httpSession.getAttribute("businessId") != null && httpSession.getAttribute("customerId") != null) {
                addAppointmentFormHelper.setBusinessId((int) httpSession.getAttribute("businessId"));
                addAppointmentFormHelper.setCustomerId((int) httpSession.getAttribute("customerId"));
            }

            if (httpSession.getAttribute("CurrentlyEditingAppointmentId") != null) {
                int appointmentId = Integer.parseInt(httpSession.getAttribute("CurrentlyEditingAppointmentId").toString());
                addAppointmentFormHelper.getAppointment().setId(appointmentId);
                addAppointmentFormHelper.saveUpdate();
                message = "Appointment updated successfully. ";
                httpSession.setAttribute("CurrentlyEditingAppointmentId", null);
            } else {
                int newAppointmentId = addAppointmentFormHelper.saveNew();
                message = "Appointment " + newAppointmentId + " saved successfully. ";
            }

            User user = null;
            if (httpSession.getAttribute("User") != null) {
                user = (User) httpSession.getAttribute("User");
            }
            Appointment appointment = addAppointmentFormHelper.getAppointment();
            modelAndView = ModelViewHelper.GetModelViewForEditAppointment(appointment.getCustomerId(), user.getBusinessId(), message, httpSession, appointment);
            //modelAndView = ModelViewHelper.GetModelViewForUserHome(user, message);
        }
        else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }




    @RequestMapping(value = "/get/{appointmentId}", method = RequestMethod.GET)
    public ModelAndView ShowAppointmentDetails(HttpSession httpSession, @PathVariable int appointmentId)
    {
        ModelAndView modelAndView;
        User user;
        if (httpSession.getAttribute("User") != null){
            user = (User)httpSession.getAttribute("User");
            Appointment appointment = new AppointmentStore().getAppointment(appointmentId);
            httpSession.setAttribute("CurrentlyEditingAppointmentId", appointmentId);
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
            modelAndView = ModelViewHelper.GetAppointmentSearchResultsForm(user.getBusinessId(), null);
        }
        else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }

    @RequestMapping(value = {"/getAllForDateRange/{customerId}"}, method = RequestMethod.GET)
    public ModelAndView getAllForDateRangeCustomer( HttpSession httpSession, @PathVariable int customerId){
        ModelAndView modelAndView;
        User user;
        if (httpSession.getAttribute("User") != null){
            user = (User)httpSession.getAttribute("User");
            modelAndView = ModelViewHelper.GetAppointmentSearchResultsForm(user.getBusinessId(),customerId, null);
        }
        else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ModelAndView GetAllForDateRange(HttpSession httpSession, @ModelAttribute GetAppointmentSearchResultsHelper getAppointmentSearchResultsHelper){
        ModelAndView modelAndView;

        User user;
        if (httpSession.getAttribute("User") != null){
            user = (User)httpSession.getAttribute("User");
            getAppointmentSearchResultsHelper.setBusinessId(user.getBusinessId());
            getAppointmentSearchResultsHelper.generateSearchResults();
            modelAndView = ModelViewHelper.GetAppointmentSearchResults(getAppointmentSearchResultsHelper, null, httpSession);
        }
        else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }



    public ModelAndView GetAllFiltered(HttpSession httpSession, Integer locationId, Integer appointmentTypeId, Integer appointmentStatusId){
        ModelAndView modelAndView;

        User user;
        if (httpSession.getAttribute("User") != null){
            user = (User)httpSession.getAttribute("User");
            GetAppointmentSearchResultsHelper getAppointmentSearchResultsHelper = new GetAppointmentSearchResultsHelper();
            getAppointmentSearchResultsHelper.setBusinessId(user.getBusinessId());
            getAppointmentSearchResultsHelper.generateSearchResults();
            modelAndView = ModelViewHelper.GetAppointmentSearchResults(getAppointmentSearchResultsHelper, null, httpSession);
        }
        else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }




}

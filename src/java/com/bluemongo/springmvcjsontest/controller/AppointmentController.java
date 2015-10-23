package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.service.AddAppointmentFormHelper;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by glenn on 11/10/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/appointment")
public class AppointmentController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView getAddAppointmentForm( HttpSession httpSession){
        ModelAndView modelAndView;
        User user;
        if (httpSession.getAttribute("User") != null){
            user = (User)httpSession.getAttribute("User");
            modelAndView = ModelViewHelper.GetModelViewForAddAppointment(user.getBusinessId(), null);
        }
        else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView AddAppointment(@ModelAttribute AddAppointmentFormHelper addAppointmentFormHelper, HttpSession httpSession){
        int newAppointmentId = addAppointmentFormHelper.saveNew();
        String message = "Appointment saved successfully: " + newAppointmentId;
        User user = null;
        if (httpSession.getAttribute("User") != null){
            user = (User)httpSession.getAttribute("User");
        }
        ModelAndView modelAndView = ModelViewHelper.GetModelViewForUserHome(user, message);

        return modelAndView;
    }

    @RequestMapping(value = "/getAllForDateRange", method = RequestMethod.GET)
    public ModelAndView GetAllForDateRange(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();

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

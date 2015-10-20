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

    @RequestMapping(value = "/add/{businessId}", method = RequestMethod.GET)
    public ModelAndView getAddAppointmentForm(@PathVariable int businessId){
        ModelAndView modelAndView = ModelViewHelper.GetModelViewForAddAppointment(businessId, null);
        return modelAndView;
    }

    @RequestMapping(value="/add/{businessId}", method = RequestMethod.POST)
    public ModelAndView AddAppointment(@PathVariable int businessId, @ModelAttribute AddAppointmentFormHelper addAppointmentFormHelper, HttpSession httpSession){
        int newAppointmentId = addAppointmentFormHelper.saveNew();
        String message = "Appointment saved successfully: " + newAppointmentId;
        User user = null;
        if (httpSession.getAttribute("User") != null){
            user = (User)httpSession.getAttribute("User");

        }
        ModelAndView modelAndView = ModelViewHelper.GetModelViewForUserHome(user, message);

        return modelAndView;
    }
}

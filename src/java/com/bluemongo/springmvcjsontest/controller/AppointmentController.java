package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.service.AddAppointmentFormHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by glenn on 11/10/15.
 */
@Controller
@RequestMapping("/FlexibleUIConfig/appointment")
public class AppointmentController {

    @RequestMapping(value = "/add")
    public ModelAndView getAddAppointmentForm(){
        ModelAndView modelAndView = new ModelAndView();
        AddAppointmentFormHelper addAppointmentFormHelper = new AddAppointmentFormHelper();
        modelAndView.addObject("command", addAppointmentFormHelper);
        modelAndView.setViewName("FlexibleUIConfig/Appointment/addCustomerAppointment");
        return modelAndView;
    }
}

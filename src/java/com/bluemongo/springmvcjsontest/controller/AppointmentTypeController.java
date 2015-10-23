package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.AppointmentType;

import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.AppointmentTypeStore;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by glenn on 20/10/15.
 */
@Controller
@RequestMapping(value = "/FlexibleUIConfig/appointmentType")
public class AppointmentTypeController {
    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView GetAppointmentTypeAddForm(HttpSession httpSession){
        return ModelViewHelper.GetModelViewForAddAppointmentType(httpSession);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView AddAppointmentType(@ModelAttribute AppointmentType appointmentType, HttpSession httpSession){
        ModelAndView modelAndView;

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            try {
                appointmentType.setBusinessId(user.getBusinessId());
                int newAppointmentTypeId = new AppointmentTypeStore().saveNew(appointmentType);
                String message = "Appointment Type saved successfully: " + newAppointmentTypeId;
                modelAndView = ModelViewHelper.GetModelViewForUserHome(user, message);
            }
            catch (Exception ex){
                modelAndView = ModelViewHelper.GetModelViewForError(ex.getMessage());
            }
        }

        return modelAndView;
    }
}

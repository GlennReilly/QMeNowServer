package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.AppointmentStatus;
import com.bluemongo.springmvcjsontest.model.AppointmentType;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.AppointmentStatusStore;
import com.bluemongo.springmvcjsontest.persistence.AppointmentTypeStore;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by glenn on 21/10/15.
 */
@Controller
@RequestMapping(value = "/FlexibleUIConfig/appointmentStatus")
public class AppointmentStatusController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView GetAppointmentStatusHome(HttpSession httpSession){
        ModelAndView modelAndView;

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");

            try {
                modelAndView = new ModelViewHelper().getModelViewForAppointmentStatusHome(user);
            }
            catch (Exception ex){
                modelAndView = ModelViewHelper.GetModelViewForError(ex.getMessage());
            }
        }
        return modelAndView;
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView GetAppointmentTypeAddForm(HttpSession httpSession){
        return ModelViewHelper.GetModelViewForAddAppointmentStatus(httpSession);
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView AddAppointmentStatus(@ModelAttribute AppointmentStatus appointmentStatus, HttpSession httpSession){
        ModelAndView modelAndView;

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            try {
                appointmentStatus.setBusinessId(user.getBusinessId());
                int newAppointmentTypeId = new AppointmentStatusStore().saveNew(appointmentStatus);
                String message = "Appointment Status saved successfully: " + newAppointmentTypeId;
                //modelAndView = ModelViewHelper.GetModelViewForUserHome(user, message);
                modelAndView = new ModelViewHelper().getModelViewForAppointmentStatusHome(user);
            }
            catch (Exception ex){
                modelAndView = ModelViewHelper.GetModelViewForError(ex.getMessage());
            }
        }

        return modelAndView;
    }
}

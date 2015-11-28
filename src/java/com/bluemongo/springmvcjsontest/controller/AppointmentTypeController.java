package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.AppointmentType;

import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.AppointmentTypeStore;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView GetAppointmentTypeHome(HttpSession httpSession){
        ModelAndView modelAndView;

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");

            try {
                modelAndView = new ModelViewHelper().getModelViewForAppointmentTypeHome(user);
            }
            catch (Exception ex){
                modelAndView = ModelViewHelper.GetModelViewForError(ex.getMessage());
            }
        }
        return modelAndView;
    }



    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView GetAppointmentTypeAddForm(HttpSession httpSession){
        return ModelViewHelper.GetModelViewForAddEditAppointmentType(httpSession, null);
    }

    @RequestMapping(value="/addOrUpdate", method = RequestMethod.POST)
    public ModelAndView AddAppointmentType(@ModelAttribute AppointmentType appointmentType, HttpSession httpSession){
        ModelAndView modelAndView;

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            try {
                appointmentType.setBusinessId(user.getBusinessId());
                if (httpSession.getAttribute("currentlyEditingAppointmentTypeId") != null){
                    int currentlyEditingAppointmentTypeId = Integer.parseInt(httpSession.getAttribute("currentlyEditingAppointmentTypeId").toString());
                    appointmentType.setId(currentlyEditingAppointmentTypeId);
                    new AppointmentTypeStore().saveUpdate(appointmentType);
                    httpSession.setAttribute("currentlyEditingAppointmentTypeId", null);
                }

                int newAppointmentTypeId = new AppointmentTypeStore().saveNew(appointmentType);
                String message = "Appointment Type saved successfully: " + newAppointmentTypeId;
                //modelAndView = ModelViewHelper.GetModelViewForUserHome(user, message);
                modelAndView = new ModelViewHelper().getModelViewForAppointmentTypeHome(user);
            }
            catch (Exception ex){
                modelAndView = ModelViewHelper.GetModelViewForError(ex.getMessage());
            }
        }

        return modelAndView;
    }

    @RequestMapping(value = "/update/{appointmentTypeId}", method = RequestMethod.GET)
    public ModelAndView GetEditAppointmentTypeForm(HttpSession httpSession, @PathVariable int appointmentTypeId){
        httpSession.setAttribute("currentlyEditingAppointmentTypeId", appointmentTypeId);
        return ModelViewHelper.GetModelViewForAddEditAppointmentType(httpSession, appointmentTypeId);
    }
}

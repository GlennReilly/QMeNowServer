package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.AppointmentType;

import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.AppointmentStore;
import com.bluemongo.springmvcjsontest.persistence.AppointmentTypeStore;
import com.bluemongo.springmvcjsontest.service.AppointmentAndCustomer;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.bluemongo.springmvcjsontest.controller.GenericController.populateHeaderValues;

/**
 * Created by glenn on 20/10/15.
 */
@Controller
@RequestMapping(value = "/appointmentType")

public class AppointmentTypeController {
    private static final Logger logger =  LogManager.getLogger(AppointmentTypeController.class);

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
                modelAndView = ModelViewHelper.GetModelViewForError(httpSession,ex.getMessage());
            }
        }
        return modelAndView;
    }



    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView GetAppointmentTypeAddForm(HttpSession httpSession){
        return ModelViewHelper.GetModelViewForAddEditAppointmentType(httpSession, null);
    }

    @RequestMapping(value="/addOrUpdate", method = RequestMethod.POST)
    public ModelAndView AddOrEditAppointmentType(@ModelAttribute("appointmentType") @Valid AppointmentType appointmentType, BindingResult bindingResult, HttpSession httpSession){
        ModelAndView modelAndView;
        appointmentType.validate(appointmentType, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.info("AppointmentType error.");

           User user = (User) httpSession.getAttribute("User");

            String pageTitle = "Edit Appointment Type";

            modelAndView = new ModelAndView();
            modelAndView.setViewName("FlexibleUIConfig/AppointmentType/addEditAppointmentTypeForm");

            appointmentType.setBusinessId(user.getBusinessId());
            populateHeaderValues(user.getBusinessId(), modelAndView);
            modelAndView.addObject("appointmentType", appointmentType);
            modelAndView.addObject("pageTitle", pageTitle);
            modelAndView.addObject("result", bindingResult);

            return modelAndView;
        }

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
                modelAndView = ModelViewHelper.GetModelViewForError(httpSession,ex.getMessage());
            }
        }

        return modelAndView;
    }

    @RequestMapping(value = "/update/{appointmentTypeId}", method = RequestMethod.GET)
    public ModelAndView GetEditAppointmentTypeForm(HttpSession httpSession, @PathVariable int appointmentTypeId){
        httpSession.setAttribute("currentlyEditingAppointmentTypeId", appointmentTypeId);
        return ModelViewHelper.GetModelViewForAddEditAppointmentType(httpSession, appointmentTypeId);
    }


    @RequestMapping(value = "/delete/{appointmentTypeId}", method = RequestMethod.GET)
    public ModelAndView deactivate(HttpSession httpSession, @PathVariable int appointmentTypeId){
        String result;
        ModelAndView modelAndView;
        List<AppointmentAndCustomer> appointmentResultList = new AppointmentStore().getAll(appointmentTypeId,null,null);
        if (appointmentResultList.size()>0){
            result = "the appointment type you want to delete still has active appointments.";
            modelAndView = ModelViewHelper.GetAppointmentsFiltered(appointmentResultList,result,httpSession);
        }else{
            //ok to delete this location
            new AppointmentTypeStore().setInactive(appointmentTypeId);
            result = "appointment type deactivated.";
            modelAndView = GetAppointmentTypeHome(httpSession);
            modelAndView.addObject("pageMessage", result);
        }
        return modelAndView;
    }
}

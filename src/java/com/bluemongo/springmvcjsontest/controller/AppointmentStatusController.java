package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.AppointmentStatus;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.AppointmentStatusStore;
import com.bluemongo.springmvcjsontest.persistence.AppointmentStore;
import com.bluemongo.springmvcjsontest.service.AppointmentAndCustomer;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by glenn on 21/10/15.
 */
@Controller
@RequestMapping(value = "/FlexibleUIConfig/appointmentStatus")
public class AppointmentStatusController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView GetAppointmentStatusHome(HttpSession httpSession) {
        ModelAndView modelAndView;

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        } else {
            User user = (User) httpSession.getAttribute("User");

            try {
                modelAndView = new ModelViewHelper().getModelViewForAppointmentStatusHome(user);
            } catch (Exception ex) {
                modelAndView = ModelViewHelper.GetModelViewForError(httpSession, ex.getMessage());
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView GetAppointmentTypeAddForm(HttpSession httpSession) {
        return ModelViewHelper.GetModelViewForAddEditAppointmentStatus(httpSession, null);
    }

    @RequestMapping(value = "/addOrUpdate", method = RequestMethod.POST)
    public ModelAndView AddAppointmentStatus(@ModelAttribute AppointmentStatus appointmentStatus, BindingResult bindingResult, HttpSession httpSession) {
        ModelAndView modelAndView;

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        } else {
            appointmentStatus.validate(appointmentStatus, bindingResult);
            if(bindingResult.hasErrors()){
             modelAndView = ModelViewHelper.GetModelViewForAddEditAppointmentStatus(httpSession, null);
             modelAndView.addObject("appointmentStatus", appointmentStatus);
            }
            else {
                User user = (User) httpSession.getAttribute("User");
                try {
                    appointmentStatus.setBusinessId(user.getBusinessId());
                    if (httpSession.getAttribute("currentlyEditingAppointmentStatusId") != null) {
                        int currentlyEditingAppointmentStatusId = Integer.parseInt(httpSession.getAttribute("currentlyEditingAppointmentStatusId").toString());
                        appointmentStatus.setId(currentlyEditingAppointmentStatusId);
                        new AppointmentStatusStore().saveUpdate(appointmentStatus);
                        httpSession.setAttribute("currentlyEditingAppointmentStatusId", null);
                    } else {
                        int newAppointmentTypeId = new AppointmentStatusStore().saveNew(appointmentStatus);
                        String message = "Appointment Status saved successfully: " + newAppointmentTypeId;
                    }
                    modelAndView = new ModelViewHelper().getModelViewForAppointmentStatusHome(user);
                } catch (Exception ex) {
                    modelAndView = ModelViewHelper.GetModelViewForError(httpSession,ex.getMessage());
                }
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/update/{appointmentStatusId}", method = RequestMethod.GET)
    public ModelAndView getAppointmentStatusUpdateForm(HttpSession httpSession, @PathVariable int appointmentStatusId){
    ModelAndView modelAndView;
    if(httpSession.getAttribute("User")==null)
    {
        modelAndView = ModelViewHelper.GetLoginForm(null);
    }
    else
    {
        User user = (User) httpSession.getAttribute("User");
        try {

            modelAndView = ModelViewHelper.GetModelViewForAddEditAppointmentStatus(httpSession, appointmentStatusId);
            httpSession.setAttribute("currentlyEditingAppointmentStatusId", appointmentStatusId);
        } catch (Exception ex) {
            modelAndView = ModelViewHelper.GetModelViewForError(httpSession, ex.getMessage());
        }
    }

    return modelAndView;
    }


    @RequestMapping(value = "/delete/{appointmentStatusId}", method = RequestMethod.GET)
    public ModelAndView deactivate(HttpSession httpSession, @PathVariable int appointmentStatusId){
        String result;
        ModelAndView modelAndView;
        List<AppointmentAndCustomer> appointmentResultList = new AppointmentStore().getAll(null,appointmentStatusId,null);
        if (appointmentResultList.size()>0){
            result = "the appointment status you want to delete still has active appointments.";
            modelAndView = ModelViewHelper.GetAppointmentsFiltered(appointmentResultList,result,httpSession);
        }else{
            //ok to delete this location
            new AppointmentStatusStore().setInactive(appointmentStatusId);
            result = "appointment status deactivated.";
            modelAndView = GetAppointmentStatusHome(httpSession);
            modelAndView.addObject("pageMessage", result);
        }
        return modelAndView;
    }


}

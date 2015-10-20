package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.*;
import com.bluemongo.springmvcjsontest.persistence.BusinessStore;
import com.bluemongo.springmvcjsontest.persistence.CustomerStore;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by glenn on 20/10/15.
 */
public class ModelViewHelper {

    public static ModelAndView GetStartPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/index");
        return modelAndView;
    }

    public static ModelAndView GetLoginForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("FlexibleUIConfig/login");
        modelAndView.addObject("command", new UserCredentials());
        return  modelAndView;
    }

    public static ModelAndView ProcessLogin(UserCredentials userCredentials,HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        User validUser = User.get(userCredentials);
        if (validUser != null) {
            httpSession.setAttribute("User", validUser);
            modelAndView = ModelViewHelper.GetModelViewForUserHome(validUser, null);
        } else {
            modelAndView.setViewName("Error");
        }
        return modelAndView;
    }

    public static ModelAndView GetModelViewForUserHome(User validUser, String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("User", validUser);
        BusinessStore businessStore = new BusinessStore();
        Business business = businessStore.Get(validUser.getBusinessId());
        message = (message == null) ? "" :  message;
        modelAndView.addObject("message", message);
        modelAndView.addObject("Business", business);
        modelAndView.setViewName("FlexibleUIConfig/showUserHome");
        return modelAndView;
    }

    public static ModelAndView GetModelViewForAddAppointment(int businessId, String message){
        ModelAndView modelAndView = new ModelAndView();
        AddAppointmentFormHelper addAppointmentFormHelper = new AddAppointmentFormHelper();

        addAppointmentFormHelper.setBusinessId(businessId);
        List<Customer> customerList = new CustomerStore().getAll(businessId, true);
        addAppointmentFormHelper.setCustomersList(customerList);

        message = (message == null) ? "" :  message;
        modelAndView.addObject("message", message);
        modelAndView.addObject("businessId", businessId);
        modelAndView.addObject("command", addAppointmentFormHelper);
        modelAndView.setViewName("FlexibleUIConfig/Appointment/addCustomerAppointment");
        return modelAndView;
    }

    public static ModelAndView GetModeViewForAddLocation(int businessId, String message) {
        ModelAndView modelAndView = new ModelAndView();
        Location location = new Location();
        location.setBusinessId(businessId);
        modelAndView.addObject("command", location);
        message = (message == null) ? "" :  message;
        modelAndView.addObject("message", message);
        modelAndView.setViewName("FlexibleUIConfig/Location/addBusinessLocation");
        return modelAndView;
    }
}

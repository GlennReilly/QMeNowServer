package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.*;
import com.bluemongo.springmvcjsontest.persistence.AppointmentTypeStore;
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

    public static ModelAndView GetLoginForm(String message){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("FlexibleUIConfig/login");
        modelAndView.addObject("command", new UserCredentials());
        modelAndView.addObject("pageTitle", "Please Login");
        message = (message == null) ? "" :  message;
        modelAndView.addObject("message", message);
        return  modelAndView;
    }

    public static ModelAndView ProcessLogin(UserCredentials userCredentials,HttpSession httpSession) {
        ModelAndView modelAndView;
        User validUser = User.get(userCredentials);
        if (validUser != null) {
            httpSession.setAttribute("User", validUser);
            modelAndView = ModelViewHelper.GetModelViewForUserHome(validUser, null);
        } else {
            modelAndView = GetLoginForm("a error occurred while trying to log you in. Please try again.");
        }
        return modelAndView;
    }

    public static ModelAndView GetModelViewForUserHome(User validUser, String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("User", validUser);
        BusinessStore businessStore = new BusinessStore();
        Business business = businessStore.Get(validUser.getBusinessId());
        modelAndView.addObject("pageTitle", "User Home");
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

        List<AppointmentType> appointmentTypeList = new AppointmentTypeStore().getAll(true, businessId);
        addAppointmentFormHelper.setAppointmentTypeList(appointmentTypeList);

        modelAndView.addObject("command", addAppointmentFormHelper);
        modelAndView.addObject("pageTitle", "Add Appointment");
        message = (message == null) ? "" :  message;
        modelAndView.addObject("message", message);
        modelAndView.addObject("businessId", businessId);
        modelAndView.setViewName("FlexibleUIConfig/Appointment/addCustomerAppointment");
        return modelAndView;
    }

    public static ModelAndView GetModeViewForAddLocation(int businessId, String message) {
        ModelAndView modelAndView = new ModelAndView();
        Location location = new Location();
        location.setBusinessId(businessId);
        modelAndView.addObject("command", location);
        modelAndView.addObject("pageTitle", "Add Location");
        message = (message == null) ? "" :  message;
        modelAndView.addObject("message", message);
        modelAndView.setViewName("FlexibleUIConfig/Location/addBusinessLocation");
        return modelAndView;
    }

    public static ModelAndView GetModelViewForAddAppointmentType(HttpSession httpSession) {
        ModelAndView modelAndView;

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else {
            User user = (User) httpSession.getAttribute("User");
            modelAndView = new ModelAndView();
            modelAndView.setViewName("/FlexibleUIConfig/AppointmentType/addAppointmentTypeForm");
            AppointmentType appointmentType = new AppointmentType();
            appointmentType.setBusinessId(user.getBusinessId());
            modelAndView.addObject("command", appointmentType);
            modelAndView.addObject("pageTitle", "Add Appointment Type");
        }

        return  modelAndView;
    }

    public static ModelAndView GetModelViewForCustomerAdd() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("FlexibleUIConfig/Customer/addCustomerForm");
        modelAndView.addObject("command", new Customer());
        modelAndView.addObject("pageTitle", "Add a Customer");
        return  modelAndView;
    }

    public static ModelAndView GetModelViewForError(String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("pageTitle", "Oops..");
        modelAndView.addObject("message", message);
        modelAndView.setViewName("error");
        return modelAndView;
    }

    public static ModelAndView GetModelViewForAddAppointmentStatus(HttpSession httpSession) {
        ModelAndView modelAndView;

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else {
            User user = (User) httpSession.getAttribute("User");
            modelAndView = new ModelAndView();
            modelAndView.setViewName("/FlexibleUIConfig/AppointmentStatus/addAppointmentStatusForm");
            AppointmentStatus appointmentStatus = new AppointmentStatus();
            appointmentStatus.setBusinessId(user.getBusinessId());
            modelAndView.addObject("command", appointmentStatus);
            modelAndView.addObject("pageTitle", "Add Appointment Status");
        }

        return  modelAndView;
    }

    public static ModelAndView GetModelViewForGetCustomerAppointments(int businessId, String message) {
        ModelAndView modelAndView = new ModelAndView();
        GetAppointmentsByCustomerAndDateHelper getAppointmentsByCustomerAndDate = new GetAppointmentsByCustomerAndDateHelper();
        modelAndView.addObject("command", getAppointmentsByCustomerAndDate);
        modelAndView.addObject("pageTitle", "Get Appointments for Customer by Date");
        modelAndView.setViewName("/FlexibleUIConfig/Appointment/getAppointmentsForCustomerByDate");

        return  modelAndView;
    }

    public static ModelAndView GetModelViewForFindCustomer(User user, String message) {
        ModelAndView modelAndView = new ModelAndView();
        GetFindCustomerHelper getFindCustomerHelper = new GetFindCustomerHelper();
        modelAndView.addObject("command", getFindCustomerHelper);
        modelAndView.addObject("pageTitle", "Find a Customer");
        modelAndView.setViewName("/FlexibleUIConfig/Customer/findCustomer");

        return  modelAndView;
    }
}

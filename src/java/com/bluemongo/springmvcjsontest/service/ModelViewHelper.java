package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.*;
import com.bluemongo.springmvcjsontest.persistence.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        ModelAndView modelAndView = new ModelAndView();
        User validUser = User.get(userCredentials);
        if (validUser != null) {
            httpSession.setAttribute("User", validUser);
            if (validUser.getUserType().equals(User.UserType.USER)) {
                modelAndView = ModelViewHelper.GetModelViewForUserHome(validUser, null);
            }else if (validUser.getUserType().equals(User.UserType.ADMIN)) {
                modelAndView = ModelViewHelper.GetModelViewForAdminHome(validUser, null);
            }
        } else {
            modelAndView = GetLoginForm("a error occurred while trying to log you in. Please try again.");
        }
        return modelAndView;
    }

    private static ModelAndView GetModelViewForAdminHome(User validUser, String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("User", validUser);
        BusinessStore businessStore = new BusinessStore();
        Business business = businessStore.get(validUser.getBusinessId());
        populateHeaderValues(business.getId(), modelAndView);
        modelAndView.addObject("pageTitle", "Admin Home");
        message = (message == null) ? "" :  message;
        modelAndView.addObject("message", message);
        modelAndView.addObject("Business", business);
        modelAndView.setViewName("FlexibleUIConfig/index");
        return modelAndView;
    }

    public static ModelAndView GetModelViewForUserHome(User validUser, String message) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("User", validUser);
        BusinessStore businessStore = new BusinessStore();
        Business business = businessStore.get(validUser.getBusinessId());
        populateHeaderValues(validUser.getBusinessId(), modelAndView);
        modelAndView.addObject("pageTitle", "User Home");
        message = (message == null) ? "" :  message;
        modelAndView.addObject("message", message);
        modelAndView.addObject("Business", business);
        modelAndView.setViewName("FlexibleUIConfig/showUserHome");
        return modelAndView;
    }

    public static ModelAndView GetModelViewForAddAppointment(int customerId, int businessId, String message, HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();
        AddAppointmentFormHelper addAppointmentFormHelper = new AddAppointmentFormHelper();

        addAppointmentFormHelper.setBusinessId(businessId);
        addAppointmentFormHelper.setCustomerId(customerId);
        Customer customer = new CustomerStore().get(businessId, customerId);
        List<AppointmentType> appointmentTypeList = new AppointmentTypeStore().getAll(true, businessId);
        addAppointmentFormHelper.setAppointmentTypeList(appointmentTypeList);
        populateHeaderValues(businessId, modelAndView);
        modelAndView.addObject("command", addAppointmentFormHelper);
        modelAndView.addObject("pageTitle", "Add Appointment for " + customer.getName());
        message = (message == null) ? "" :  message;
        modelAndView.addObject("message", message);

        httpSession.setAttribute("businessId", businessId);
        httpSession.setAttribute("customerId", customerId);
        modelAndView.setViewName("FlexibleUIConfig/Appointment/addEditCustomerAppointment");
        return modelAndView;
    }

    public static ModelAndView GetModelViewForEditAppointment(int customerId, int businessId, String message, HttpSession httpSession, Appointment appointment){
        ModelAndView modelAndView = new ModelAndView();
        AddAppointmentFormHelper addAppointmentFormHelper = new AddAppointmentFormHelper();

        addAppointmentFormHelper.setAppointment(appointment);
        addAppointmentFormHelper.setBusinessId(businessId);
        addAppointmentFormHelper.setCustomerId(customerId);
        Customer customer = new CustomerStore().get(businessId, customerId);
        List<AppointmentType> appointmentTypeList = new AppointmentTypeStore().getAll(true, businessId);
        addAppointmentFormHelper.setAppointmentTypeList(appointmentTypeList);
        populateHeaderValues(businessId, modelAndView);
        modelAndView.addObject("command", addAppointmentFormHelper);
        modelAndView.addObject("pageTitle", "Edit Appointment for " + customer.getName());
        message = (message == null) ? "" :  message;
        modelAndView.addObject("message", message);

        httpSession.setAttribute("businessId", businessId);
        httpSession.setAttribute("customerId", customerId);
        modelAndView.setViewName("FlexibleUIConfig/Appointment/addEditCustomerAppointment");
        return modelAndView;
    }

    public static ModelAndView GetModeViewForAddLocation(int businessId, String message) {
        ModelAndView modelAndView = new ModelAndView();
        populateHeaderValues(businessId, modelAndView);
        Location location = new Location();
        location.setBusinessId(businessId);
        populateHeaderValues(businessId, modelAndView);
        modelAndView.addObject("command", location);
        modelAndView.addObject("pageTitle", "Add Location");
        message = (message == null) ? "" :  message;
        modelAndView.addObject("message", message);
        modelAndView.setViewName("FlexibleUIConfig/Location/addBusinessLocation");
        return modelAndView;
    }

    private static void populateHeaderValues(int businessId, ModelAndView modelAndView) {
        Business business = new BusinessStore().get(businessId);
        modelAndView.addObject("businessName", business.getBusinessName());
        modelAndView.addObject("logoName", business.getLogoName());
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
            populateHeaderValues(user.getBusinessId(), modelAndView);
            modelAndView.addObject("command", appointmentType);
            modelAndView.addObject("pageTitle", "Add Appointment Type");
        }

        return  modelAndView;
    }

    public ModelAndView getModelViewForAppointmentTypeHome(User user) {
        ModelAndView modelAndView = new ModelAndView();
        List<AppointmentType> appointmentTypes;
        appointmentTypes = new AppointmentTypeStore().getAll(true, user.getBusinessId());
        populateHeaderValues(user.getBusinessId(), modelAndView);
        modelAndView.addObject("appointmentTypes", appointmentTypes);
        modelAndView.addObject("pageTitle", "Appointment Types");
        modelAndView.setViewName("FlexibleUIConfig/AppointmentType/index");
        return modelAndView;
    }

    public static ModelAndView GetModelViewForCustomerAdd(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else {
            User user = (User) httpSession.getAttribute("User");
            modelAndView.setViewName("FlexibleUIConfig/Customer/addCustomerForm");
            populateHeaderValues(user.getBusinessId(), modelAndView);
            modelAndView.addObject("command", new Customer());
            modelAndView.addObject("pageTitle", "Add a Customer");
        }
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
            populateHeaderValues(user.getBusinessId(), modelAndView);
            modelAndView.addObject("command", appointmentStatus);
            modelAndView.addObject("pageTitle", "Add Appointment Status");
        }

        return  modelAndView;
    }


    public static ModelAndView GetModelViewForFindCustomer(User user, String message) {
        ModelAndView modelAndView = new ModelAndView();
        GetFindCustomerHelper getFindCustomerHelper = new GetFindCustomerHelper();
        populateHeaderValues(user.getBusinessId(), modelAndView);
        modelAndView.addObject("command", getFindCustomerHelper);
        modelAndView.addObject("pageTitle", "Find a Customer");
        modelAndView.setViewName("/FlexibleUIConfig/Customer/findCustomer");

        return  modelAndView;
    }

    public static ModelAndView GetAppointmentSearchResults(int businessId, String strFromDate, String strToDate, String message) {
        ModelAndView modelAndView = new ModelAndView();
        GetAppointmentSearchResultsHelper getAppointmentSearchResultsHelper = new GetAppointmentSearchResultsHelper();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date fromDate = sdf.parse(strFromDate);
            Date toDate = sdf.parse(strToDate);
            getAppointmentSearchResultsHelper.setFromDate(fromDate);
            getAppointmentSearchResultsHelper.setToDate(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        getAppointmentSearchResultsHelper.setBusinessId(businessId);
        populateHeaderValues(businessId, modelAndView);
        modelAndView.addObject("command", getAppointmentSearchResultsHelper);
        modelAndView.addObject("pageTitle", "Show Appointments");
        modelAndView.setViewName("/FlexibleUIConfig/Appointment/getAppointmentsForCustomerByDate");

        return  modelAndView;
    }


    public static ModelAndView GetAppointmentSearchResultsForm(int businessId, String message) {
        ModelAndView modelAndView = new ModelAndView();
        GetAppointmentSearchResultsHelper getAppointmentSearchResultsHelper = new GetAppointmentSearchResultsHelper();
        populateHeaderValues(businessId, modelAndView);
        modelAndView.addObject("command", getAppointmentSearchResultsHelper);
        modelAndView.addObject("pageTitle", "Show Appointments");
        modelAndView.setViewName("/FlexibleUIConfig/Appointment/getAppointmentsForCustomerByDate");

        return  modelAndView;
    }

    public static ModelAndView GetAppointmentSearchResultsForm(int businessId, int customerId, String message) {
        ModelAndView modelAndView = new ModelAndView();
        GetAppointmentSearchResultsHelper getAppointmentSearchResultsHelper = new GetAppointmentSearchResultsHelper();
        getAppointmentSearchResultsHelper.setCustomerId(customerId);
        populateHeaderValues(businessId, modelAndView);
        modelAndView.addObject("command", getAppointmentSearchResultsHelper);
        modelAndView.addObject("pageTitle", "Show Appointments");
        modelAndView.setViewName("/FlexibleUIConfig/Appointment/getAppointmentsForCustomerByDate");

        return  modelAndView;
    }


    public static ModelAndView GetAppointmentSearchResults(GetAppointmentSearchResultsHelper getAppointmentSearchResultsHelper, String message, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else {
            User user = (User) httpSession.getAttribute("User");
            populateHeaderValues(user.getBusinessId(), modelAndView);
            modelAndView.addObject("command", getAppointmentSearchResultsHelper);
            modelAndView.addObject("pageTitle", "Show Appointments");
            modelAndView.setViewName("/FlexibleUIConfig/Appointment/getAppointmentsForCustomerByDate");
        }
        return  modelAndView;
    }


    public ModelAndView getModelViewForAppointmentStatusHome(User user) {
        ModelAndView modelAndView;
            modelAndView = new ModelAndView();
            modelAndView.setViewName("/FlexibleUIConfig/AppointmentStatus/index");
            List<AppointmentStatus> appointmentStatusList = new AppointmentStatusStore().getAll(user.getBusinessId());
            populateHeaderValues(user.getBusinessId(), modelAndView);
            modelAndView.addObject("appointmentStatuses", appointmentStatusList);
            modelAndView.addObject("pageTitle", "Appointment Statuses");

        return  modelAndView;
    }

    public ModelAndView getModelViewForLocationsHome(User user) {
        ModelAndView modelAndView;
        modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/Location/index");
        List<Location> locationList = new LocationStore().getAll(user.getBusinessId(),true);
        populateHeaderValues(user.getBusinessId(), modelAndView);
        modelAndView.addObject("locations", locationList);
        modelAndView.addObject("pageTitle", "Locations");

        return  modelAndView;
    }
}
package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.model.Customer;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.BusinessStore;
import com.bluemongo.springmvcjsontest.persistence.CustomerStore;
import com.bluemongo.springmvcjsontest.service.GetFindCustomerHelper;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by glenn on 17/10/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/customer")
public class CustomerController {

    // Customer methods

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView GetCustomerAddForm(HttpSession httpSession){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else {
            modelAndView = ModelViewHelper.GetModelViewForCustomerAdd(httpSession);
        }
        return modelAndView;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView AddCustomer(@ModelAttribute Customer customer, HttpSession httpSession){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            customer.setBusinessId(user.getBusinessId());
            int newCustomerId = customer.saveNew();
            String message = "Customer saved successfully: " + newCustomerId;
            modelAndView = ModelViewHelper.GetModelViewForUserHome(user, message);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ModelAndView GetFindCustomerForm(HttpSession httpSession){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            modelAndView = ModelViewHelper.GetModelViewForFindCustomer(user, null);
            Business business = new BusinessStore().get(user.getBusinessId());
            modelAndView.addObject("businessName", business.getBusinessName());
            modelAndView.addObject("logoName", business.getLogoName());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ModelAndView FindCustomer(HttpSession httpSession, @ModelAttribute GetFindCustomerHelper getFindCustomerHelper){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            modelAndView = new ModelAndView();
            Business business = new BusinessStore().get(user.getBusinessId());
            modelAndView.addObject("businessName", business.getBusinessName());
            modelAndView.addObject("logoName", business.getLogoName());
            String customerIdStr = getFindCustomerHelper.getCustomerIdStr();
            String firstName = getFindCustomerHelper.getFirstName();
            String lastName = getFindCustomerHelper.getLastName();
            List<Customer> customerList = new CustomerStore().get(user.getBusinessId(), customerIdStr, firstName, lastName);
            if (customerList.size() > 0){
                if (customerList.size() > 1){
                    //more than one matching customers
                    modelAndView.addObject("customerList", customerList);
                    modelAndView.addObject("pageTitle", "Customer List");
                    modelAndView.setViewName("FlexibleUIConfig/Customer/customerList");
                }else{
                    //one customer
                    modelAndView.addObject("command", customerList.get(0));
                    modelAndView.addObject("pageTitle", "Customer Details");
                    modelAndView.setViewName("FlexibleUIConfig/Customer/customerDetails");
                }
            }else{
                modelAndView = ModelViewHelper.GetModelViewForError("No matching customers found.");
            }

        }
        return modelAndView;
    }

    @RequestMapping(value = "/details/{customerId}", method = RequestMethod.GET)
    public ModelAndView ShowCustomerDetails(HttpSession httpSession, @PathVariable int customerId){
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            Customer customer = new CustomerStore().get(user.getBusinessId(), customerId);
            if (customer != null){
                Business business = new BusinessStore().get(user.getBusinessId());
                modelAndView.addObject("businessName", business.getBusinessName());
                modelAndView.addObject("logoName", business.getLogoName());
                modelAndView.addObject("command", customer);
                modelAndView.addObject("pageTitle", "Customer Details");
                modelAndView.setViewName("FlexibleUIConfig/Customer/customerDetails");
            }else {
                modelAndView = ModelViewHelper.GetModelViewForError("Sorry, there was an error retrieving that customer.");
            }
        }

        return modelAndView;
    }
}
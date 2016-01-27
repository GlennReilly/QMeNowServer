package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.QRCodePayload;
import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.model.Customer;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.BusinessStore;
import com.bluemongo.springmvcjsontest.persistence.CustomerStore;
import com.bluemongo.springmvcjsontest.service.GetFindCustomerHelper;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import utils.InputHelper;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by glenn on 17/10/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/customer")
public class CustomerController implements ServletContextAware
{
    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

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
                    httpSession.setAttribute("CurrentlyEditingCustomerId", customerList.get(0).getId());
                }
            }else{
                modelAndView = ModelViewHelper.GetModelViewForError("No matching customers found.");
            }

        }
        return modelAndView;
    }

    @RequestMapping(value = "/details/{customerId}", method = RequestMethod.GET)
    public ModelAndView ShowCustomerDetails(HttpSession httpSession, @PathVariable int customerId){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            Customer customer = new CustomerStore().get(user.getBusinessId(), customerId);
            if (customer != null){
                modelAndView = getModelViewForCustomerEdit(httpSession, user, customer);
            }else {
                modelAndView = ModelViewHelper.GetModelViewForError("Sorry, there was an error retrieving that customer.");
            }
        }

        return modelAndView;
    }

    private static ModelAndView getModelViewForCustomerEdit(HttpSession httpSession, User user, Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
        Business business = new BusinessStore().get(user.getBusinessId());
        modelAndView.addObject("businessName", business.getBusinessName());
        modelAndView.addObject("logoName", business.getLogoName());
        modelAndView.addObject("command", customer);
        modelAndView.addObject("pageTitle", "Customer Details");
        modelAndView.setViewName("FlexibleUIConfig/Customer/customerDetails");
        httpSession.setAttribute("CurrentlyEditingCustomerId", customer.getId());
        return modelAndView;
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public ModelAndView UpdateCustomer(HttpSession httpSession, Customer customer){
        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else {
            User user = (User) httpSession.getAttribute("User");
            if (httpSession.getAttribute("CurrentlyEditingCustomerId") != null){
                int currentlyEditingCustomerId = Integer.parseInt(httpSession.getAttribute("CurrentlyEditingCustomerId").toString());
                customer.setId(currentlyEditingCustomerId);
                new CustomerStore().saveUpdate(customer);
                modelAndView = getModelViewForCustomerEdit(httpSession, user, customer);
                modelAndView.addObject("message", "Customer updated successfully.");
                httpSession.setAttribute("CurrentlyEditingCustomerId", null);
            }
        }
        return modelAndView;
    }



}

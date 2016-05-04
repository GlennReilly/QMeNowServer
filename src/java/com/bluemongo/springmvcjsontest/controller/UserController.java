package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.service.AddUserFormHelper;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import javax.servlet.http.HttpSession;

/**
 * Created by glenn on 11/10/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/user")
public class UserController extends GenericController{
    private static final Logger logger =  LogManager.getLogger(UserController.class);
    // User methods
    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView GetUserAddForm(HttpSession httpSession){
        ModelAndView modelAndView = new ModelAndView();

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else {
            modelAndView.setViewName("/FlexibleUIConfig/addUserForm");
            modelAndView.addObject("command", new AddUserFormHelper());
            modelAndView.addObject("pageTitle", "Add a user");
            int businessId = ((User)httpSession.getAttribute("User")).getBusinessId();
            populateHeaderValues(businessId, modelAndView);
        }
        return  modelAndView;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String AddUser(@ModelAttribute("newUser") @Valid AddUserFormHelper newUser, BindingResult bindingResult) {
        String output = "";
        newUser.validate(newUser, bindingResult);
        if (bindingResult.hasErrors()) {
            logger.info("AddUserFormHelper error.");
        } else {
            newUser.save();
            output = "User saved successfully: " + newUser.getFirstName() + " " + newUser.getLastName();
        }
        return output;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView GetUserHome(@ModelAttribute User validUser){
        ModelAndView modelAndView = ModelViewHelper.GetModelViewForUserHome(validUser, null);
        return modelAndView;
    }
}

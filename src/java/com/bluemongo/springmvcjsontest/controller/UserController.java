package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.service.AddUserFormHelper;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by glenn on 11/10/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/user")
public class UserController {
    // User methods
    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView GetUserAddForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/addUserForm");
        modelAndView.addObject("command", new AddUserFormHelper());
        return  modelAndView;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String AddUser(@ModelAttribute AddUserFormHelper user) {
        user.save();
        return "User saved successfully: " + user.getFirstName() + " " + user.getLastName();
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView GetUserHome(@ModelAttribute User validUser){
        ModelAndView modelAndView = ModelViewHelper.GetModelViewForUserHome(validUser, null);
        return modelAndView;
    }
}

package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.api.UserAppointmentService;
import com.bluemongo.springmvcjsontest.model.*;
import com.bluemongo.springmvcjsontest.service.ConfigHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import com.bluemongo.springmvcjsontest.service.ModelViewHelper;

import javax.servlet.http.HttpSession;

/**
 * Created by glenn on 9/08/15.
 */


@Controller
@RequestMapping("/")
public class HomeController {
    private static final Logger logger = LogManager.getLogger(HomeController.class);


    @RequestMapping(value={"/", "/login"}, method=RequestMethod.GET)
    public ModelAndView GetLoginForm(HttpSession httpSession){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");
            modelAndView = ModelViewHelper.GetModelViewForUserHome(user, null);
        }
        return  modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView GetLogoutPageConfirmationForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/logoutConfirmation");
        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String DoLogout(HttpSession httpSession){
        httpSession.setAttribute("User", null);
       return "redirect:/login";
    }

    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public ModelAndView ProcessLogin(@ModelAttribute UserCredentials userCredentials,HttpSession httpSession){
        return ModelViewHelper.ProcessLogin(userCredentials, httpSession);
    }

    // Button style methods

    @RequestMapping(value="/buttonStyle/add", method = RequestMethod.GET)
    public ModelAndView GetButtonStyleForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("command", new ButtonStyle());
        modelAndView.setViewName("/FlexibleUIConfig/addButtonStyleForm");
        return modelAndView;
    }


    // Edit Config methods

    @RequestMapping(value = "/edit/{configID}", method = RequestMethod.GET)
    public ModelAndView GetEditConfigForm(@PathVariable int configID){
        ModelAndView modelAndView = new ModelAndView();
        ConfigHelper configHelper = ConfigHelper.get(configID);
        ReconfigurableAppConfig appConfig = configHelper.getCurrentAppConfig();
        if (appConfig == null) {
            logger.error("No appConfig found with configID: " + configID);
            modelAndView.setViewName("error");
        }else {
            configHelper.setAvailableButtonStyles();
            modelAndView.setViewName("/FlexibleUIConfig/EditConfigForm");
            modelAndView.addObject("command", configHelper);
        }
        return modelAndView;
    }


}

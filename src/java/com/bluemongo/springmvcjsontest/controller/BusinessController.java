package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.Business;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by glenn on 17/10/15.
 */
@RestController
@RequestMapping("/FlexibleUIConfig/business")
public class BusinessController {

    // Customer methods

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public ModelAndView GetBusinessAddForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/addBusinessForm");
        modelAndView.addObject("command", new Business());
        return  modelAndView;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String AddBusiness(@ModelAttribute Business business){
        int newBusinessId = business.saveNew();
        return "Business saved successfully: " + newBusinessId;
    }
}

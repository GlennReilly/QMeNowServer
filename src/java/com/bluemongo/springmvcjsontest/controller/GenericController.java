package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.BusinessStore;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by glenn on 6/03/16.
 */
public class GenericController {

    public void addHeaderDetails(ModelAndView modelAndView, int businessId) {
        Business business = new BusinessStore().get(businessId);
        modelAndView.addObject("businessName", business.getBusinessName());
        modelAndView.addObject("logoName", business.getLogoName());
        modelAndView.addObject("headerColour", business.getHeaderColourHexCode());
    }
}

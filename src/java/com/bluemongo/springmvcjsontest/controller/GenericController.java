package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.persistence.BusinessStore;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by glenn on 6/03/16.
 */
public class GenericController {

    public static void populateHeaderValues(int businessId, ModelAndView modelAndView) {
        Business business = new BusinessStore().get(businessId);
        modelAndView.addObject("businessName", business.getBusinessName());
        modelAndView.addObject("logoFileName", business.getLogoFileName());
        modelAndView.addObject("headerColour", business.getHeaderColourHexCode());
        modelAndView.addObject("backgroundColourHexCode", business.getBackgroundColourHexCode());
    }


}

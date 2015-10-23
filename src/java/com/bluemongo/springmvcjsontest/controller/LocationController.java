package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.Location;
import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.persistence.LocationStore;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by glenn on 19/10/15.
 */

@RestController
@RequestMapping("/FlexibleUIConfig/location")
public class LocationController {

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView AddLocationForBusinessForm(HttpSession httpSession){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") != null) {
            User user = (User)httpSession.getAttribute("User");
            modelAndView = ModelViewHelper.GetModeViewForAddLocation(user.getBusinessId(), null);
        } else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView AddLocation(@ModelAttribute Location location, HttpSession httpSession){
        LocationStore locationStore = new LocationStore();
        locationStore.saveNew(location);
        if (httpSession.getAttribute("User") != null) {
            User user = (User)httpSession.getAttribute("User");
        }
        String message = "Location saved successfully: "; // + newAppointmentId;
        ModelAndView modelAndView = ModelViewHelper.GetModelViewForAddAppointment(location.getBusinessId(), message);
        return modelAndView;
    }
}

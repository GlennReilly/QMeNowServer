package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.Location;
import com.bluemongo.springmvcjsontest.persistence.LocationStore;
import com.bluemongo.springmvcjsontest.service.ModelViewHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by glenn on 19/10/15.
 */

@RestController
@RequestMapping("/FlexibleUIConfig/location")
public class LocationController {

    @RequestMapping(value = "/add/{businessId}", method = RequestMethod.GET)
    public ModelAndView AddLocationForBusinessForm(@PathVariable int businessId){
        ModelAndView modelAndView = ModelViewHelper.GetModeViewForAddLocation(businessId, null);

        return modelAndView;
    }

    @RequestMapping(value="/add/{businessId}", method = RequestMethod.POST)
    public ModelAndView AddLocation(@PathVariable int businessId, @ModelAttribute Location location){
        LocationStore locationStore = new LocationStore();
        //int newLocationId =
                locationStore.saveNew(location);
        String message = "Location saved successfully: "; // + newAppointmentId;
        ModelAndView modelAndView = ModelViewHelper.GetModelViewForAddAppointment(businessId, message);
        return modelAndView;
    }
}

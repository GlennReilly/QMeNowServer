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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView GetAppointmentStatusHome(HttpSession httpSession){
        ModelAndView modelAndView;

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");

            try {
                modelAndView = new ModelViewHelper().getModelViewForLocationsHome(user);
            }
            catch (Exception ex){
                modelAndView = ModelViewHelper.GetModelViewForError(ex.getMessage());
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView AddLocationForBusinessForm(HttpSession httpSession){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") != null) {
            User user = (User)httpSession.getAttribute("User");
            modelAndView = ModelViewHelper.GetModeViewForAddEditLocation(user.getBusinessId(), null, null);
        } else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }

    @RequestMapping(value="/addOrEdit", method = RequestMethod.POST)
    public ModelAndView AddLocation(@ModelAttribute Location location, HttpSession httpSession){
        ModelAndView modelAndView;
        if (httpSession.getAttribute("User") != null) {
            User user = (User)httpSession.getAttribute("User");
            String message;
            location.setBusinessId(user.getBusinessId());
            if (httpSession.getAttribute("CurrentlyEditingLocationId") != null){
                int currentlyEditingLocationId = Integer.parseInt(httpSession.getAttribute("CurrentlyEditingLocationId").toString());
                location.setId(currentlyEditingLocationId);
                new LocationStore().saveUpdate(location);
                message = "Location updated successfully.";
                httpSession.setAttribute("CurrentlyEditingLocationId", null);
            }
            else {
                LocationStore locationStore = new LocationStore();
                locationStore.saveNew(location);
                message = "Location saved successfully.";
            }

            modelAndView = new ModelViewHelper().getModelViewForLocationsHome(user);
            modelAndView.addObject("message", message);
        }
        else{
            modelAndView = ModelViewHelper.GetLoginForm("Please log in");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/update/{locationId}", method = RequestMethod.GET)
    public ModelAndView getUpdateLocationForm(HttpSession httpSession, @PathVariable int locationId){
        ModelAndView modelAndView;

        if (httpSession.getAttribute("User") == null) {
            modelAndView = ModelViewHelper.GetLoginForm(null);
        }
        else{
            User user = (User)httpSession.getAttribute("User");

            try {
                modelAndView = ModelViewHelper.GetModeViewForAddEditLocation(user.getBusinessId(), null, locationId);
                httpSession.setAttribute("CurrentlyEditingLocationId", locationId);
            }
            catch (Exception ex){
                modelAndView = ModelViewHelper.GetModelViewForError(ex.getMessage());
            }
        }
        return modelAndView;
    }
}

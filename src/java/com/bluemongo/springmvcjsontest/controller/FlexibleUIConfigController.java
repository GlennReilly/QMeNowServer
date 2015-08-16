package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.ConfigOptions;
import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;
import com.bluemongo.springmvcjsontest.service.ReconfigurableAppConfigService;
import com.google.appengine.api.urlfetch.HTTPMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * Created by glenn on 9/08/15.
 */


@RestController
@RequestMapping("/FlexibleUIConfig")
public class FlexibleUIConfigController {
    private static final Logger logger = LogManager.getLogger(FlexibleUIConfigController.class);

    @RequestMapping(value = "/edit/{configID}", method = RequestMethod.GET)
    public ModelAndView GetEditConfigForm(@PathVariable int configID){
        ModelAndView modelAndView = new ModelAndView();
        ReconfigurableAppConfig appConfig = ReconfigurableAppConfigService.getConfig(configID);
        if (appConfig == null) {
            logger.error("No appConfig found with configID: " + configID);
            modelAndView.setViewName("Error");
        }else {
            ConfigOptions configOptions = new ConfigOptions();
            configOptions.setCurrentAppConfig(appConfig);
            modelAndView.setViewName("EditConfigForm");
            modelAndView.addObject("command", configOptions);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String EditConfig2(){
        ReconfigurableAppConfig appConfig = new ReconfigurableAppConfig();

        return "hello";
    }

    @RequestMapping(value = "/edit/{configID}", method = RequestMethod.POST)
    public ReconfigurableAppConfig EditConfig(@PathVariable int configID){
        ReconfigurableAppConfig appConfig = new ReconfigurableAppConfig();

        return appConfig;
    }

    @RequestMapping(value = "/save/{configID}")
    public String SaveConfig2(@PathVariable int configID){

        return "Saved: " + configID;
    }

    @RequestMapping(value = "/save/{configID}", method = RequestMethod.POST)
    public String SaveConfig(@PathVariable int configID, @RequestParam int numberOfButtonsRequired,
                @RequestParam String[] selectedButtonStyle,
                @RequestParam String selectedButtonDestination){

        return "Saved: " + configID + ", numberOfButtonsRequired: " + numberOfButtonsRequired
                + ", selectedButtonStyle: " + selectedButtonStyle[1]
                + ", selectedButtonDestination: " + selectedButtonDestination;
    }

}

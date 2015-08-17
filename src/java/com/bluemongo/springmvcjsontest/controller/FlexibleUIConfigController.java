package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.AppButton;
import com.bluemongo.springmvcjsontest.model.ButtonStyle;
import com.bluemongo.springmvcjsontest.model.ConfigOptions;
import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;
import com.bluemongo.springmvcjsontest.service.ReconfigurableAppConfigService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by glenn on 9/08/15.
 */


@RestController
@RequestMapping("/FlexibleUIConfig")
public class FlexibleUIConfigController {
    private static final Logger logger = LogManager.getLogger(FlexibleUIConfigController.class);

/*    @RequestMapping("/")
    public String index(){
        return "index";
    }*/


    @RequestMapping(value="/")
    public ModelAndView GetIndexPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/index");
        return modelAndView;
    }

    @RequestMapping(value="/buttonStyle/add", method = RequestMethod.GET)
    public ModelAndView GetButtonStyleForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("command",new ButtonStyle());
        modelAndView.setViewName("/FlexibleUIConfig/addButtonStyleForm");
        return modelAndView;
    }

    @RequestMapping(value="/buttonStyle/add", method = RequestMethod.POST)
    public String AddButtonStyle(@ModelAttribute ButtonStyle buttonStyle){
        buttonStyle.save();
        return "button style saved successfully: " + buttonStyle.getName();
    }

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
            modelAndView.setViewName("/FlexibleUIConfig/EditConfigForm");
            modelAndView.addObject("command", configOptions);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{configID}", method = RequestMethod.POST)
    public ReconfigurableAppConfig EditConfig(@PathVariable int configID){
        ReconfigurableAppConfig appConfig = new ReconfigurableAppConfig();

        return appConfig;
    }

    @RequestMapping(value = "/testConfigJson", method = RequestMethod.GET)
    public ReconfigurableAppConfig EditConfig2(){
        ReconfigurableAppConfig appConfig = new ReconfigurableAppConfig();

        appConfig.setTitle("Great trucking songs of the renaissance..");

        AppButton button1 = new AppButton();
        button1.setName("button1");
        button1.setText("button one");
        button1.setBackgroundColorHex("#0066CC");
        button1.setTextColour("#ffffff");
        button1.setPadding("7dp 10dp 3dp 10dp");
        appConfig.addButton(button1);

        AppButton button2 = new AppButton();
        button2.setName("button2");
        button2.setText("button Two");
        button1.setBackgroundColorHex("#33CC33");
        button1.setTextColour("#000000");
        button1.setPadding("14dp 10dp 2dp 10dp");
        appConfig.addButton(button2);

        AppButton button3 = new AppButton();
        button3.setName("button3");
        button3.setText("button three");
        button1.setBackgroundColorHex("#CCFF99");
        button1.setTextColour("#006666");
        button1.setPadding("10dp 17dp 3dp 10dp");
        appConfig.addButton(button3);

        return appConfig;
    }



    @RequestMapping(value = "/save/{configID}")
    public String SaveConfig2(@PathVariable int configID){

        return "Saved: " + configID;
    }

    @RequestMapping(value = "/save/{configID}", method = RequestMethod.POST)
    public String SaveConfig(@PathVariable int configID, @RequestParam int numberOfButtonsRequired,
                @RequestParam String selectedButtonStyle,
                @RequestParam String selectedButtonDestination){

        return "Saved: " + configID + ", numberOfButtonsRequired: " + numberOfButtonsRequired
                + ", selectedButtonStyle: " + selectedButtonStyle
                + ", selectedButtonDestination: " + selectedButtonDestination;
    }

}

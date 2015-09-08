package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.model.*;
import com.bluemongo.springmvcjsontest.service.ConfigHelper;
import com.bluemongo.springmvcjsontest.service.UserFormHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 9/08/15.
 */


@RestController
@RequestMapping("/FlexibleUIConfig")
public class FlexibleUIConfigController {
    private static final Logger logger = LogManager.getLogger(FlexibleUIConfigController.class);

    @RequestMapping(value="/")
    public ModelAndView GetIndexPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/index");
        return modelAndView;
    }

    // Login methods

    @RequestMapping(value = "/login", method=RequestMethod.GET)
    public ModelAndView GetLoginForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("FlexibleUIConfig/login");
        modelAndView.addObject("command", new UserCredentials());
        return  modelAndView;
    }

    @RequestMapping(value = "/login", method=RequestMethod.POST)
    public ModelAndView ProcessLogin(@ModelAttribute UserCredentials userCredentials){
        ModelAndView modelAndView = new ModelAndView();
        User user = User.get(userCredentials);
        if(user != null){
        //if(User.validateCredentials(userCredentials))
            modelAndView.setViewName("FlexibleUIConfig/showUserHome");
        }
        else{
            modelAndView.setViewName("Error");
        }
        return modelAndView;
    }

    // Customer methods

    @RequestMapping(value="/customer/add", method = RequestMethod.GET)
    public ModelAndView GetCustomerAddForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/addCustomerForm");
        modelAndView.addObject("command", new Customer());
        return  modelAndView;
    }

    @RequestMapping(value="/customer/add", method = RequestMethod.POST)
    public String AddCustomer(@ModelAttribute Customer customer){
        customer.save();
        return "Customer saved successfully: " + customer.getBusinessName();
    }

    // User methods
    @RequestMapping(value="/user/add", method = RequestMethod.GET)
    public ModelAndView GetUserAddForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/FlexibleUIConfig/addUserForm");
        modelAndView.addObject("command", new UserFormHelper());
        return  modelAndView;
    }

    @RequestMapping(value="/user/add", method = RequestMethod.POST)
    public String AddUser(@ModelAttribute UserFormHelper user) {
        user.save();
        return "User saved successfully: " + user.getFirstName() + " " + user.getLastName();
    }
    // Button style methods

    @RequestMapping(value="/buttonStyle/add", method = RequestMethod.GET)
    public ModelAndView GetButtonStyleForm(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("command", new ButtonStyle());
        modelAndView.setViewName("/FlexibleUIConfig/addButtonStyleForm");
        return modelAndView;
    }

    @RequestMapping(value="/buttonStyle/add", method = RequestMethod.POST)
    public String AddButtonStyle(@ModelAttribute ButtonStyle buttonStyle){
        buttonStyle.save();
        return "button style saved successfully: " + buttonStyle.getName();
    }

    // Edit Config methods

    @RequestMapping(value = "/edit/{configID}", method = RequestMethod.GET)
    public ModelAndView GetEditConfigForm(@PathVariable int configID){
        ModelAndView modelAndView = new ModelAndView();
        ConfigHelper configHelper = ConfigHelper.get(configID);
        ReconfigurableAppConfig appConfig = configHelper.getCurrentAppConfig();
        if (appConfig == null) {
            logger.error("No appConfig found with configID: " + configID);
            modelAndView.setViewName("Error");
        }else {
            configHelper.setAvailableButtonStyles();
            modelAndView.setViewName("/FlexibleUIConfig/EditConfigForm");
            modelAndView.addObject("command", configHelper);
        }
        return modelAndView;
    }



    @RequestMapping(value = "/save/{configID}", method = RequestMethod.POST)
    public String SaveConfig(@PathVariable int configID,
                             @RequestParam String pageTitle,
                             @RequestParam int numberOfButtonsRequired, //probably not really required by this point..
                             @RequestParam String buttonTexts,
                             @RequestParam String selectedButtonStyles,
                             @RequestParam String selectedButtonDestinations){

        List<AppButton> appButtonList = new ArrayList<>();
        String[] arrButtonTexts = buttonTexts.split(",");
        List<String> buttonTextList = new ArrayList<>();
        for (String strButtonText : arrButtonTexts){
            if (strButtonText.length()>0){
                buttonTextList.add(strButtonText);
            }
        }

        String[] arrButtonStyles = selectedButtonStyles.split(",");
        List<ButtonStyle> buttonStyleList = new ArrayList<>();
        for(String strButtonStyleId : arrButtonStyles){
            int buttonStyleId = Integer.parseInt(strButtonStyleId);
            if (buttonStyleId > 0) { //not sure why the 'please select' option is coming through as one of the selected results, this counters it.
                ButtonStyle buttonStyle = ButtonStyle.get(buttonStyleId);
                buttonStyleList.add(buttonStyle);
            }
        }
//selectedButtonDestinations next..
        String[] arrButtonDestinations = selectedButtonDestinations.split(",");
        List<ButtonDestination> buttonDestinationList = new ArrayList<>();
        for (String strButtonDestinationId : arrButtonDestinations){
            int buttonDestinationId = Integer.parseInt(strButtonDestinationId);
            if (buttonDestinationId > 0){
                //TODO - finish the below
                ButtonDestination buttonDestination = ButtonDestination.get(buttonDestinationId);
                buttonDestinationList.add(buttonDestination);
            }
        }

        //Now for each buttonText, button destination and buttonStyle - create a button object.

        for (int i = 0; i < buttonDestinationList.size(); i++){
            String buttonText = buttonTextList.get(i);
            ButtonDestination buttonDestination = buttonDestinationList.get(i);
            ButtonStyle buttonStyle = buttonStyleList.get(i);
            AppButton appButton = new AppButton(buttonText, buttonDestination, buttonStyle);
            appButtonList.add(appButton);
        }

        ConfigHelper configHelper = ConfigHelper.get(configID);
        ReconfigurableAppConfig appConfig = configHelper.getCurrentAppConfig();
        appConfig.setButtonList(appButtonList);
        configHelper.save();

        return "Saved new config, title: " + pageTitle + ", id:" + configID + ", numberOfButtonsRequired: " + numberOfButtonsRequired
                + ", buttonTexts: " + buttonTexts
                + ", selectedButtonStyles: " + selectedButtonStyles
                + ", selectedButtonDestinations: " + selectedButtonDestinations;
    }



    @RequestMapping(value = "/testConfigJson", method = RequestMethod.GET)
    public ReconfigurableAppConfig EditConfig2(){
        ReconfigurableAppConfig appConfig = new ReconfigurableAppConfig();

        appConfig.setPageTitle("Great trucking songs of the renaissance..");

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


}

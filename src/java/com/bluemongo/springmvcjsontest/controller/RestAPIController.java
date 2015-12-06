package com.bluemongo.springmvcjsontest.controller;

import com.bluemongo.springmvcjsontest.api.AppointmentServiceAPI;
import com.bluemongo.springmvcjsontest.model.*;
import com.bluemongo.springmvcjsontest.service.AppointmentResult;
import com.bluemongo.springmvcjsontest.service.ConfigHelper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 1/11/15.
 */
@RestController
@RequestMapping("/REST")
public class RestAPIController implements AppointmentServiceAPI {

    @RequestMapping(value="/buttonStyle/add", method = RequestMethod.POST)
    public String AddButtonStyle(@ModelAttribute ButtonStyle buttonStyle){
        buttonStyle.save();
        return "button style saved successfully: " + buttonStyle.getName();
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

    @RequestMapping(value = "/user/getAppointmentsTest/{userId}", method = RequestMethod.GET)
    public List<Appointment> getUserAppointmentsTest(@PathVariable int userId){
        List<Appointment> appointmentList = new ArrayList<>();
        if (userId == 1234){
            Appointment appointment = new Appointment();
            appointment.setMessageToCustomer("successful Retrofit call");
            appointmentList.add(appointment);
        }else{
            Appointment appointment = new Appointment();
            appointment.setMessageToCustomer("no appointments found on server");
            appointmentList.add(appointment);
        }

        return appointmentList;
    }


    @Override
    public List<AppointmentResult> getAppointmentsByUserIDAndName(int customerId, String firstName, String lastName) {
        return null;
    }

    @Override
    public List<AppointmentResult> getAppointments(int userId) {
        return null;
    }
}

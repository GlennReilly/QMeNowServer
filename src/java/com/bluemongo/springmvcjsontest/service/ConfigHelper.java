package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.ButtonStyle;
import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;
import com.bluemongo.springmvcjsontest.persistence.ConfigStore;

import java.util.List;

/**
 * Created by glenn on 9/08/15.
 */
public class ConfigHelper { //This is an helper class that deals with presenting the various options and contains a currentAppConfig that represents the final config.

    private ReconfigurableAppConfig currentAppConfig;
    private int customerId;
    private String headerImagePath;
    private int numberOfButtonsRequired;
    private String buttonTexts;
    private ButtonStyle selectedButtonStyles;
    private String selectedButtonDestinations;
    private List<ButtonStyle> availableButtonStyles;
    private String configName;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ConfigHelper(ReconfigurableAppConfig config){
        this.currentAppConfig = config;
    }

    public static ConfigHelper get(int configID) {
        ConfigStore configStore = new ConfigStore();
        ConfigHelper configHelper = configStore.get(configID);

        return configHelper;
    }


    public int save(){
        ConfigStore configStore = new ConfigStore();
        int savedId;
        if(this.getId() > 0){
            savedId = configStore.saveUpdate(this);
        }
        else{
            savedId = configStore.saveNew(this);
        }
        return savedId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public void setAvailableButtonStyles(List<ButtonStyle> availableButtonStyles) {
        this.availableButtonStyles = availableButtonStyles;
    }

    public List<ButtonStyle> getAvailableButtonStyles() {
        return availableButtonStyles;
    }

    public void setAvailableButtonStyles() {
        this.availableButtonStyles = ButtonStyle.getAllForCustomer(123L);
    }

    public ReconfigurableAppConfig getCurrentAppConfig() {
        return currentAppConfig;
    }


    //i think the below might need to be moved into the currentAppConfig..

    public String getSelectedButtonDestinations() {
        return selectedButtonDestinations;
    }

    public void setSelectedButtonDestinations(String selectedButtonDestinations) {
        this.selectedButtonDestinations = selectedButtonDestinations;
    }

    public ButtonStyle getSelectedButtonStyles() {
        return selectedButtonStyles;
    }

    public void setSelectedButtonStyles(ButtonStyle selectedButtonStyles) {
        this.selectedButtonStyles = selectedButtonStyles;
    }

    public int getNumberOfButtonsRequired() {
        return numberOfButtonsRequired;
    }

    public void setNumberOfButtonsRequired(int numberOfButtonsRequired) {
        this.numberOfButtonsRequired = numberOfButtonsRequired;
    }

}

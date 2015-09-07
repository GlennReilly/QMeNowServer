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
    private String pageTitle;
    private int numberOfButtonsRequired;
    private String buttonTexts;
    private ButtonStyle selectedButtonStyles;
    private String selectedButtonDestinations;
    private List<ButtonStyle> availableButtonStyles;
    private String configName;


    /*    public static ReconfigurableAppConfig getConfig(int configID) {
        ReconfigurableAppConfig  reconfigurableAppConfig = ReconfigurableAppConfig.get(configID);
        return reconfigurableAppConfig;
    }*/

    public ConfigHelper(ReconfigurableAppConfig config){
        this.currentAppConfig = config;
    }

    public static ReconfigurableAppConfig get(int configID) {
        ConfigStore configStore = new ConfigStore();
        ReconfigurableAppConfig appConfig = configStore.get(configID);

        return appConfig;
    }

/*    public static int save(ReconfigurableAppConfig config){
        ConfigStore configStore = new ConfigStore();
        int savedId;
        if(config.getId()>0){
            savedId = configStore.saveUpdate(config);
        }
        else{
            savedId = configStore.saveNew(config);
        }
        return savedId;
    }*/

    public int save(){
        ConfigStore configStore = new ConfigStore();
        int savedId;
        if(getCurrentAppConfig().getId()>0){
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

    public String getButtonTexts() {
        return buttonTexts;
    }

    public void setButtonTexts(String buttonTexts) {
        this.buttonTexts = buttonTexts;
    }

    public String getHeaderImagePath() {
        return currentAppConfig.getHeaderImagePath();
    }

    public void setHeaderImagePath(String headerImagePath) {
        this.headerImagePath = headerImagePath;
        currentAppConfig.setHeaderImagePath(headerImagePath);
    }


    public String getPageTitle() {
        return pageTitle = currentAppConfig.getTitle();
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
        this.currentAppConfig.setTitle(this.pageTitle);
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

/*    public void setCurrentAppConfig(ReconfigurableAppConfig currentAppConfig) {
        this.currentAppConfig = currentAppConfig;
    }*/

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


    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }
}

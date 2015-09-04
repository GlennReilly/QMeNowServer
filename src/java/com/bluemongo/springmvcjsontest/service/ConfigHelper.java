package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.ButtonStyle;
import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;

import java.util.List;

/**
 * Created by glenn on 9/08/15.
 */
public class ConfigHelper { //This is an helper class that deals with presenting the various options and contains a currentAppConfig that represents the final config.

    private ReconfigurableAppConfig currentAppConfig;
    private String headerImagePath;
    private String pageTitle;
    private int numberOfButtonsRequired;
    private String buttonTexts;
    private ButtonStyle selectedButtonStyles;
    private String selectedButtonDestinations;
    private List<ButtonStyle> availableButtonStyles;

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

    public void setCurrentAppConfig(ReconfigurableAppConfig currentAppConfig) {
        this.currentAppConfig = currentAppConfig;
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

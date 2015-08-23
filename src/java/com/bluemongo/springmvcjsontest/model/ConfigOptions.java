package com.bluemongo.springmvcjsontest.model;

import java.util.List;

/**
 * Created by glenn on 9/08/15.
 */
public class ConfigOptions { //This is an helper class that deals with presenting the various options and contains a currentAppConfig that represents the final config.

    private ReconfigurableAppConfig currentAppConfig;
    private String headerImagePath;
    private String pageTitle;
    private int numberOfButtonsRequired;
    private String selectedButtonStyle;
    private String selectedButtonDestination;
    private List<ButtonStyle> availableButtonStyles;

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


    public List<ButtonStyle> getAvailableButtonStyles() {
        return availableButtonStyles;
    }

    public void setAvailableButtonStyles() {
        this.availableButtonStyles = ButtonStyle.get(123L);
    }


    //i think the below might need to be moved into the currentAppConfig..

    public String getSelectedButtonDestination() {
        return selectedButtonDestination;
    }

    public void setSelectedButtonDestination(String selectedButtonDestination) {
        this.selectedButtonDestination = selectedButtonDestination;
    }

    public String getSelectedButtonStyle() {
        return selectedButtonStyle;
    }

    public void setSelectedButtonStyle(String selectedButtonStyle) {
        this.selectedButtonStyle = selectedButtonStyle;
    }

    public int getNumberOfButtonsRequired() {
        return numberOfButtonsRequired;
    }

    public void setNumberOfButtonsRequired(int numberOfButtonsRequired) {
        this.numberOfButtonsRequired = numberOfButtonsRequired;
    }

    public void setCurrentAppConfig(ReconfigurableAppConfig currentAppConfig) {
        this.currentAppConfig = currentAppConfig;
    }

    public ReconfigurableAppConfig getCurrentAppConfig() {
        return currentAppConfig;
    }
}

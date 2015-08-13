package com.bluemongo.springmvcjsontest.model;

/**
 * Created by glenn on 9/08/15.
 */
public class ConfigOptions {

    private ReconfigurableAppConfig currentAppConfig;
    private int numberOfButtonsRequired;

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

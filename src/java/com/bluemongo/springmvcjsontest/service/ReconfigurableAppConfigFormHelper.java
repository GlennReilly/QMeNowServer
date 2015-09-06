package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;

/**
 * Created by glenn on 9/08/15.
 */
public class ReconfigurableAppConfigFormHelper {

    public static ReconfigurableAppConfig getConfig(int configID) {
        ReconfigurableAppConfig  reconfigurableAppConfig = ReconfigurableAppConfig.get(configID);
        return reconfigurableAppConfig;
    }
}

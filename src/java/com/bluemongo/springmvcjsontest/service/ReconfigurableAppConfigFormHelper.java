package com.bluemongo.springmvcjsontest.service;

import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;

/**
 * Created by glenn on 9/08/15.
 */
public class ReconfigurableAppConfigFormHelper {

    public static ReconfigurableAppConfig getConfig(int configID) {
        ReconfigurableAppConfig reconfigurableAppConfig = null;
        if(configID == 42) {
            reconfigurableAppConfig = new ReconfigurableAppConfig();
            reconfigurableAppConfig.setId(configID + 1);
        }
        if(configID == 43) {
            reconfigurableAppConfig = new ReconfigurableAppConfig();
            reconfigurableAppConfig.setId(configID);
        }

        return reconfigurableAppConfig;
    }
}

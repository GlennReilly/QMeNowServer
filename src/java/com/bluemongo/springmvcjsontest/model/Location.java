package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.LocationStore;

/**
 * Created by glenn on 13/10/15.
 */
public class Location {
    String locationName;
    int customerId;
    private LocationStore locationStore = new LocationStore();

    public void save(){
        locationStore.saveNew(this);
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}

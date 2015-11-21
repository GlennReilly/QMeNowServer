package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.LocationStore;

/**
 * Created by glenn on 13/10/15.
 */
public class Location {
    int id;
    String locationName;
    int businessId;
    private LocationStore locationStore = new LocationStore();

    public void save(){
        locationStore.saveNew(this);
    }

    public String getLocationName() {
        if(locationName == null){
            locationName = "";
        }
        return locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getBusinessId(){
        return businessId;
    }

}

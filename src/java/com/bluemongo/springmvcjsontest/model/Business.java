package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.BusinessStore;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by glenn on 31/08/15.
 */
public class Business {
    private int id;
    private String businessName = "";
    private String phoneNumber = "";
    private String emailAddress = "";
    private String physicalAddress = "";
    private String contactName = "";
    private MultipartFile logo;
    private String logoName;
    private int defaultLocationId;


    public int saveNew() {
        BusinessStore businessStore = new BusinessStore();
        int newBusinessId = businessStore.saveNew(this);
        return newBusinessId;
    }

    public int saveUpdate() {
        BusinessStore businessStore = new BusinessStore();
        int updatedBusinessId = businessStore.saveUpdate(this);
        return updatedBusinessId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
        new BusinessStore().setLogoName(this.getId(),logoName);
    }

    public String getLogoName() {
        return logoName;
    }

    public int getDefaultLocationId() {
        return defaultLocationId;
    }

    public void setDefaultLocationId(int defaultLocationId) {
        this.defaultLocationId = defaultLocationId;
    }
}

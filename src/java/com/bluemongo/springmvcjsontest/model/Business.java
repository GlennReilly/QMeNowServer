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
    private String logoFilePath;


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

    public void setLogoName(String logoName, String absolutePath) {
        this.logoName = logoName;
        this.setLogoFilePath(absolutePath);
        new BusinessStore().setLogoName(this.getId(),logoName, absolutePath);
    }

    public String getLogoName() {
/*        String fileName = getBusinessName() + "_" + getId() + "_" + logo.getOriginalFilename();
        String filePathAndName = "/" + fileName;
        String imageRelativePath = "/resources/images" + filePathAndName;
        File logoFile = new File(servletContext.getRealPath("/") + imageRelativePath);*/

        return logoName;
    }

    public int getDefaultLocationId() {
        return defaultLocationId;
    }

    public void setDefaultLocationId(int defaultLocationId) {
        this.defaultLocationId = defaultLocationId;
    }


    public void setLogoFilePath(String logoFilePath) {
        this.logoFilePath = logoFilePath;
    }

    public String getLogoFilePath() {
        return logoFilePath;
    }
}

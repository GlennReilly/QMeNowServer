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
    private transient String contactName = "";
    private transient MultipartFile logo;
    private transient String logoName;
    private int defaultLocationId;
    private transient String logoFilePath;
    private String buttonColourHexCode;
    private String headerColourHexCode;
    private String backgroundColourHexCode;
    private String footerColourHexCode;



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

    public String getButtonColourHexCode() {
        return buttonColourHexCode;
    }

    public void setButtonColourHexCode(String buttonColourHexCode) {
        this.buttonColourHexCode = buttonColourHexCode;
    }

    public String getHeaderColourHexCode() {
        return headerColourHexCode;
    }

    public void setHeaderColourHexCode(String headerColourHexCode) {
        this.headerColourHexCode = headerColourHexCode;
    }

    public String getBackgroundColourHexCode() {
        return backgroundColourHexCode;
    }

    public void setBackgroundColourHexCode(String backgroundColourHexCode) {
        this.backgroundColourHexCode = backgroundColourHexCode;
    }

    public String getFooterColourHexCode() {
        return footerColourHexCode;
    }

    public void setFooterColourHexCode(String footerColourHexCode) {
        this.footerColourHexCode = footerColourHexCode;
    }
}

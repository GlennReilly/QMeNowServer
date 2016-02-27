package com.bluemongo.springmvcjsontest.model;

/**
 * Created by glenn on 30/01/16.
 */
public class BusinessDTO {
    private int id;
    private String businessName = "";
    private String buttonColourHexCode;
    private String headerColourHexCode;
    private String backgroundColourHexCode;
    //private String footerColourHexCode;
    /*    private String phoneNumber = "";
    private String emailAddress = "";
    private String physicalAddress = "";*/




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

/*    public String getFooterColourHexCode() {
        return footerColourHexCode;
    }

    public void setFooterColourHexCode(String footerColourHexCode) {
        this.footerColourHexCode = footerColourHexCode;
    }*/

    /*    public String getPhoneNumber() {
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
    }*/
}

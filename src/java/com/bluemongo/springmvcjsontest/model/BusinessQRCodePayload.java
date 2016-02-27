package com.bluemongo.springmvcjsontest.model;

/**
 * Created by glenn on 31/01/16.
 */
public class BusinessQRCodePayload {
    private BusinessDTO businessDTO = new BusinessDTO();
    private String dateTimeString;

    private String Content;


    public String getDateTimeString() {
        return dateTimeString;
    }

    public void setDateTimeString(String dateTimeString) {
        this.dateTimeString = dateTimeString;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public BusinessDTO getBusinessDTO() {
        return businessDTO;
    }

    public String getBusinessName() {
        return businessDTO.getBusinessName();
    }

    public void setBusinessName(String businessName) {
        businessDTO.setBusinessName(businessName);
    }

    public String getButtonColourHexCode() {
        return businessDTO.getButtonColourHexCode();
    }

    public void setButtonColourHexCode(String buttonColourHexCode) {
        this.businessDTO.setButtonColourHexCode(buttonColourHexCode);
    }

    public String getHeaderColourHexCode() {
        return businessDTO.getHeaderColourHexCode();
    }

    public void setHeaderColourHexCode(String headerColourHexCode) {
        this.businessDTO.setHeaderColourHexCode(headerColourHexCode);
    }

    public String getBackgroundColourHexCode() {
        return businessDTO.getBackgroundColourHexCode();
    }

    public void setBackgroundColourHexCode(String backgroundColourHexCode) {
        this.businessDTO.setBackgroundColourHexCode(backgroundColourHexCode);
    }
}

package com.bluemongo.springmvcjsontest.model;

/**
 * Created by glenn on 31/01/16.
 */
public class BusinessQRCodePayload {
    private String dateTimeString;
    private String businessName;
    private String buttonColourHexCode;
    private String headerColourHexCode;
    private String backgroundColourHexCode;
    private String footerColourHexCode;
    private String Content;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

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

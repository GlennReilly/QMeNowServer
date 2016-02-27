package com.bluemongo.springmvcjsontest.model;

/**
 * Created by glenn on 4/10/15.
 */
public class CustomerQRCodePayload {
    private String dateTimeString;
    private BusinessDTO businessDTO = new BusinessDTO();
    private String customerFirstName;
    private String customerLastName;
    private Integer customerId;
    private String Content;


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
        this.businessDTO.setBackgroundColourHexCode(buttonColourHexCode);
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

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}

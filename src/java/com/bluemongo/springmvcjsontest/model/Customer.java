package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.BusinessStore;
import com.bluemongo.springmvcjsontest.persistence.CustomerStore;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import utils.InputHelper;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by glenn on 1/09/15.
 */
public class Customer {
    private static CustomerStore customerStore = new CustomerStore();
    private int id;
    private String firstName = "";
    private String lastName = "";
    private String name = "";
    private String phoneNumber = "";
    private String emailAddress = "";
    private String physicalAddress = "";
    private Gender gender;
    private Date DOB;
    private int businessId;
    private String barcodeImageString = "";



    public Customer() {

    }


    public Customer(int id) {
        this.id = id;
        setBarcodeImageString();
    }


    public static Customer get(int businessId, int customerId) {
        return customerStore.get(businessId, customerId);
    }

/*
    public List<Customer> getAllCustomers(boolean active){
        List<Customer> customerList = new ArrayList<>();
        customerList = customerStore.getAll(active);
        return  customerList;
    }*/

    public int saveNew() {
        int newCustomerId = customerStore.saveNew(this);
        return newCustomerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        setBarcodeImageString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        setBarcodeImageString();
    }

    public String getLastName() {
        return lastName;
    }


    public String getName() {
        return getFirstName() + " " + getLastName();
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
        setBarcodeImageString();
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


    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Date getDOB() {
        return DOB;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public enum Gender {NOT_SPECIFIED, MALE, FEMALE,}

/*    public void setBarcodeImageString(String barcodeImageString) {
        this.barcodeImageString = getBarcodeImageString();
    }*/

    public void setBarcodeImageString(){
        Hashtable hintMap = new Hashtable();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int height = 300;
        int width = 300;
        BitMatrix byteMatrix = null;
        try {
            byteMatrix = qrCodeWriter.encode(getBarcodePayload(this), BarcodeFormat.QR_CODE, width, height, hintMap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        // Make the BufferedImage to hold the barcode
        int matrixWidth = byteMatrix.getWidth();
        int matrixHeight = byteMatrix.getHeight();
        BufferedImage image = new BufferedImage(matrixWidth, matrixHeight, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixHeight);
        // Paint and saveNew the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixHeight; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String imageString = "data:image/png;base64," +
                DatatypeConverter.printBase64Binary(baos.toByteArray());


        this.barcodeImageString = imageString;
    }

    public String getBarcodeImageString(){
        return barcodeImageString;
    }


    private String getBarcodePayload(Customer customer) {

        if (customer.equals(null) )
        {
            customer = new CustomerStore().get(this.id);
        }
        String formattedNow = getFormattedNowDateString();

        CustomerQRCodePayload customerQRCodePayload = new CustomerQRCodePayload();
        customerQRCodePayload.setDateTimeString(formattedNow);
        customerQRCodePayload.setCustomerId(customer.getId());
        customerQRCodePayload.setCustomerFirstName(customer.getFirstName());
        customerQRCodePayload.setCustomerLastName(customer.getLastName());
        customerQRCodePayload.setContent("");

        Business business = new BusinessStore().get(customer.getBusinessId());
        if (business != null) {
            customerQRCodePayload.getBusinessDTO().setId(business.getId());
            customerQRCodePayload.getBusinessDTO().setBusinessName(business.getBusinessName());
            customerQRCodePayload.getBusinessDTO().setBackgroundColourHexCode(business.getBackgroundColourHexCode());
            customerQRCodePayload.getBusinessDTO().setHeaderColourHexCode(business.getHeaderColourHexCode());
            customerQRCodePayload.getBusinessDTO().setButtonColourHexCode(business.getButtonColourHexCode());
            customerQRCodePayload.getBusinessDTO().setLogoFileName(business.getLogoFileName());
        }

        Gson gson = new Gson();
        String jsonBarcodePayload = gson.toJson(customerQRCodePayload);

        return jsonBarcodePayload;

    }

    private String getFormattedNowDateString() {
        //"yyyy-MM-dd'T'HH:mm:ssz" ISO8601
        //2015-10-10T11:16+00:00

        Date now = Calendar.getInstance().getTime();
        String dateString = InputHelper.getISO8601StringFromDate(now);
        return dateString;
    }

}

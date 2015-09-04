package com.bluemongo.springmvcjsontest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.bluemongo.springmvcjsontest.persistence.ButtonStyleStore;

/**
 * Created by glenn on 17/08/15.
 */
public class ButtonStyle {
    private String name;
    private String textColour;
    private String backgroundColorHex;
    private String padding;
    private int id;
    private Date createdDate;
    private long customerId;
    private String classification1;
    private String classification2;
    private String classification3;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getClassification1() {
        return classification1;
    }

    public void setClassification1(String classification1) {
        this.classification1 = classification1;
    }

    public String getClassification2() {
        return classification2;
    }

    public void setClassification2(String classification2) {
        this.classification2 = classification2;
    }

    public String getClassification3() {
        return classification3;
    }

    public void setClassification3(String classification3) {
        this.classification3 = classification3;
    }

    public static List<ButtonStyle> getAll(){
        return new ArrayList<>();
    }

    public void save() {
        ButtonStyleStore buttonStyleStore = new ButtonStyleStore();
        buttonStyleStore.saveNew(this);
    }

    public static List<ButtonStyle> getAllForCustomer(long customerId){
        ButtonStyleStore buttonStyleStore = new ButtonStyleStore();
        return buttonStyleStore.getAllForCustomer(customerId);
    }

    public static ButtonStyle get(int buttonStyleId) {
        ButtonStyleStore buttonStyleStore = new ButtonStyleStore();
        return buttonStyleStore.get(buttonStyleId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextColour() {
        return textColour;
    }

    public void setTextColour(String textColour) {
        this.textColour = textColour;
    }

    public String getBackgroundColorHex() {
        return backgroundColorHex;
    }

    public void setBackgroundColorHex(String backgroundColorHex) {
        this.backgroundColorHex = backgroundColorHex;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

}

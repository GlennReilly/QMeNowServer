package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.PersistButtonStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 17/08/15.
 */
public class ButtonStyle {
    private String name;
    private String textColour;
    private String backgroundColorHex;
    private String padding;

    public static List<ButtonStyle> getAll(){
        return new ArrayList<>();
    }

    public void save() {
        PersistButtonStyle.save(this);
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

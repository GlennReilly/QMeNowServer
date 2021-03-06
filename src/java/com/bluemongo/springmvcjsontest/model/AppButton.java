package com.bluemongo.springmvcjsontest.model;

/**
 * Created by glenn on 26/07/15.
 */
public class AppButton {
    private String name;
    private String text;
    private ButtonDestination buttonDestination =  null;
    private ButtonStyle buttonStyle = new ButtonStyle();

    public AppButton(){}

    public AppButton(String buttonText, ButtonDestination buttonDestination, ButtonStyle buttonStyle) {
        this.setText(buttonText);
        this.buttonDestination = buttonDestination;
        this.buttonStyle = buttonStyle;
    }

    public ButtonStyle getButtonStyle() {
        return buttonStyle;
    }

    public void setButtonStyle(ButtonStyle buttonStyle) {
        this.buttonStyle = buttonStyle;
    }

    public ButtonDestination getButtonDestination() {
        return buttonDestination;
    }

    public void setButtonDestination(ButtonDestination buttonDestination) {
        this.buttonDestination = buttonDestination;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextColour() {
        return buttonStyle.getTextColour();
    }

    public void setTextColour(String textColour) {
        this.buttonStyle.setTextColour(textColour);
    }

    public String getBackgroundColorHex() {
        return buttonStyle.getBackgroundColorHex();
    }

    public void setBackgroundColorHex(String backgroundColorHex) {
        this.buttonStyle.setBackgroundColorHex(backgroundColorHex);
    }

    public String getPadding() {
        return buttonStyle.getPadding();
    }

    public void setPadding(String padding) {
        this.buttonStyle.setPadding(padding);
    }
}

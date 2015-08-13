package com.bluemongo.springmvcjsontest.model;

import com.sun.jndi.toolkit.url.Uri;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by glenn on 26/07/15.
 */

public class ReconfigurableAppConfig {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int versionNumber;
    private String title;
    private Uri imageUri;
    private List<AppButton> buttonList = new ArrayList<>();

    public List<AppButton> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<AppButton> buttonList) {
        this.buttonList = buttonList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }


    public void addButton(AppButton button) {
        buttonList.add(button);
    }
}

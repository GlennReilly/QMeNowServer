package com.bluemongo.springmvcjsontest.model;

import com.bluemongo.springmvcjsontest.persistence.ConfigStore;
import com.sun.jndi.toolkit.url.Uri;
import sun.security.krb5.Config;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by glenn on 26/07/15.
 */

public class ReconfigurableAppConfig {
    int id;
    private String headerImagePath;
    private int versionNumber;
    private int customerId;
    private String title;
    private Uri imageUri;
    private List<AppButton> buttonList = new ArrayList<>();
    private int revisionNumber;
    private Date createdDate;

    public static ReconfigurableAppConfig get(int configID) {
        ConfigStore configStore = new ConfigStore();
        ReconfigurableAppConfig appConfig = configStore.get(configID);

        return appConfig;
    }

    public void save(){
        ConfigStore configStore = new ConfigStore();
        configStore.saveNew(this);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void setHeaderImagePath(String headerImagePath) {
        this.headerImagePath = headerImagePath;
    }

    public String getHeaderImagePath() {
        return headerImagePath;
    }


    public void setRevisionNumber(int revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public int getRevisionNumber() {
        return revisionNumber;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}

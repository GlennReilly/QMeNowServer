package com.bluemongo.springmvcjsontest.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 13/12/15.
 */
public class AppointmentsResponse {
    private BusinessDTO businessDTO = new BusinessDTO();
    private List<Appointment> appointmentList = new ArrayList<>();
    private List<AppointmentStatus> appointmentStatusList = new ArrayList<>();
    private List<String> errorsList = new ArrayList<>();
    private String appointmentCreationURL;

    public void setBusinessDTOFromBusiness(Business business) {
        this.businessDTO.setBusinessName(business.getBusinessName());
        this.businessDTO.setButtonColourHexCode(business.getButtonColourHexCode());
        this.businessDTO.setHeaderColourHexCode(business.getHeaderColourHexCode());
        this.businessDTO.setBackgroundColourHexCode(business.getBackgroundColourHexCode());
        this.businessDTO.setLogoFileName(business.getLogoFileName());
    }

    public void setBusinessDTO(BusinessDTO businessDTO) {
        this.businessDTO = businessDTO;
    }

    public BusinessDTO getBusinessDTO() {
        return businessDTO;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentCreationURL(String appointmentCreationURL) {
        this.appointmentCreationURL = appointmentCreationURL;
    }

    public String getAppointmentCreationURL() {
        return appointmentCreationURL;
    }

    public List<AppointmentStatus> getAppointmentStatusList() {
        return appointmentStatusList;
    }

    public void setAppointmentStatusList(List<AppointmentStatus> appointmentStatusList) {
        this.appointmentStatusList = appointmentStatusList;
    }

    public void addErrorMessage(String errorMessage) {
        this.errorsList.add(errorMessage);
    }

    public List<String> getErrorsList() {
        return errorsList;
    }
}

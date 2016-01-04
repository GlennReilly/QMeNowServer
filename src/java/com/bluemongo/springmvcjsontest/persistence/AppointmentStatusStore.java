package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.AppointmentStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 16/10/15.
 */
public class AppointmentStatusStore {
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(AppointmentStatusStore.class);



    public int saveNew(AppointmentStatus appointmentStatus) {
        int lastInsertedId = -1;

        String query = " insert into appointmentStatus(businessId, statusName, backgroundColourHexCode)" +
        " select ?,?,? from DUAL" +
        " WHERE NOT exists (select id from appointmentStatus where statusName = ?);";


        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, appointmentStatus.getBusinessId());
            preparedStatement.setString(2, appointmentStatus.getName());
            preparedStatement.setString(3, appointmentStatus.getBackgroundColourHexCode());
            preparedStatement.setString(4, appointmentStatus.getName());

            preparedStatement.executeUpdate();
            logger.info("new AppointmentStatusStore inserted.");

            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                lastInsertedId = resultSet.getInt(1);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return lastInsertedId;
    }

    public List<AppointmentStatus> getAll(int businessId, boolean isActive) {
        List<AppointmentStatus> appointmentStatusList = new ArrayList<>();
        String query = "select id, statusName, businessId, backgroundColourHexCode, sequenceNumber, customerInitiated  from appointmentStatus where businessId = ? AND isActive = ?; ";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, businessId);
            preparedStatement.setBoolean(2, isActive);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                appointmentStatusList.add(getAppointmentStatusFromResultSet(resultSet));
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return appointmentStatusList;

    }

    public AppointmentStatus get(int statusId){
        AppointmentStatus appointmentStatus = new AppointmentStatus();
        String query = "select id, statusName, businessId, backgroundColourHexCode, sequenceNumber, customerInitiated from appointmentStatus where id = ? AND isActive = ?; ";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, statusId);
            preparedStatement.setBoolean(2, true);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                appointmentStatus = getAppointmentStatusFromResultSet(resultSet);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return appointmentStatus;

    }

    private AppointmentStatus getAppointmentStatusFromResultSet( ResultSet resultSet) throws SQLException {
        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setId(resultSet.getInt("id"));
        appointmentStatus.setBusinessId(resultSet.getInt("businessId"));
        appointmentStatus.setName(resultSet.getString("StatusName"));
        appointmentStatus.setBackgroundColourHexCode(resultSet.getString("backgroundColourHexCode"));
        appointmentStatus.setSequenceNumber(resultSet.getInt("sequenceNumber"));
        appointmentStatus.setCustomerInitiated(resultSet.getBoolean("customerInitiated"));
        return appointmentStatus;
    }

    public void saveUpdate(AppointmentStatus appointmentStatus) {
        String query = " update appointmentStatus set businessId=?, statusName=?, backgroundColourHexCode=? WHERE id  = ?;";


        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, appointmentStatus.getBusinessId());
            preparedStatement.setString(2, appointmentStatus.getName());
            preparedStatement.setString(3, appointmentStatus.getBackgroundColourHexCode());
            preparedStatement.setInt(4, appointmentStatus.getId());

            preparedStatement.executeUpdate();
            logger.info("Appointment Status updated.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }

    public void setInactive(int appointmentStatusId) {
        String query = " update appointmentStatus set isActive=0 where id=?;";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,appointmentStatusId);
            preparedStatement.executeUpdate();
            logger.info("Appointment Status deactivated.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }
}

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
        //String query = "insert into appointmentStatus(businessId, statusName, backgroundColourHexCode) values (?,?,?)";

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

    public List<AppointmentStatus> getAll(int businessId){
        List<AppointmentStatus> appointmentStatusList = new ArrayList<>();
        String query = "select id, statusName, businessId, backgroundColourHexCode from appointmentStatus where businessId = ?";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, businessId);

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

    private AppointmentStatus getAppointmentStatusFromResultSet( ResultSet resultSet) throws SQLException {
        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setId(resultSet.getInt("id"));
        appointmentStatus.setBusinessId(resultSet.getInt("businessId"));
        appointmentStatus.setName(resultSet.getString("StatusName"));
        appointmentStatus.setBackgroundColourHexCode(resultSet.getString("backgroundColourHexCode"));
        return appointmentStatus;
    }
}

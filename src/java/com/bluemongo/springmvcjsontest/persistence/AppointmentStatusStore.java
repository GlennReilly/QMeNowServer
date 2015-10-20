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
        String query = "insert into appointmentStatus(customerId, statusName) values (?,?)";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, appointmentStatus.getCustomerId());
            preparedStatement.setString(2, appointmentStatus.getStatusName());

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

    public List<AppointmentStatus> getAll(int customerId){
        List<AppointmentStatus> appointmentStatusList = new ArrayList<>();
        String query = "select statusName from appointmentStatus where customerId = ?";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                AppointmentStatus appointmentStatus = new AppointmentStatus();
                appointmentStatus.setCustomerId(customerId);
                appointmentStatus.setStatusName(resultSet.getString("StatusName"));
                appointmentStatusList.add(appointmentStatus);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return appointmentStatusList;

    }
}

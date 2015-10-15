package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Date;

/**
 * Created by glenn on 13/10/15.
 */
public class AppointmentStore {
    String appointmentType;
    Date appointmentDate;
    int userId;
    String messageToUser;

    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(BusinessStore.class);

    public int saveNew(Appointment appointment) {
        int lastInsertedId = -1;
        String query = "insert into appointment(appointmentType, appointmentDate, userId, messageToUser) values (?,?,?,?)";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, appointment.getAppointmentType());
            preparedStatement.setTimestamp(2, new Timestamp(appointment.getAppointmentDate().getTime()));
            preparedStatement.setInt(3, appointment.getUserId());
            preparedStatement.setString(4, appointment.getMessageToUser());
            preparedStatement.executeUpdate();
            logger.info("new Customer inserted.");

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
}

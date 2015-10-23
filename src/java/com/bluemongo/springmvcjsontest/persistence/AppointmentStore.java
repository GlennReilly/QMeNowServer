package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Appointment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by glenn on 13/10/15.
 */
public class AppointmentStore {

    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(AppointmentStore.class);

        public int saveNew(Appointment appointment) {
        int lastInsertedId = -1;
        String query = "insert into appointment(appointmentTypeId, appointmentDate, customerId, status, messageToUser, locationId) values (?,?,?,?,?,?)";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, appointment.getAppointmentTypeId());
            if (appointment.getAppointmentDate() != null) {
                preparedStatement.setTimestamp(2, new Timestamp(appointment.getAppointmentDate().getTime()));
            }
            else {
                preparedStatement.setTimestamp(2, null);
            }
            preparedStatement.setInt(3, appointment.getCustomerId());
            preparedStatement.setInt(4, appointment.getStatus());
            preparedStatement.setString(5, appointment.getMessageToCustomer());
            preparedStatement.setInt(6, appointment.getLocationId());
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

        public List<Appointment> getAllForToday(int userId){
            //chronologically ordered list
            List<Appointment> appointmentList = new ArrayList<>();
            String query = "SELECT id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, status FROM appointment" +
                    " where userId = ?;";

            try(Connection connection = dbHelper.getConnection()){
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, userId);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    Appointment appointment = new Appointment(resultSet.getInt("id"));
                    appointment.setAppointmentTypeId(resultSet.getInt("appointmentTypeId"));
                    appointment.setAppointmentDate(resultSet.getDate("appointmentDate"));
                    appointment.setUserId(resultSet.getInt("userId"));
                    appointment.setMessageToCustomer(resultSet.getNString("messageToUser"));

                    appointmentList.add(appointment);
                }
            }
            catch (Exception ex){
                logger.info(ex.getMessage());
            }

            return appointmentList;
        }

    public List<Appointment> getAllForDate(int userId, Date date){
        //chronologically ordered list
        List<Appointment> appointmentList = new ArrayList<>();
        String query = "SELECT id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, status FROM appointment" +
                " where userId = ? and appointmentDate between ? and DATE_ADD(?,INTERVAL 1 day) ;";

        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Appointment appointment = new Appointment(resultSet.getInt("id"));
                appointment.setAppointmentTypeId(resultSet.getInt("appointmentTypeId"));
                appointment.setAppointmentDate(resultSet.getDate("appointmentDate"));
                appointment.setUserId(resultSet.getInt("userId"));
                appointment.setMessageToCustomer(resultSet.getNString("messageToUser"));

                appointmentList.add(appointment);
            }
        }
        catch (Exception ex){
            logger.info(ex.getMessage());
        }

        return appointmentList;
    }

    public List<Appointment> getAll(int userId){
        //TODO chronologically ordered list?
        List<Appointment> appointmentList = new ArrayList<>();
        String query = "SELECT id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, status FROM appointment" +
                " where userId = ?;";

        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Appointment appointment = new Appointment(resultSet.getInt("id"));
                appointment.setAppointmentTypeId(resultSet.getInt("appointmentType"));
                appointment.setAppointmentDate(resultSet.getDate("appointmentDate"));
                appointment.setUserId(resultSet.getInt("userId"));
                appointment.setMessageToCustomer(resultSet.getNString("messageToUser"));

                appointmentList.add(appointment);
            }
        }
        catch (Exception ex){
            logger.info(ex.getMessage());
        }

        return appointmentList;
    }
}

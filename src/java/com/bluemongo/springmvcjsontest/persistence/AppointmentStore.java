package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Appointment;
import com.bluemongo.springmvcjsontest.model.Customer;
import com.bluemongo.springmvcjsontest.service.AppointmentResult;
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
        String query = "insert into appointment(appointmentTypeId, appointmentDate, customerId, status, messageToUser, locationId, isComplete) values (?,?,?,?,?,?,?)";
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
            preparedStatement.setBoolean(7, appointment.isComplete());
            preparedStatement.executeUpdate();
            logger.info("new appointment inserted.");

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
                    Appointment appointment = getAppointmentFromResultSet(resultSet);
                    appointmentList.add(appointment);
                }
            }
            catch (Exception ex){
                logger.info(ex.getMessage());
            }

            return appointmentList;
        }


    public List<AppointmentResult> get(int businessId, Date strFromDate, Date toDate){
        //TODO chronologically ordered list?
        List<AppointmentResult> appointmentResultList = new ArrayList<>();
        String query = "SELECT appointment.id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, " +
                "customer.businessId, status, isComplete FROM appointment " +
                "inner join customer on appointment.customerId = customer.id" +
                " where customer.businessId = ?;";

        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, businessId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                AppointmentResult appointmentResult = getAppointmentResultFromResultSet(resultSet);
                appointmentResultList.add(appointmentResult);
            }
        }
        catch (Exception ex){
            logger.info(ex.getMessage());
        }

        return appointmentResultList;
    }


    public List<Appointment> getAllForDate(int userId, Date date){
        //chronologically ordered list
        List<Appointment> appointmentList = new ArrayList<>();
        String query = "SELECT id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, status, isComplete FROM appointment" +
                " where userId = ? and appointmentDate between ? and DATE_ADD(?,INTERVAL 1 day) ;";

        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userId);
            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(date.getTime()));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Appointment appointment = getAppointmentFromResultSet(resultSet);
                appointmentList.add(appointment);
            }
        }
        catch (Exception ex){
            logger.info(ex.getMessage());
        }

        return appointmentList;
    }

    public List<Appointment> getAll(int customerId){
        //TODO chronologically ordered list?
        List<Appointment> appointmentList = new ArrayList<>();
        String query = "SELECT id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, status FROM appointment" +
                " where customerId = ?;";

        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Appointment appointment = getAppointmentFromResultSet(resultSet);
                appointmentList.add(appointment);
            }
        }
        catch (Exception ex){
            logger.info(ex.getMessage());
        }

        return appointmentList;
    }

    private Appointment getAppointmentFromResultSet(ResultSet resultSet) throws SQLException {
        Appointment appointment = new Appointment(resultSet.getInt("id"));
        appointment.setAppointmentTypeId(resultSet.getInt("appointmentTypeId"));
        appointment.setAppointmentDate(resultSet.getDate("appointmentDate"));
        appointment.setCustomerId(resultSet.getInt("customerId"));
        appointment.setMessageToCustomer(resultSet.getNString("messageToUser"));
        appointment.setIsComplete(resultSet.getBoolean("isComplete"));
        return appointment;
    }

    private AppointmentResult getAppointmentResultFromResultSet(ResultSet resultSet) throws SQLException {
        AppointmentResult appointmentResult = new AppointmentResult();
        Appointment appointment = new Appointment(resultSet.getInt("id"));
        appointment.setAppointmentTypeId(resultSet.getInt("appointmentTypeId"));
        appointment.setAppointmentDate(resultSet.getDate("appointmentDate"));
        appointment.setCustomerId(resultSet.getInt("customerId"));
        appointment.setMessageToCustomer(resultSet.getNString("messageToUser"));
        appointment.setIsComplete(resultSet.getBoolean("isComplete"));
        appointmentResult.setAppointment(appointment);

        Customer customer = new CustomerStore().get(resultSet.getInt("businessId"), resultSet.getInt("customerId"));
        appointmentResult.setCustomer(customer);

        return appointmentResult;
    }

}

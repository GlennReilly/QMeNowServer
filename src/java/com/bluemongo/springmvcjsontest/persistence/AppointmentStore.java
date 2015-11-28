package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Appointment;
import com.bluemongo.springmvcjsontest.model.Customer;
import com.bluemongo.springmvcjsontest.service.AppointmentResult;
import org.apache.commons.lang3.StringUtils;
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

    public int saveUpdate(Appointment appointment) {
        int lastInsertedId = -1;
        String query = "update appointment set appointmentTypeId = ?, appointmentDate = ?, status = ?, messageToUser = ?, locationId = ?, isComplete = ? where id = ?";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, appointment.getAppointmentTypeId());
            if (appointment.getAppointmentDate() != null) {
                preparedStatement.setTimestamp(2, new Timestamp(appointment.getAppointmentDate().getTime()));
            }
            else {
                if(StringUtils.isNotEmpty(appointment.getStrAppointmentDate())){
                    preparedStatement.setTimestamp(2, null);
                }
                preparedStatement.setTimestamp(2, null);
            }
            preparedStatement.setInt(3, appointment.getStatus());
            preparedStatement.setString(4, appointment.getMessageToCustomer());
            preparedStatement.setInt(5, appointment.getLocationId());
            preparedStatement.setBoolean(6, appointment.isComplete());
            preparedStatement.setInt(7, appointment.getId());
            preparedStatement.executeUpdate();
            logger.info("appointment updated.");

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

    public Appointment get(int appointmentId){
        //TODO needs work or renaming
        //chronologically ordered list
        Appointment appointment = null;
        String query = "SELECT id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, status, locationId, isComplete FROM appointment" +
                " where id = ?;";

        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, appointmentId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                appointment = getAppointmentFromResultSet(resultSet);
            }
        }
        catch (Exception ex){
            logger.info(ex.getMessage());
        }

        return appointment;
    }

        public List<Appointment> getAllForToday(int userId){
            //TODO needs work or renaming
            //chronologically ordered list
            List<Appointment> appointmentList = new ArrayList<>();
            String query = "SELECT id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, status, locationId, isComplete FROM appointment" +
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


    public List<AppointmentResult> get(int businessId, int customerId, Date fromDate, Date toDate){
        //TODO chronologically ordered list?
        List<AppointmentResult> appointmentResultList = new ArrayList<>();

        if(customerId > 0) {
            getResultsForCustomer(businessId, customerId, fromDate, toDate, appointmentResultList);
        } else {
            getResultsForAllCustomers(businessId, fromDate, toDate, appointmentResultList);
        }

        return appointmentResultList;
    }

    private void getResultsForAllCustomers(int businessId, Date fromDate, Date toDate, List<AppointmentResult> appointmentResultList) {
        String query = "SELECT appointment.id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, status, locationId, isComplete, " +
                "customer.businessId FROM appointment " +
                "inner join customer on appointment.customerId = customer.id" +
                " where customer.businessId = ? AND appointmentDate BETWEEN ? AND ? " +
                "ORDER by appointmentDate ASC ;";

        try (Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, businessId);
            preparedStatement.setDate(2, new java.sql.Date(fromDate.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(toDate.getTime()));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AppointmentResult appointmentResult = getAppointmentResultFromResultSet(resultSet);
                appointmentResultList.add(appointmentResult);
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
    }

    private void getResultsForCustomer(int businessId, int customerId, Date fromDate, Date toDate, List<AppointmentResult> appointmentResultList) {
        String query = "SELECT appointment.id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, status, locationId, isComplete, " +
                "customer.businessId FROM appointment " +
                "inner join customer on appointment.customerId = customer.id" +
                " where customer.businessId = ? AND appointmentDate BETWEEN ? AND ? " +
                "AND customer.id = ? " +
                "ORDER by appointmentDate ASC ;";

        try (Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, businessId);
            preparedStatement.setDate(2, new java.sql.Date(fromDate.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(toDate.getTime()));
            preparedStatement.setInt(4, customerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AppointmentResult appointmentResult = getAppointmentResultFromResultSet(resultSet);
                appointmentResultList.add(appointmentResult);
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
    }


    public List<Appointment> getAllForDate(int userId, Date date){
        //chronologically ordered list
        List<Appointment> appointmentList = new ArrayList<>();
        String query = "SELECT id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, status, locationId, isComplete FROM appointment" +
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
        String query = "SELECT id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, status, locationId, isComplete FROM appointment" +
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
        appointment.setCustomerId(resultSet.getInt("customerId"));
        appointment.setAppointmentTypeId(resultSet.getInt("appointmentTypeId"));
        appointment.setAppointmentDate(new Date(resultSet.getTimestamp("appointmentDate").getTime()));
        appointment.setMessageToCustomer(resultSet.getNString("messageToUser"));
        appointment.setStatus(resultSet.getInt("status"));
        appointment.setLocationId(resultSet.getInt("locationId"));
        appointment.setIsComplete(resultSet.getBoolean("isComplete"));
        return appointment;
    }

    private AppointmentResult getAppointmentResultFromResultSet(ResultSet resultSet) throws SQLException {
        AppointmentResult appointmentResult = new AppointmentResult();
        Appointment appointment = getAppointmentFromResultSet(resultSet);
        appointmentResult.setAppointment(appointment);

        Customer customer = new CustomerStore().get(resultSet.getInt("businessId"), resultSet.getInt("customerId"));
        appointmentResult.setCustomer(customer);

        return appointmentResult;
    }


}

package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Appointment;
import com.bluemongo.springmvcjsontest.model.Customer;
import com.bluemongo.springmvcjsontest.service.AppointmentAndCustomer;
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
        String query = "insert into appointment(appointmentTypeId, appointmentDate, customerId, statusId, messageToUser, locationId, isComplete) values (?,?,?,?,?,?,?)";
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
        String query = "update appointment set appointmentTypeId=?, appointmentDate=?, statusId=?, messageToUser=?, locationId=?, isComplete=?, checkInDate=? where id = ?";
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
            if (appointment.getCheckInDate() != null) {
                preparedStatement.setTimestamp(7, new Timestamp(appointment.getCheckInDate().getTime()));
            }else{
                preparedStatement.setTimestamp(7, null);
            }
            preparedStatement.setInt(8, appointment.getId());
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

    public Appointment getAppointment(int appointmentId){
        Appointment appointment = null;
        String query = getQueryStringBuilder().toString()
                + " where app.id = ?;";

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

/*        public List<Appointment> getAllForToday(int userId){
            //TODO needs work or renaming
            //chronologically ordered list
            List<Appointment> appointmentList = new ArrayList<>();
            String query = "SELECT id, createdDate, appointmentTypeId, appointmentDate, customerId, messageToUser, statusId, locationId, isComplete FROM appointment" +
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
        }*/


    public List<AppointmentAndCustomer> getAppointmentsAndCustomer(int businessId, int customerId, Date fromDate, Date toDate, Boolean isComplete){
        //TODO chronologically ordered list?
        List<AppointmentAndCustomer> appointmentResultList = new ArrayList<>();

        if(customerId > 0) {
            appointmentResultList = getAppointmentsAndCustomer(customerId, fromDate, toDate, isComplete);
        } else {
            appointmentResultList = getAppointmentsAndCustomers(businessId, fromDate, toDate, isComplete);
        }

        return appointmentResultList;
    }

    public List<AppointmentAndCustomer> getAppointmentsAndCustomers(int businessId, Date fromDate, Date toDate, Boolean isComplete) {
        List<AppointmentAndCustomer> appointmentResultList = new ArrayList<>();

        String isCompleteSegment = isComplete==null? "" : " AND isComplete=?";

        String dateSegment = "";
        if (fromDate != null && toDate != null) {
            dateSegment = " AND appointmentDate BETWEEN ? AND ? ";
        }else{
            dateSegment = "";
        }

        String query = getQueryStringBuilder().toString()
                + " where customer.businessId = ? "
                + dateSegment
                + isCompleteSegment
                + " ORDER by appointmentDate ASC ;";

        try (Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, businessId);
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(fromDate.getTime()));
            preparedStatement.setTimestamp(3, new java.sql.Timestamp(toDate.getTime()));
            if(isCompleteSegment != "") {
                preparedStatement.setBoolean(4, isComplete);
            }
            resultSet = preparedStatement.executeQuery();
            //System.out.println(preparedStatement.toString());

            while (resultSet.next()) {
                AppointmentAndCustomer appointmentResult = getAppointmentResultFromResultSet(resultSet);
                appointmentResultList.add(appointmentResult);
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        return appointmentResultList;
    }

    public List<AppointmentAndCustomer> getAppointmentsAndCustomer(int customerId, Date fromDate, Date toDate, Boolean isComplete) {
        List<AppointmentAndCustomer> appointmentResultList = new ArrayList<>();

        String isCompleteSegment = isComplete==null? "" : " AND isComplete=?";
        String query = getQueryStringBuilder().toString()
                + " where appointmentDate BETWEEN ? AND ? "
                + "AND customer.id = ? "
                + isCompleteSegment
                + "ORDER by appointmentDate ASC ;";

        try (Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, new java.sql.Date(fromDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(toDate.getTime()));
            preparedStatement.setInt(3, customerId);
            if(isCompleteSegment != "") {
                preparedStatement.setBoolean(4, isComplete);
            }
            resultSet = preparedStatement.executeQuery();
            //System.out.println(preparedStatement.toString());

            while (resultSet.next()) {
                AppointmentAndCustomer appointmentResult = getAppointmentResultFromResultSet(resultSet);
                appointmentResultList.add(appointmentResult);
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        return appointmentResultList;
    }

    public List<Appointment> get(int customerId, Date fromDate, Date toDate, Boolean isComplete) {
        List<Appointment> appointments = new ArrayList<>();
        String isCompleteSegment = isComplete==null? "" : " AND isComplete=?";

        String query = getQueryStringBuilder().toString()
               + " where appointmentDate BETWEEN ? AND ? " +
                " AND customer.id = ? " +
                isCompleteSegment +
                " ORDER by appointmentDate ASC ;";



        try (Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, new java.sql.Date(fromDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(toDate.getTime()));
            preparedStatement.setInt(3, customerId);
            if(isCompleteSegment != "") {
                preparedStatement.setBoolean(4, isComplete);
            }
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = getAppointmentFromResultSet(resultSet);
                appointments.add(appointment);
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        return appointments;
    }


    public List<Appointment> getAllForDate(int userId, Date date){
        //chronologically ordered list
        List<Appointment> appointmentList = new ArrayList<>();
        String query = getQueryStringBuilder().toString()
                + " where userId = ? and appointmentDate between ? and DATE_ADD(?,INTERVAL 1 day) ;";

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
        String query = getQueryStringBuilder().toString()
                + " where customerId = ? ORDER BY appointmentDate DESC;";



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


    public List<AppointmentAndCustomer> getAll(Integer appointmentTypeId, Integer statusId, Integer locationId){
        //List<Appointment> appointmentList = new ArrayList<>();
        List<AppointmentAndCustomer> appointmentResultList = new ArrayList<>();
        StringBuilder sbQuery = getQueryStringBuilder();

        if (appointmentTypeId != null || statusId != null || locationId != null ){
            sbQuery.append(" WHERE ");
        }

        sbQuery.append(appointmentTypeId!=null? " appointmentTypeId=?":"");

        if ( appointmentTypeId!=null && (statusId != null || locationId != null )){
            sbQuery.append(" AND ");
        }

        sbQuery.append(statusId!=null? " statusId=?":"");

        if ( locationId != null  && (statusId != null || appointmentTypeId != null )){
            sbQuery.append(" AND ");
        }

        sbQuery.append(locationId!=null? " locationId=?":"");
        sbQuery.append(" ORDER BY appointmentDate DESC;");

        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(sbQuery.toString());
            int parameterCount = 0;
            if (appointmentTypeId != null) {
                parameterCount++;
                preparedStatement.setInt(parameterCount, appointmentTypeId);
            }
            if (statusId != null) {
                parameterCount++;
                preparedStatement.setInt(parameterCount, statusId);
            }
            if (locationId != null) {
                parameterCount++;
                preparedStatement.setInt(parameterCount, locationId);
            }

            resultSet = preparedStatement.executeQuery();


                while (resultSet.next()) {

                    AppointmentAndCustomer appointmentResult = getAppointmentResultFromResultSet(resultSet);
                    appointmentResultList.add(appointmentResult);

/*                Appointment appointment = getAppointmentFromResultSet(resultSet);
                appointmentList.add(appointment);*/
            }
        }
        catch (Exception ex){
            logger.info(ex.getMessage());
        }

        return appointmentResultList;
    }

    private StringBuilder getQueryStringBuilder() {
        StringBuilder sbQuery = new StringBuilder();
        sbQuery.append(" SELECT app.id, app.createdDate, appointmentTypeId, appointmentDate, customerId, customer.businessId, messageToUser, statusId, locationId, isComplete, ");
        sbQuery.append(" appstatus.backgroundColourHexCode as appStatusHexCode, apptype.backgroundColourHexCode as appTypeHexCode,");
        sbQuery.append(" location.backgroundColourHexCode as locationHexCode, checkInDate");
        sbQuery.append(" FROM appointment app");
        sbQuery.append(" inner join customer on app.customerId = customer.id");
        sbQuery.append(" inner join appointmentStatus appstatus on app.statusId = appstatus.id");
        sbQuery.append(" inner join appointmentType apptype on app.appointmentTypeId = apptype.id");
        sbQuery.append(" inner join location on location.id = app.locationId");
        return sbQuery;
    }


    private Appointment getAppointmentFromResultSet(ResultSet resultSet) throws SQLException {
        Appointment appointment = new Appointment(resultSet.getInt("id"));
        appointment.setCustomerId(resultSet.getInt("customerId"));
        appointment.setAppointmentTypeId(resultSet.getInt("appointmentTypeId"));
        appointment.setAppointmentDate(new Date(resultSet.getTimestamp("appointmentDate").getTime()));
        appointment.setMessageToCustomer(resultSet.getNString("messageToUser"));
        appointment.setStatus(resultSet.getInt("statusId"));
        appointment.setLocationId(resultSet.getInt("locationId"));
        appointment.setIsComplete(resultSet.getBoolean("isComplete"));
        appointment.setStatusHexCode(resultSet.getString("appStatusHexCode"));
        appointment.setAppTypeHexCode(resultSet.getString("appTypeHexCode"));
        appointment.setLocationHexCode(resultSet.getString("locationHexCode"));
        if(resultSet.getTimestamp("checkInDate") != null) {
            appointment.setCheckInDate(new Date(resultSet.getTimestamp("checkInDate").getTime()));
        }
        else{
            appointment.setCheckInDate(null);
        }
        return appointment;
    }

    private AppointmentAndCustomer getAppointmentResultFromResultSet(ResultSet resultSet) throws SQLException {
        AppointmentAndCustomer appointmentResult = new AppointmentAndCustomer();
        Appointment appointment = getAppointmentFromResultSet(resultSet);
        appointmentResult.setAppointment(appointment);

        Customer customer = new CustomerStore().get(resultSet.getInt("businessId"), resultSet.getInt("customerId"));
        appointmentResult.setCustomer(customer);

        return appointmentResult;
    }



}

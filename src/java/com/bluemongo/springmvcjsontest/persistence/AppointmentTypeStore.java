package com.bluemongo.springmvcjsontest.persistence;


import com.bluemongo.springmvcjsontest.model.AppointmentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 21/10/15.
 */
public class AppointmentTypeStore {
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(AppointmentTypeStore.class);

    public int saveNew(AppointmentType appointmentType) {
        int lastInsertedId = -1;
        //String query = "insert into appointmentType(businessId, name, backgroundColourHexCode, styleJson) values (?,?,?,?)";

        String query = " insert into appointmentType(businessId, name, backgroundColourHexCode, styleJson)" +
                " select ?,?,?,? from DUAL" +
                " WHERE NOT exists (select id from appointmentType where name = ?);";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, appointmentType.getBusinessId());
            preparedStatement.setString(2, appointmentType.getName());
            preparedStatement.setString(3, appointmentType.getBackgroundColourHexCode());
            preparedStatement.setString(4, appointmentType.getStyleJson());
            preparedStatement.setString(5, appointmentType.getName());
            preparedStatement.executeUpdate();
            logger.info("new AppointmentType inserted.");

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

    public List<AppointmentType> getAll(boolean isActive, int businessId){
        List<AppointmentType> appointmentTypeList = new ArrayList<>();
        String query = "select id, name, backgroundColourHexCode, styleJson, businessId from appointmentType where isActive = ? AND businessId = ?";

        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, isActive);
            preparedStatement.setInt(2, businessId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                AppointmentType appointmentType = getAppointmentType(businessId, resultSet);
                appointmentTypeList.add(appointmentType);
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentTypeList;
    }

    private AppointmentType getAppointmentType(int businessId, ResultSet resultSet) throws SQLException {
        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setBusinessId(businessId);
        appointmentType.setId(resultSet.getInt("id"));
        appointmentType.setName(resultSet.getString("name"));
        appointmentType.setBackgroundColourHexCode(resultSet.getString("backgroundColourHexCode"));
        appointmentType.setStyleJson(resultSet.getString("styleJson"));
        return appointmentType;
    }

    public AppointmentType get(int businessId, int appointmentTypeId) {
        AppointmentType appointmentType = null;
        String query = "select id, name, backgroundColourHexCode, styleJson, businessId from appointmentType where isActive = ? AND businessId = ? AND id=?";

        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, businessId);
            preparedStatement.setInt(3, appointmentTypeId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                appointmentType = getAppointmentType(businessId, resultSet);
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointmentType;
    }
}

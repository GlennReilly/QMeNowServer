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
    private static final String COLUMNS = " id, name, backgroundColourHexCode, styleJson, businessId, prefix, isDefault ";

    public int saveNew(AppointmentType appointmentType) {
        int lastInsertedId = -1;

        String query = " insert into appointmentType(businessId, name, backgroundColourHexCode, styleJson, prefix, isDefault)" +
                " select ?,?,?,?,?,? from DUAL" +
                " WHERE NOT exists (select id from appointmentType where name = ?);";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, appointmentType.getBusinessId());
            preparedStatement.setString(2, appointmentType.getName());
            preparedStatement.setString(3, appointmentType.getBackgroundColourHexCode());
            preparedStatement.setString(4, appointmentType.getStyleJson());
            preparedStatement.setString(5, appointmentType.getPrefix());
            preparedStatement.setBoolean(6, appointmentType.getIsDefault());
            preparedStatement.setString(7, appointmentType.getName());
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

    public List<AppointmentType> getAll(int businessId, boolean isActive){
        List<AppointmentType> appointmentTypeList = new ArrayList<>();
        String query = "select" + COLUMNS + " from appointmentType where isActive = ? AND businessId = ?";

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
        appointmentType.setPrefix(resultSet.getString("prefix"));
        appointmentType.setIsDefault(resultSet.getBoolean("isDefault"));
        return appointmentType;
    }

    public AppointmentType get(int businessId, int appointmentTypeId) {
        AppointmentType appointmentType = null;
        String query = "select" + COLUMNS + " from appointmentType where isActive = ? AND businessId = ? AND id=?";

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

    public void saveUpdate(AppointmentType appointmentType) {
        String query = "update appointmentType set name=?, backgroundColourHexCode=?, prefix=? where id=?";
        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, appointmentType.getName());
            preparedStatement.setString(2, appointmentType.getBackgroundColourHexCode());
            preparedStatement.setString(3, appointmentType.getPrefix());
            preparedStatement.setInt(4, appointmentType.getId());
            preparedStatement.execute();

            if (appointmentType.getIsDefault()) {
                setDefault(appointmentType.getId());
            }

        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setInactive(int appointmentTypeId) {
        String query = " update appointmentType set isActive=0 where id=?;";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,appointmentTypeId);
            preparedStatement.executeUpdate();
            logger.info("Appointment Status deactivated.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }


    public void setDefault(int appointmentTypeId){
        String queryNukeExisting = "update appointmentType set isDefault=?;";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(queryNukeExisting);
            preparedStatement.setBoolean(1,false);
            preparedStatement.executeUpdate();
            logger.info("Appointment Type getIsDefault deactivated.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        String queryUpdate = "update appointmentType set isDefault=1 where id=?";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setInt(1,appointmentTypeId);
            preparedStatement.executeUpdate();
            logger.info("Appointment Type getIsDefault set.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }

    public int getDefault()
    {
        int output = 0;
        String query = "select id from appointmentType where isDefault=1;";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                output = resultSet.getInt("id");
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return output;
    }
}

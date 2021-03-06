package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.model.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 13/10/15.
 */
public class LocationStore {
    private static final String COLUMNS = " id, createdDate, locationName, businessId, backgroundColourHexCode, isDefault ";
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(BusinessStore.class);

    public void saveNew(Location location){
        String query = " insert into location(locationName, businessId, backgroundColourHexCode, isDefault)" +
                " select ?,?,?,? from DUAL" +
                " WHERE NOT exists (select id from location where locationName = ?);";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,location.getName());
            preparedStatement.setInt(2, location.getBusinessId());
            preparedStatement.setString(3,location.getBackgroundColourHexCode());
            preparedStatement.setBoolean(4, location.getIsDefault());
            preparedStatement.setString(5,location.getName());
            preparedStatement.executeUpdate();
            logger.info("new Location inserted.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }

    public List<Location> getAll(int businessId, boolean isActive) {
        List<Location> locationList = new ArrayList<>();
        String query = " SELECT " + COLUMNS + " FROM location where businessId = ? AND isActive = ?; ";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, businessId);
            preparedStatement.setBoolean(2, isActive);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Location location = getLocationFromResultSet(resultSet);
                locationList.add(location);
            }

        }catch (Exception ex){
            logger.info(ex.getMessage());
        }


        return locationList;
    }


    public Location get(int locationId) {
        Location location = null;
        String query = " SELECT " + COLUMNS + " FROM location where id=? AND isActive = ?; ";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, locationId);
            preparedStatement.setBoolean(2, true);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                location = getLocationFromResultSet(resultSet);
            }

        }catch (Exception ex){
            logger.info(ex.getMessage());
        }
        return location;
    }

    private Location getLocationFromResultSet(ResultSet resultSet) throws SQLException {
        Business business = new BusinessStore().get(resultSet.getInt("businessId"));
        Location location = new Location();
        location.setId(resultSet.getInt("id"));
        location.setBusinessId(business!=null? business.getId(): null);
        location.setName(resultSet.getNString("locationName"));
        location.setBackgroundColourHexCode(resultSet.getString("backgroundColourHexCode"));
        location.setIsDefault(resultSet.getBoolean("isDefault"));
        return location;
    }

    public void saveUpdate(Location location) {

        String query = " update location set locationName=?, businessId=?, backgroundColourHexCode=?, isDefault=? where id=?;";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,location.getName());
            preparedStatement.setInt(2, location.getBusinessId());
            preparedStatement.setString(3,location.getBackgroundColourHexCode());
            preparedStatement.setInt(4,location.getId());
            preparedStatement.executeUpdate();

            if (location.getIsDefault()) {
                setDefault(location.getId());
            }

            logger.info("Location updated.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }

    public void setInactive(int locationId) {
        String query = " update location set isActive=0 where id=?;";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,locationId);
            preparedStatement.executeUpdate();
            logger.info("Location deactivated.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }

    public void setDefault(int locationId){
        String queryNukeExisting = "update location set isDefault=?;";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(queryNukeExisting);
            preparedStatement.setBoolean(1,false);
            preparedStatement.executeUpdate();
            logger.info("Appointment location getIsDefault deactivated.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        String queryUpdate = "update location set isDefault=1 where id=?";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setInt(1,locationId);
            preparedStatement.executeUpdate();
            logger.info("Appointment location getIsDefault set.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }

    public int getDefault()
    {
        int output = 0;
        String query = "select id from location where isDefault=1;";

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

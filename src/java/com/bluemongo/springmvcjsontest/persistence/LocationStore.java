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
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(BusinessStore.class);

    public void saveNew(Location location){
        String query = " insert into location(locationName, businessId, backgroundColourHexCode)" +
                " select ?,?,? from DUAL" +
                " WHERE NOT exists (select id from location where locationName = ?);";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,location.getName());
            preparedStatement.setInt(2, location.getBusinessId());
            preparedStatement.setString(3,location.getBackgroundColourHexCode());
            preparedStatement.setString(4,location.getName());
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
        String query = " SELECT id, createdDate, locationName, businessId, backgroundColourHexCode FROM location where businessId = ? AND isActive = ?; ";

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
        String query = " SELECT id, createdDate, locationName, businessId, backgroundColourHexCode FROM location where id=? AND isActive = ?; ";

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
        return location;
    }

    public void saveUpdate(Location location) {

        String query = " update location set locationName=?, businessId=?, backgroundColourHexCode=? where id=?;";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,location.getName());
            preparedStatement.setInt(2, location.getBusinessId());
            preparedStatement.setString(3,location.getBackgroundColourHexCode());
            preparedStatement.setInt(4,location.getId());
            preparedStatement.executeUpdate();
            logger.info("Location updated.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }
}

package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by glenn on 13/10/15.
 */
public class LocationStore {
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(BusinessStore.class);

    public void saveNew(Location location){
        String query = "insert into location(locationName, customerId) values (?,?)";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,location.getLocationName());
            preparedStatement.setInt(2, location.getCustomerId());

            preparedStatement.executeUpdate();
            logger.info("new Customer inserted.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

    }
}

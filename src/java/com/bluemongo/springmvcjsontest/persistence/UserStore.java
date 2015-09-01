package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by glenn on 1/09/15.
 */
public class UserStore {
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(UserStore.class);

    public void saveNew(User user){
        String query = "insert into appUser(username, password, customerId, firstName, lastName, physicalAddress, emailAddress) values (?,?,?,?,?,?,?)";
        try{
            preparedStatement = dbHelper.getConnection().prepareStatement(query);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setInt(3, user.getCustomerId());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5,user.getLastName());
            preparedStatement.setString(6, user.getPhysicalAddress());
            preparedStatement.setString(7, user.getEmailAddress());
            preparedStatement.executeUpdate();
            logger.info("new Customer inserted.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }
}

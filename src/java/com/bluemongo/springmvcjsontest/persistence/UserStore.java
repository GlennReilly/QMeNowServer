package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.model.UserCredentials;
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

    public boolean validateCredentials(UserCredentials userCredentials) {
        boolean foundUserWithTheseCredentials = false;
        String query = "select count(id) from appUser where username = ? AND password = ? AND active = true";
        try {
            preparedStatement = dbHelper.getConnection().prepareStatement(query);
            preparedStatement.setString(1, userCredentials.getUsername());
            preparedStatement.setString(2, userCredentials.getPassword());
            resultSet = preparedStatement.executeQuery();
            int rowCount = 0;
            while(resultSet.next()){
                rowCount = resultSet.getInt(1);
            }
            foundUserWithTheseCredentials = rowCount>0? true: false;
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return foundUserWithTheseCredentials;
    }

    public User get(UserCredentials userCredentials){
        User user = null;
        String query = "SELECT id, username, customerId, firstName, lastName, physicalAddress, " +
                "emailAddress, active FROM appUser where username = ? AND password = ? AND active = true";
        try {
            preparedStatement = dbHelper.getConnection().prepareStatement(query);
            preparedStatement.setString(1, userCredentials.getUsername());
            preparedStatement.setString(2, userCredentials.getPassword());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getNString("username"));
                user.setCustomerId(resultSet.getInt("customerId"));
                user.setFirstName(resultSet.getNString("firstName"));
                user.setLastName(resultSet.getNString("lastName"));
                user.setPhysicalAddress(resultSet.getNString("physicalAddress"));
                user.setEmailAddress(resultSet.getNString("emailAddress"));
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        return user;
    }

}
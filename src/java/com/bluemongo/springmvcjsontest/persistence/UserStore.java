package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.User;
import com.bluemongo.springmvcjsontest.model.UserCredentials;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 1/09/15.
 */
public class UserStore {
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(UserStore.class);

    public int saveNew(User user){
        int lastInsertedId = -1;
        String query = "insert into appUser(username, password, customerId, firstName, lastName, physicalAddress, emailAddress) values (?,?,?,?,?,?,?)";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setInt(3, user.getBusinessId());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5,user.getLastName());
            preparedStatement.setString(6, user.getPhysicalAddress());
            preparedStatement.setString(7, user.getEmailAddress());
            preparedStatement.executeUpdate();
            logger.info("new Customer inserted.");

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

    public boolean validateCredentials(UserCredentials userCredentials) {
        boolean foundUserWithTheseCredentials = false;
        String query = "select count(id) from appUser where username = ? AND password = ? AND active = true";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
        String query = "SELECT appUser.id, username, customerId, firstName, lastName, physicalAddress," +
                " emailAddress, active, appUserType.name as appUserTypeName FROM appUser inner join appUserType on appUser.userType = appUserType.id" +
                " where username = ? AND password = ? AND active = true";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userCredentials.getUsername());
            preparedStatement.setString(2, userCredentials.getPassword());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user = getUser(resultSet);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        return user;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user;
        user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getNString("username"));
        user.setBusinessId(resultSet.getInt("customerId"));
        user.setFirstName(resultSet.getNString("firstName"));
        user.setLastName(resultSet.getNString("lastName"));
        user.setPhysicalAddress(resultSet.getNString("physicalAddress"));
        user.setEmailAddress(resultSet.getNString("emailAddress"));
        user.setUserType(resultSet.getString("appUserTypeName"));
        return user;
    }

    public List<User> getAll(boolean isActive) {
        List<User> userList = new ArrayList<>();
        String query = "SELECT id, username, customerId, firstName, lastName, physicalAddress, " +
                "emailAddress, active FROM appUser where active = ?";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, isActive);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = getUser(resultSet);
                userList.add(user);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        return userList;
    }
}

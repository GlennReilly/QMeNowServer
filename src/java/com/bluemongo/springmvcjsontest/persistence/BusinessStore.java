package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by glenn on 1/09/15.
 */
public class BusinessStore {
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(BusinessStore.class);

    public int saveNew(Business business){
        int lastInsertedId = -1;
        String query = "insert into business(businessName, phoneNumber, emailAddress, physicalAddress) values (?,?,?,?)";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, business.getBusinessName());
            preparedStatement.setString(2, business.getPhoneNumber());
            preparedStatement.setString(3, business.getEmailAddress());
            preparedStatement.setString(4, business.getPhysicalAddress());
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

    public List<Business> getAll(boolean isActive){
        List<Business> businessList = new ArrayList<>();
        String query = "select id, businessName, phoneNumber, emailAddress, physicalAddress from business where active = ?";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, isActive);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Business business = new Business();
                business.setId(resultSet.getInt("id"));
                business.setBusinessName(resultSet.getNString("businessName"));
                business.setPhoneNumber(resultSet.getString("phoneNumber"));
                business.setEmailAddress(resultSet.getString("emailAddress"));
                business.setPhysicalAddress(resultSet.getNString("physicalAddress"));
                businessList.add(business);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return businessList;
    }

    public Business get(int businessId) {
        Business business = null;
        String query = "select id, businessName, phoneNumber, emailAddress, physicalAddress from business where id = ? AND active = ?";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, businessId);
            preparedStatement.setBoolean(2, true);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                business = new Business();
                business.setId(resultSet.getInt("id"));
                business.setBusinessName(resultSet.getNString("businessName"));
                business.setPhoneNumber(resultSet.getString("phoneNumber"));
                business.setEmailAddress(resultSet.getString("emailAddress"));
                business.setPhysicalAddress(resultSet.getNString("physicalAddress"));
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return business;

    }

    public List<ReconfigurableAppConfig> getConfigs(int customerId){
        List<ReconfigurableAppConfig> reconfigurableAppConfigList = new ArrayList<>();
        String query = "SELECT id, title, config, createdDate, updatedDate, customerId, revisionNumber " +
        "FROM ConfigStore where customerId is null or  customerId = ?";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                ReconfigurableAppConfig config = new ReconfigurableAppConfig();
                config.setPageTitle(resultSet.getNString("title"));
                //TODO complete this.
                reconfigurableAppConfigList.add(config);
            }

        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return reconfigurableAppConfigList;
    }


}

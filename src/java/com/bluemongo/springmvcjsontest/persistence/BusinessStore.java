package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void saveNew(Business business){
        String query = "insert into customer(businessName, phoneNumber, emailAddress, physicalAddress) values (?,?,?,?)";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, business.getBusinessName());
            preparedStatement.setString(2, business.getPhoneNumber());
            preparedStatement.setString(3, business.getEmailAddress());
            preparedStatement.setString(4, business.getPhysicalAddress());
            preparedStatement.executeUpdate();
            logger.info("new Customer inserted.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }

    public List<Business> getAll(boolean isActive){
        List<Business> businessList = new ArrayList<>();
        String query = "select id, businessName, phoneNumber, emailAddress, physicalAddress from customer where active = ?";
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

/*    public Customer getAllForCustomer(int customerId){

    }*/
}

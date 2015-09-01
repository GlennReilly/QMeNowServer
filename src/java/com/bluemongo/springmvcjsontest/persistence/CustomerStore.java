package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * Created by glenn on 1/09/15.
 */
public class CustomerStore {
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(CustomerStore.class);

    public void saveNew(Customer customer){
        String query = "insert into customer(businessName, phoneNumber, emailAddress, physicalAddress) values (?,?,?,?)";
        try{
            preparedStatement = dbHelper.getConnection().prepareStatement(query);
            preparedStatement.setString(1,customer.getBusinessName());
            preparedStatement.setString(2,customer.getPhoneNumber());
            preparedStatement.setString(3,customer.getEmailAddress());
            preparedStatement.setString(4, customer.getPhysicalAddress());
            preparedStatement.executeUpdate();
            logger.info("new Customer inserted.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }

/*    public Customer get(int customerId){

    }*/
}

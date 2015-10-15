package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 1/09/15.
 */
public class CustomerStore {
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(CustomerStore.class);

    public int saveNew(Customer customer){
        int lastInsertedId = -1;
        String query = "insert into customer(firstName, lastName, physicalAddress, emailAddress, phoneNumber, DOB) values (?,?,?,?,?,?)";
        try{
            preparedStatement = dbHelper.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPhysicalAddress());
            preparedStatement.setString(4, customer.getEmailAddress());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setDate(6, (Date) customer.getDOB());
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
            return Integer.parseInt(null);
        }
        return lastInsertedId;
    }


    public Customer get(int customerId){
        Customer customer = null;
        String query = "SELECT id, firstName, lastName, physicalAddress, " +
                "emailAddress, active FROM customer where id = ? AND active = true";
        try {
            preparedStatement = dbHelper.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                customer = new Customer(resultSet.getInt("id"));
                //customer.setId(resultSet.getInt("id"));
                customer.setCustomerId(resultSet.getInt("customerId"));
                customer.setFirstName(resultSet.getNString("firstName"));
                customer.setLastName(resultSet.getNString("lastName"));
                customer.setPhysicalAddress(resultSet.getNString("physicalAddress"));
                customer.setEmailAddress(resultSet.getNString("emailAddress"));
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        return customer;
    }

    public List<Customer> getAll(boolean isActive) {
        List<Customer> customerList = new ArrayList<>();
        String query = "SELECT id, username, customerId, firstName, lastName, physicalAddress, " +
                "emailAddress, active FROM customer where active = ?";
        try {
            preparedStatement = dbHelper.getConnection().prepareStatement(query);
            preparedStatement.setBoolean(1, isActive);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Customer customer = new Customer(resultSet.getInt("id"));
                //customer.setId(resultSet.getInt("id"));
                customer.setCustomerId(resultSet.getInt("customerId"));
                customer.setFirstName(resultSet.getNString("firstName"));
                customer.setLastName(resultSet.getNString("lastName"));
                customer.setPhysicalAddress(resultSet.getNString("physicalAddress"));
                customer.setEmailAddress(resultSet.getNString("emailAddress"));
                customerList.add(customer);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        return customerList;
    }
}

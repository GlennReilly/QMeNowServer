package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
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
        String query = "insert into customer(firstName, lastName, physicalAddress, emailAddress, phoneNumber, DOB, businessId) values (?,?,?,?,?,?,?)";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPhysicalAddress());
            preparedStatement.setString(4, customer.getEmailAddress());
            preparedStatement.setString(5, customer.getPhoneNumber());
            if (customer.getDOB() != null) {
                preparedStatement.setDate(6, new java.sql.Date(customer.getDOB().getTime()));
            }else{
                preparedStatement.setDate(6, null);
            }
            preparedStatement.setInt(7, customer.getBusinessId());
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


    public Customer get(int businessId, int customerId){
        Customer customer = null;
        String query = "SELECT id, firstName, lastName, phoneNumber, physicalAddress, " +
                "emailAddress, businessId, DOB, active FROM customer where id = ? AND active = true AND businessId = ?";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, businessId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                customer = getCustomerFromResultSet();
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        return customer;
    }

    public Customer get(int customerId){
        Customer customer = null;
        String query = "SELECT id, firstName, lastName, phoneNumber, physicalAddress, " +
                "emailAddress, businessId, DOB, active FROM customer where id = ? AND active = true;";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                customer = getCustomerFromResultSet();
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        return customer;
    }

    public List<Customer> getAll(int businessId, boolean isActive) {
        List<Customer> customerList = new ArrayList<>();
        String query = "SELECT id, firstName, lastName, phoneNumber, physicalAddress, " +
                "emailAddress, businessId, DOB, active FROM customer where businessId = ? AND active = ?";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, businessId);
            preparedStatement.setBoolean(2, isActive);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Customer customer = getCustomerFromResultSet();
                customerList.add(customer);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        return customerList;
    }

    private Customer getCustomerFromResultSet() throws SQLException {
        Customer customer = new Customer(resultSet.getInt("id"));
        customer.setFirstName(resultSet.getNString("firstName"));
        customer.setLastName(resultSet.getNString("lastName"));
        customer.setPhoneNumber(resultSet.getString("phoneNumber"));
        customer.setPhysicalAddress(resultSet.getNString("physicalAddress"));
        customer.setEmailAddress(resultSet.getNString("emailAddress"));
        customer.setBusinessId(resultSet.getInt("businessId"));
        customer.setDOB(resultSet.getDate("DOB"));
        return customer;
    }

    public List<Customer> get(int businessId, String customerIdStr, String firstName, String lastName) {
        List<Customer> customerList = new ArrayList<>();

        customerIdStr = customerIdStr == null? "%%" : "%" + customerIdStr + "%";
        firstName = firstName == null? "%%" : "%" + firstName + "%";
        lastName = lastName == null? "%%" : "%" + lastName + "%";

/*        if (customerIdStr.equals(null)|| customerIdStr.equals("")){
            customerIdStr = "%%";
        }*/

/*        if (firstName.equals(null)|| firstName.equals("")){
            firstName = "%%";
        }

        if (lastName.equals(null)|| lastName.equals("")){
            lastName = "%%";
        }*/

        String query = "SELECT id, firstName, lastName, phoneNumber, physicalAddress, " +
                "emailAddress, businessId, DOB, active FROM customer where id like ? AND firstName like ? And lastName like ? AND businessId = ?";

        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customerIdStr);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, businessId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Customer customer = getCustomerFromResultSet();
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

package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Business;
import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by glenn on 1/09/15.
 */
public class BusinessStore {
    private static final String SELECT_BUSINESS_FIELDS = "select id, businessName, phoneNumber, emailAddress, physicalAddress, " +
            "contactName, logoName, logoFilePath, defaultLocationId, buttonColourHexCode," +
            " headerColourHexCode, backgroundColourHexCode, footerColourHexCode, serverBaseURL, applicationAPIURL from business";
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(BusinessStore.class);

    public int saveNew(Business business){
        int lastInsertedId = -1;
        String query = "insert into business(businessName, phoneNumber, emailAddress, physicalAddress, contactName, defaultLocationId, " +
                "buttonColourHexCode, headerColourHexCode, backgroundColourHexCode, footerColourHexCode, serverBaseURL, applicationAPIURL) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, business.getBusinessName());
            preparedStatement.setString(2, business.getPhoneNumber());
            preparedStatement.setString(3, business.getEmailAddress());
            preparedStatement.setString(4, business.getPhysicalAddress());
            preparedStatement.setString(5, business.getContactName());
            preparedStatement.setInt(6,business.getDefaultLocationId());
            preparedStatement.setString(7, business.getButtonColourHexCode());
            preparedStatement.setString(8, business.getHeaderColourHexCode());
            preparedStatement.setString(9, business.getBackgroundColourHexCode());
            preparedStatement.setString(10, business.getFooterColourHexCode());
            preparedStatement.setString(11, business.getServerBaseURL());
            preparedStatement.setString(12, business.getApplicationAPIURL());
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

    public int saveUpdate(Business business) {
        int lastInsertedId = -1;
        String query = "update business set businessName=?, phoneNumber=?, emailAddress=?, physicalAddress=?, " +
                "contactName=?, defaultLocationId=?, buttonColourHexCode=?, headerColourHexCode=?," +
                " backgroundColourHexCode=?, footerColourHexCode=?, serverBaseURL=?, applicationAPIURL=?  where id = ?";
        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, business.getBusinessName());
            preparedStatement.setString(2, business.getPhoneNumber());
            preparedStatement.setString(3, business.getEmailAddress());
            preparedStatement.setString(4, business.getPhysicalAddress());
            preparedStatement.setString(5, business.getContactName());
            preparedStatement.setInt(6, business.getDefaultLocationId());
            preparedStatement.setString(7, business.getButtonColourHexCode());
            preparedStatement.setString(8, business.getHeaderColourHexCode());
            preparedStatement.setString(9, business.getBackgroundColourHexCode());
            preparedStatement.setString(10, business.getFooterColourHexCode());
            preparedStatement.setString(11, business.getServerBaseURL());
            preparedStatement.setString(12, business.getApplicationAPIURL());
            preparedStatement.setInt(13, business.getId());
            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastInsertedId;
    }

    public List<Business> getAll(boolean isActive){
        List<Business> businessList = new ArrayList<>();
        String query = SELECT_BUSINESS_FIELDS + " where active = ?";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1, isActive);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Business business = getBusiness(resultSet);
                businessList.add(business);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return businessList;
    }

    private Business getBusiness(ResultSet resultSet) throws SQLException {
        Business business = new Business();
        business.setId(resultSet.getInt("id"));
        business.setBusinessName(resultSet.getNString("businessName"));
        business.setPhoneNumber(resultSet.getString("phoneNumber"));
        business.setEmailAddress(resultSet.getString("emailAddress"));
        business.setPhysicalAddress(resultSet.getNString("physicalAddress"));
        business.setContactName(resultSet.getString("contactName"));
        business.setLogoFileName(resultSet.getString("logoName"), resultSet.getString("logoFilePath"));
        business.setDefaultLocationId(resultSet.getInt("defaultLocationId"));
        business.setButtonColourHexCode(resultSet.getString("buttonColourHexCode"));
        business.setHeaderColourHexCode(resultSet.getString("headerColourHexCode"));
        business.setBackgroundColourHexCode(resultSet.getString("backgroundColourHexCode"));
        business.setFooterColourHexCode(resultSet.getString("footerColourHexCode"));
        business.setServerBaseURL(resultSet.getString("serverBaseURL"));
        business.setApplicationAPIURL(resultSet.getString("applicationAPIURL"));
        return business;
    }

    public Business get(int businessId) {
        Business business = null;
        String query = SELECT_BUSINESS_FIELDS + " where id = ? AND active = ?";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, businessId);
            preparedStatement.setBoolean(2, true);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                business = getBusiness(resultSet);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return business;

    }




    ///////////////////////////////////////////////////////////////////////////////////////////////////


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

    public void setLogoFileName(int businessId, String logoName, String logoFilePath) {
        String query = "update business set logoName=?, logoFilePath=? where id=?";
        try(Connection connection = dbHelper.getConnection()){
          preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, logoName);
            preparedStatement.setString(2, logoFilePath);
            preparedStatement.setInt(3, businessId);
            preparedStatement.execute();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

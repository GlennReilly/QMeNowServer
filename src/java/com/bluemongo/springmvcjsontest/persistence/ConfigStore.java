package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.bluemongo.springmvcjsontest.service.ConfigHelper;
import com.google.gson.JsonSyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

/**
 * Created by glenn on 4/09/15.
 */
public class ConfigStore {
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Gson gson = new Gson();
    private static final Logger logger = LogManager.getLogger(ConfigStore.class);


    public int saveNew(ConfigHelper configHelper){
        int lastInsertedId = -1;
        String query = "insert into ConfigStore(title, config, customerId) values(?,?,?)";
        String configJson = gson.toJson(configHelper.getCurrentAppConfig());
        try(Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, configHelper.getConfigName());
            preparedStatement.setString(2, configJson);
            preparedStatement.setInt(3, configHelper.getCustomerId());
            preparedStatement.executeUpdate();
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


    public int saveUpdate(ConfigHelper configHelper) {
        int lastUpdatedId = -1;
        String query = "update ConfigStore SET title = ?, config = ?, customerId = ?, revisionNumber = ? where id = ?";
        String configJson = gson.toJson(configHelper.getCurrentAppConfig());
        try(Connection connection = dbHelper.getConnection()){
        preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, configHelper.getConfigName());
            preparedStatement.setString(2, configJson);
            preparedStatement.setInt(3, configHelper.getCustomerId());
            preparedStatement.setInt(4, configHelper.getCurrentAppConfig().incrementAndGetRevisionNumber());
            preparedStatement.setInt(5, configHelper.getId());
            preparedStatement.executeUpdate();
            lastUpdatedId = configHelper.getId();
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return lastUpdatedId;
    }

    public List<ConfigHelper> getAll(int customerId){
        List<ConfigHelper> configList = new ArrayList<>();
        String query = "select id, title, config, createdDate, customerId from ConfigStore where customerId=? or customerId is null";
        try (Connection connection = dbHelper.getConnection()){
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customerId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                final ConfigHelper configHelper = getConfigHelper(resultSet);
                if (configHelper != null) {
                    configList.add(configHelper);
                }
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        return configList;
    }


    public ConfigHelper get(int configID) {
        ConfigHelper configHelper = null;
        ReconfigurableAppConfig appConfig;
        String query = "select id, title, config, createdDate, customerId from ConfigStore where id = ?";
        try(Connection connection = dbHelper.getConnection()) {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, configID);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    configHelper = getConfigHelper(resultSet);
                }

        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }


        return configHelper;
    }

    private ConfigHelper getConfigHelper(ResultSet resultSet) throws SQLException {
        ReconfigurableAppConfig appConfig = null;
        ConfigHelper configHelper = null;
        Gson gson = new Gson();
        final String jsonConfig = resultSet.getString("config");
        try {
            appConfig = gson.fromJson(jsonConfig, ReconfigurableAppConfig.class);
        } catch (JsonSyntaxException e) {
            //e.printStackTrace();
        }

        if (appConfig != null) {
            configHelper = new ConfigHelper(appConfig);
            configHelper.setId(resultSet.getInt("id"));
            configHelper.setConfigName(resultSet.getString("title"));
            configHelper.setCustomerId(resultSet.getInt("customerId"));
        }
        return configHelper;
    }

}

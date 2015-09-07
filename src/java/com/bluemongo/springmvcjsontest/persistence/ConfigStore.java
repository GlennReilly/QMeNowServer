package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bluemongo.springmvcjsontest.service.ConfigHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            preparedStatement.setInt(5, configHelper.getCurrentAppConfig().getId());
            preparedStatement.executeUpdate();
            lastUpdatedId = configHelper.getCurrentAppConfig().getId();
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return lastUpdatedId;
    }

    public List<ReconfigurableAppConfig> getAll(int customerId){
        List<ReconfigurableAppConfig> configList = new ArrayList<>();

        return configList;
    }

    public ReconfigurableAppConfig get(int configID) {
        ReconfigurableAppConfig appConfig = null;
        String query = "select id, title, config, createdDate, customerId, revisionNumber from ConfigStore where id = ?";
        try(Connection connection = dbHelper.getConnection()) {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, configID);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Gson gson = new Gson();
                    final String jsonConfig = resultSet.getString("config");
                    appConfig = gson.fromJson(jsonConfig, ReconfigurableAppConfig.class);
                    //appConfig = new ReconfigurableAppConfig(configID);

                    //appConfig.setTitle(resultSet.getNString("title"));
                    //appConfig.setCustomerId(resultSet.getInt("customerId"));
                    appConfig.setRevisionNumber(resultSet.getInt("revisionNumber"));
                    ConfigHelper configHelper = new ConfigHelper(appConfig);
                }

        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }


        return appConfig;
    }

}

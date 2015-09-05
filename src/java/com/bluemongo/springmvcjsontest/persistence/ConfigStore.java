package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.ReconfigurableAppConfig;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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


    public void saveNew(ReconfigurableAppConfig config){
        String query = "insert into ConfigStore(title, config, customerId) values(?,?,?)";
        String configJson = gson.toJson(config);
        try {
            preparedStatement = dbHelper.getConnection().prepareStatement(query);
            preparedStatement.setString(1, config.getTitle());
            preparedStatement.setString(2, configJson);
            preparedStatement.setInt(3, config.getCustomerId());
            preparedStatement.executeUpdate();
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }

    public List<ReconfigurableAppConfig> getAll(int customerId){
        List<ReconfigurableAppConfig> configList = new ArrayList<>();

        return configList;
    }

    public ReconfigurableAppConfig get(int configID) {
        ReconfigurableAppConfig appConfig = null;
        String query = "select id, title, config, createdDate, customerId, revisionNumber from ConfigStore where id = ?";
        try {
            preparedStatement = dbHelper.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, configID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                appConfig = new ReconfigurableAppConfig();
                appConfig.setId(resultSet.getInt("id"));
                appConfig.setTitle(resultSet.getNString("title"));
                appConfig.setCustomerId(resultSet.getInt("customerId"));
                appConfig.setRevisionNumber(resultSet.getInt("revisionNumber"));
                appConfig.setCreatedDate(resultSet.getDate("createdDate"));
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }

        return appConfig;
    }
}

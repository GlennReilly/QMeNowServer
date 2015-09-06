package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.ButtonStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 17/08/15.
 */
public class ButtonStyleStore {
    private DBHelper dbHelper = new DBHelper();
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(ButtonStyleStore.class);

    public void saveNew(com.bluemongo.springmvcjsontest.model.ButtonStyle buttonStyle){
        //insert into buttonStyle(styleName, textColour, backgroundColourHex, padding) values('buttonStyle1','#000000','#33CC33', '14dp 10dp 2dp 10dp');
        String query = "insert into buttonStyle(styleName, textColour, backgroundColourHex, padding) values(?,?,?,?)";
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, buttonStyle.getName());
            preparedStatement.setString(2, buttonStyle.getTextColour());
            preparedStatement.setString(3, buttonStyle.getBackgroundColorHex());
            preparedStatement.setString(4, buttonStyle.getPadding());
            preparedStatement.executeUpdate();
            logger.info("ButtonStyle saved.");
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
    }

    public List<com.bluemongo.springmvcjsontest.model.ButtonStyle> getAllForCustomer(long customerId) {
        String query = "select id, createdDate, styleName, textColour, backgroundColourHex, padding," +
                " classification1, classification2, classification3, customerId" +
                " from buttonStyle where customerId=? or customerId is null";
        List<com.bluemongo.springmvcjsontest.model.ButtonStyle> buttonStyleList = new ArrayList<>();
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, customerId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                com.bluemongo.springmvcjsontest.model.ButtonStyle buttonStyle = new com.bluemongo.springmvcjsontest.model.ButtonStyle();
                buttonStyle.setId(resultSet.getInt("id"));
                buttonStyle.setCreatedDate(resultSet.getDate("createdDate"));
                buttonStyle.setName(resultSet.getString("styleName"));
                buttonStyle.setTextColour(resultSet.getString("textColour"));
                buttonStyle.setBackgroundColorHex(resultSet.getString("backgroundColourHex"));
                buttonStyle.setPadding(resultSet.getString("padding"));
                buttonStyle.setClassification1(resultSet.getString("classification1"));
                buttonStyle.setClassification2(resultSet.getString("classification2"));
                buttonStyle.setClassification3(resultSet.getString("classification3"));

                buttonStyleList.add(buttonStyle);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return buttonStyleList;
    }

    public ButtonStyle get(int buttonStyleId) {
        String query = "select id, createdDate, styleName, textColour, backgroundColourHex, padding," +
                " classification1, classification2, classification3, customerId" +
                " from buttonStyle where id = ?";
        ButtonStyle buttonStyle = null;
        try(Connection connection = dbHelper.getConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, buttonStyleId);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                buttonStyle = new com.bluemongo.springmvcjsontest.model.ButtonStyle();
                buttonStyle.setId(resultSet.getInt("id"));
                buttonStyle.setCreatedDate(resultSet.getDate("createdDate"));
                buttonStyle.setName(resultSet.getString("styleName"));
                buttonStyle.setTextColour(resultSet.getString("textColour"));
                buttonStyle.setBackgroundColorHex(resultSet.getString("backgroundColourHex"));
                buttonStyle.setPadding(resultSet.getString("padding"));
                buttonStyle.setClassification1(resultSet.getString("classification1"));
                buttonStyle.setClassification2(resultSet.getString("classification2"));
                buttonStyle.setClassification3(resultSet.getString("classification3"));
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return buttonStyle;
    }
}

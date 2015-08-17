package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.ButtonStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by glenn on 17/08/15.
 */
public class PersistButtonStyle {
    static DBHelper dbHelper = new DBHelper();
    private static PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(PersistButtonStyle.class);

    public static void save(ButtonStyle buttonStyle){
        //insert into buttonStyle(styleName, textColour, backgroundColourHex, padding) values('buttonStyle1','#000000','#33CC33', '14dp 10dp 2dp 10dp');
        String query = "insert into buttonStyle(styleName, textColour, backgroundColourHex, padding) values(?,?,?,?)";
        try {
            preparedStatement = dbHelper.getConnection().prepareStatement(query);
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
}

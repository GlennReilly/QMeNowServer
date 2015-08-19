package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.ButtonStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by glenn on 17/08/15.
 */
public class PersistButtonStyle {
    static DBHelper dbHelper = new DBHelper();
    private static PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private static final Logger logger = LogManager.getLogger(PersistButtonStyle.class);

    public static void saveNew(ButtonStyle buttonStyle){
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

    public static List<ButtonStyle> get(long customerId) {
        String query = "select id, createdDate, styleName, textColour, backgroundColourHex, padding," +
                " classification1, classification2, classification3, customerId" +
                " from buttonStyle where customerId=? or customerId is null";
        List<ButtonStyle> buttonStyleList = new ArrayList<>();
        try {
            preparedStatement = dbHelper.getConnection().prepareStatement(query);
            preparedStatement.setLong(1, customerId);

            ResultSet rs = preparedStatement.executeQuery();
            ButtonStyle buttonStyle = new ButtonStyle();
            while (rs.next()){
                buttonStyle.setId(rs.getInt("id"));
                buttonStyle.setCreatedDate(rs.getDate("createdDate"));
                buttonStyle.setName(rs.getString("styleName"));
                buttonStyle.setTextColour(rs.getString("textColour"));
                buttonStyle.setBackgroundColorHex(rs.getString("backgroundColourHex"));
                buttonStyle.setPadding(rs.getString("padding"));
                buttonStyle.setClassification1(rs.getString("classification1"));
                buttonStyle.setClassification2(rs.getString("classification2"));
                buttonStyle.setClassification3(rs.getString("classification3"));

                buttonStyleList.add(buttonStyle);
            }
        }
        catch(Exception ex)
        {
            logger.info(ex.getMessage());
        }
        return buttonStyleList;
    }
}

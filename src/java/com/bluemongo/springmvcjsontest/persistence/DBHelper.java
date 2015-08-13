package com.bluemongo.springmvcjsontest.persistence;

/**
 * Created by glenn on 8/08/15.
 */
import org.apache.commons.logging.Log;

import java.sql.*;
import java.util.Properties;


public class DBHelper {

    protected Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        final String DBURL = "jdbc:mysql://localhost:3306/DemoJunk1";
        String username  = "user456";
        String password = "userHotDogFiasco$";
        Connection conn = null;

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", username);
        connectionProperties.put("password", password);
        conn = DriverManager.getConnection(DBURL,connectionProperties);
        return conn;
    }

    public String testDB(){
        String toReturn = "";
        try(Statement statement = getConnection().createStatement()){
            ResultSet rs = statement.executeQuery("select * from ConfigStore");
            while (rs.next()){
                toReturn += rs.getString("config") + " | ";
            }
        }
        catch (SQLException sqlex){
            sqlex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return toReturn;
    }


}

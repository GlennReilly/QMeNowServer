package com.bluemongo.springmvcjsontest.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by glenn on 21/02/15.
 */
public class PersistGreeting {
    private static Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private static final Logger logger = LogManager.getLogger(PersistGreeting.class);

    private static final String url = "jdbc:mysql://localhost:3306/grTestDB";
    private static final String user = "sqluser";
    private static final String password = "us3rP455w0rd!"; //set in mysql aws & dev


    public void SaveGreeting(com.bluemongo.springmvcjsontest.model.Greeting greeting) {
        initConnection();
        //initStatement();
        try {
            preparedStatement = connection.prepareStatement("insert into Greeting(comment) values(?)");
            preparedStatement.setString(1, greeting.getContent());
            preparedStatement.executeUpdate();
            logger.info("greeting saved.");
        }
        catch(SQLException sqlx)
        {
            logger.info(sqlx.getMessage());
        }
    }

/*    private void doQuery(String query) {
        try{
            connection.prepareStatement("");

        }catch(SQLException sqlx)
        {
            logger.info(sqlx.getMessage());
        }
    }*/

    public void initConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException sqlx)
        {
            logger.info(sqlx.getMessage());
        }
        catch (ClassNotFoundException cnfe) {
            logger.info(cnfe.getMessage());
        }
    }

    public void initStatement(){
        try{
            statement = connection.createStatement();

        }catch(SQLException sqlx)
        {
            logger.info(sqlx.getMessage());
        }
    }


}

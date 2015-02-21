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
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private static final Logger logger = LogManager.getLogger(PersistGreeting.class);

    private final String url = "jdbc:mysql://localhost:3306/grTestDB";
    private final String user = "sqluser";
    private final String password = "us3rP455w0rd!";


    public static void SaveGreeting(com.bluemongo.springmvcjsontest.model.Greeting greeting) {
        //AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider());
        //DynamoDBMapper mapper = new DynamoDBMapper(client);
        //mapper.save(greeting);
        logger.info("greeting saved.");
    }

    public Connection getConnection(){
        try{
            connection = DriverManager.getConnection(url, user, password);

        }catch(SQLException sqlx)
        {

        }
        return connection;
    }


}

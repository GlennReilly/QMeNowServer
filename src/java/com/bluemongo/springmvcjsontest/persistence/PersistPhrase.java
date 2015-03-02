package com.bluemongo.springmvcjsontest.persistence;

import com.bluemongo.springmvcjsontest.model.Phrase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

/**
 * Created by glenn on 1/03/15.
 */
public class PersistPhrase {

        private static Connection connection = null;
        private Statement statement = null;
        private PreparedStatement preparedStatement = null;
        private ResultSet resultSet = null;

        private static final Logger logger = LogManager.getLogger(PersistGreeting.class);

        private static final String url = "jdbc:mysql://localhost:3306/grTestDB";
        private static final String user = "sqluser";
        private static final String password = "us3rP455w0rd!"; //set in mysql aws & dev


        public void SavePhrase(Phrase phrase) {
            initConnection();
            //initStatement();
            try {
                preparedStatement = connection.prepareStatement("insert into Phrase(phraseText, author, phraseDate) values(?,?,?)");
                preparedStatement.setString(1, phrase.getPhraseText());
                preparedStatement.setString(2, phrase.getPhraseAuthor());
                preparedStatement.setDate(3, new java.sql.Date(phrase.getPhraseDate().getTime()));
                preparedStatement.executeUpdate();
                logger.info("phrase saved.");
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

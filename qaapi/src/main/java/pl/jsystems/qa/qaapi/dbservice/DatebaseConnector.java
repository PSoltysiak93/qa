package pl.jsystems.qa.qaapi.dbservice;

import pl.jsystems.qa.qaapi.configuration.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatebaseConnector {

    private static Connection connection = null;

    public static Connection getConnection(){
        if (connection == null) initConnection();
        return connection;
    }

    private static void initConnection(){

        try{
            Class.forName(Configuration.DB_CLASS);
            String url = Configuration.DB_URL;
            String user = Configuration.DB_USER;
            String password = Configuration.DB_PASSWORD;
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

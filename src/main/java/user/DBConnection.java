package user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final String DB_DRIVER_CLASS;
    private static final String DB_URL ;
    private static final String DB_USERNAME ;
    private static final String DB_PASSWORD ;

    static {
        String resourceName = "application.properties"; // could also be a constant
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException e) {
            System.out.println("app prop's not found");
        }
        DB_DRIVER_CLASS = props.getProperty("DB_DRIVER_CLASS");
        DB_URL = props.getProperty("DB_URL");
        DB_USERNAME = props.getProperty("DB_USERNAME");
        DB_PASSWORD = props.getProperty("DB_PASSWORD");
    }


    public static Connection getConnection() {

        Connection con = null;
        try {
            // load the Driver Class
            Class.forName(DB_DRIVER_CLASS);

            // create the connection now
            con = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
            System.out.println(e);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return con;
    }

}
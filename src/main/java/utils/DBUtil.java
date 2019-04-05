package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtil {

    private static final String mySqlUser = "root";
    private static final String mySqlPwd = "";
    private static final String mySqlCS = "jdbc:mysql://localhost:3306/car?autoReconnect=true&useSSL=false";

    public static Connection getConnection(DBType dbType) throws SQLException {

    Connection connection = null;

    switch(dbType){
        case MYSQL:
            connection = DriverManager.getConnection(mySqlCS,mySqlUser,mySqlPwd);
            break;
    }
    return connection;
    }

    public static void showErrorMessage(SQLException e){
        System.out.println("ERROR: " + e.getMessage());
        System.out.println("ERROR CODE: " + e.getErrorCode());
    }
}


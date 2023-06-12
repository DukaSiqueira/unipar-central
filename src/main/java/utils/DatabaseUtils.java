package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://3.142.131.90:5435/uniparcentral",
                "supervisor",
                "sup@"
        );
    }
}

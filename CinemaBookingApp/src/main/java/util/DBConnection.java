package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/db_cinema_booking";
    private static final String USER = "root";
    private static final String PASS = "n";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
        }
    }


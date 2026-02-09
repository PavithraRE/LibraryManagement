package com.kce.book.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getDBConnection() {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "pavi",
                    "pass123");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

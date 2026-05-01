package com.gecpian.profexphostelmanagement2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // These match your January project credentials
  private static final String URL = "jdbc:mysql://mysql-22a4c18-harsh12345677890.l.aivencloud.com:21545/defaultdb?ssl-mode=REQUIRED";
private static final String USER = "avnadmin";
private static final String PASS = "AVNS_8rshQhLh6mSKXmPgpBi";
public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // This line is strictly required for Web Apps to load the MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
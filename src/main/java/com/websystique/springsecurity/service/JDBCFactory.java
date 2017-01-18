
package com.websystique.springsecurity.service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCFactory {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://vps1.vistar.su:3306/vk_messenger?useUnicode=yes&amp;characterEncoding=UTF-8";
    static final String USER = "dasha";
    static final String PASSWORD = "dasha";
    static Connection connection;
    
    public static Connection getConnection() throws SQLException{
        if (connection == null)
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        return connection;
    }
  
}

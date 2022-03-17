/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RxZ
 */
public class DBContext {

    public Connection getConnection() throws Exception {
//    Class.forName("com.mysql.cj.jdbc.Driver");
//    return DriverManager.getConnection(URI, user, password);

        String user = "sa";
        String pass = "123456";
        String url = "jdbc:sqlserver://localhost;encrypt=false;databaseName=PRJ03";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, pass);

    }

    private final String URI = "jdbc:mysql://localhost:3306/sfblog_test?useSSL=false";
    private final String user = "root";
    private final String password = "rxz232198";
}

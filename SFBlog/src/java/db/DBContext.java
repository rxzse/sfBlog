/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author RxZ
 */

public class DBContext {

  public Connection getConnection() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");
    return DriverManager.getConnection(URI, user, password);
  }

  private final String URI = "jdbc:mysql://localhost:3306/sfblog_test?useSSL=false";
  private final String user = "root";
  private final String password = "rxz232198";
}

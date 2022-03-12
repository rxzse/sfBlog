/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RxZ
 */
public class AuthDAO {

    private static DBContext db = new DBContext();

    public static boolean existsAccount() {
        try {
            Connection conn = db.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "select 1 from auth");
            ResultSet rs = ps.executeQuery();
            
            boolean result = rs.next();
            
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean newAccount() {

        try {
            Connection conn = db.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "select 1 from auth");
            ResultSet rs = ps.executeQuery();
            
            boolean result = rs.next();
            
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static void main(String[] args) {
        System.out.println(AuthDAO.existsAccount());
    }
}

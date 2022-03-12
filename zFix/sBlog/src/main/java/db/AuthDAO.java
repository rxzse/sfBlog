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

import java.sql.Date;

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
            
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean newAccount(String password) {
        
        try {
            Date cur = new Date(System.currentTimeMillis());
            Connection conn = db.getConnection();
           
            String sql = "insert into auth (password, createTime, modifyTime) values (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setDate(2, cur);
            ps.setDate(3, cur);
            boolean result = ps.executeUpdate() != 0;
            
            conn.close();
            
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static void main(String[] args) {
        System.out.println(AuthDAO.existsAccount());
    }
}

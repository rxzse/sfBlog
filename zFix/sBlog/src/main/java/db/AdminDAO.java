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

import java.time.LocalDateTime;
import java.sql.Date;
import java.time.LocalDate;

import models.Post;
import models.Category;

/**
 *
 * @author RxZ
 */
public class AdminDAO {

    private static DBContext db = new DBContext();

    public static ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            Connection conn = db.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "select * from category order by sequence");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("alias"),
                        rs.getInt("sequence"),
                        rs.getDate("createTime"),
                        rs.getDate("modifyTime")
                ));
            };

            conn.close();

            return categories;

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static boolean newCategory(Category cartInsert) {
//        boolean eAcc = AuthDAO.existsAccount();
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();
           
            String sql = "insert into category (name, alias, sequence, createTime, modifyTime) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cartInsert.getName());
            ps.setString(2, cartInsert.getAlias());
            ps.setInt(3, cartInsert.getSequence());
            ps.setDate(4, cartInsert.getCreateTime());
            ps.setDate(5, cartInsert.getModifyTime());
            boolean result = ps.executeUpdate() != 0;
            
            conn.close();
            
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
    
     public static void main(String[] args) {
         ArrayList<Category> ct = AdminDAO.getCategories();
         for (Category i: ct) {
             System.out.println(i);
         }
    }
}

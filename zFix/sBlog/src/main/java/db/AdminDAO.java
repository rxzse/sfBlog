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

    // Region for category management
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
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();

            String sql = "insert into category (name, alias, sequence, createTime, modifyTime) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cartInsert.getName());
            ps.setString(2, cartInsert.getAlias());
            ps.setInt(3, cartInsert.getSequence());
            ps.setDate(4, cur);
            ps.setDate(5, cur);
            boolean result = ps.executeUpdate() != 0;

            conn.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean editCategory(int id, Category cartUpd) {
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();

            String sql = "update category set name = ?, alias = ?, sequence = ?, modifyTime = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cartUpd.getName());
            ps.setString(2, cartUpd.getAlias());
            ps.setInt(3, cartUpd.getSequence());
            ps.setDate(4, cur);
            ps.setInt(5, id);
            boolean result = ps.executeUpdate() != 0;

            conn.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean deleteCategory(int id) {
        boolean isSuccess = false;
        int otherCateId = 10000;
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();

            // Update all post in current category to others
            String sql_posts = "update post set category = ? where category = ?";
            PreparedStatement ps = conn.prepareStatement(sql_posts);
            ps.setInt(1, id);
            ps.setInt(2, otherCateId);
            boolean isPostsUpdated = ps.executeUpdate() != 0;

            // Delete current category
            if (isPostsUpdated) {
                String sql_del = "delete category where id = ?";
                ps = conn.prepareStatement(sql_del);
                ps.setInt(1, id);
                isSuccess = ps.executeUpdate() != 0;

            }
            conn.close();
            return isSuccess;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
    
    // Region for posts management
    public static ArrayList<Post> getPost() {
        ArrayList<Post> categories = new ArrayList<>();
        try {
            Connection conn = db.getConnection();

            PreparedStatement ps = conn.prepareStatement(
                    "select * from category order by sequence");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                categories.add(new Post());
            };

            conn.close();

            return categories;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean newPost(Post Post) {
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();

            String sql = "insert into category (name, alias, sequence, createTime, modifyTime) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cartInsert.getName());
            ps.setString(2, cartInsert.getAlias());
            ps.setInt(3, cartInsert.getSequence());
            ps.setDate(4, cur);
            ps.setDate(5, cur);
            boolean result = ps.executeUpdate() != 0;

            conn.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean editCategory(int id, Category cartUpd) {
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();

            String sql = "update category set name = ?, alias = ?, sequence = ?, modifyTime = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, cartUpd.getName());
            ps.setString(2, cartUpd.getAlias());
            ps.setInt(3, cartUpd.getSequence());
            ps.setDate(4, cur);
            ps.setInt(5, id);
            boolean result = ps.executeUpdate() != 0;

            conn.close();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static boolean deleteCategory(int id) {
        boolean isSuccess = false;
        int otherCateId = 10000;
        try {
            Date cur = Date.valueOf(LocalDateTime.now().toLocalDate());
            Connection conn = db.getConnection();

            // Update all post in current category to others
            String sql_posts = "update post set category = ? where category = ?";
            PreparedStatement ps = conn.prepareStatement(sql_posts);
            ps.setInt(1, id);
            ps.setInt(2, otherCateId);
            boolean isPostsUpdated = ps.executeUpdate() != 0;

            // Delete current category
            if (isPostsUpdated) {
                String sql_del = "delete category where id = ?";
                ps = conn.prepareStatement(sql_del);
                ps.setInt(1, id);
                isSuccess = ps.executeUpdate() != 0;

            }
            conn.close();
            return isSuccess;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public static void main(String[] args) {
        Category newCate = new Category(-1, "Othersss", "miumiu1", 10000, null, null);
        System.out.println(AdminDAO.editCategory(1, newCate));
        ArrayList<Category> ct = AdminDAO.getCategories();
        for (Category i : ct) {
            System.out.println(i);
        }
    }
}
